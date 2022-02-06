package com.learning.service;

import com.learning.config.JwtTokenUtil;
import com.learning.exception.CustomException;
import com.learning.model.ERole;
import com.learning.model.Role;
import com.learning.model.User;
import com.learning.model.UserDetailsImpl;
import com.learning.payload.request.LoginRequest;
import com.learning.payload.request.SignupRequest;
import com.learning.payload.response.JwtResponse;
import com.learning.payload.response.MessageResponse;
import com.learning.repository.RoleRepository;
import com.learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(user);
  }

  public JwtResponse signin(@Valid LoginRequest loginRequest) {
    try {
      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

      SecurityContextHolder.getContext().setAuthentication(authentication);

      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      List<String> roles = userDetails.getAuthorities().stream()
              .map(item -> item.getAuthority())
              .collect(Collectors.toList());

      final String token = jwtTokenUtil.generateToken(userDetails);
      return  new JwtResponse(token,
              userDetails.getId(),
              userDetails.getUsername(),
              userDetails.getEmail(),
              roles);
    } catch (DisabledException e) {
      throw new CustomException("User has been disabled", HttpStatus.UNPROCESSABLE_ENTITY);
    } catch (BadCredentialsException e) {
      throw new CustomException("Invalid credentials", HttpStatus.UNPROCESSABLE_ENTITY);
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public MessageResponse signup(@Valid SignupRequest signUpRequest) {

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new CustomException("Error: Role is not found.",HttpStatus.NOT_FOUND));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new CustomException("Error: Role is not found.",HttpStatus.NOT_FOUND));
            roles.add(adminRole);

            break;
          case "mod":
            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                    .orElseThrow(() -> new CustomException("Error: Role is not found.",HttpStatus.NOT_FOUND));
            roles.add(modRole);
            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new CustomException("Error: Role is not found.",HttpStatus.NOT_FOUND));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return new MessageResponse("User registration successful");
  }

  public void delete(String username) {
    userRepository.deleteByUsername(username);
  }

  public User search(String username) {
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isEmpty()) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return user.get();
  }

  public Optional<User> whoami(HttpServletRequest req) {
    return userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(jwtTokenUtil.parseJwt(req)));
  }

  public String refresh(String username) {
    UserDetails userDetails = UserDetailsImpl.build( userRepository.findByUsername(username).get());
    return jwtTokenUtil.generateToken(userDetails);
  }

  public boolean existsByUsername(String username) {
    Optional<User> user = userRepository.findByUsername(username);
    return user.isPresent();
  }

  public boolean existsByEmail(String email) {
    Optional<User> user = userRepository.findByEmail(email);
    return user.isPresent();
  }
}
