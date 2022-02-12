package com.learning.controller.auth;

import com.learning.exception.CustomException;
import com.learning.payload.request.LoginRequest;
import com.learning.payload.request.SignupRequest;
import com.learning.service.UserDetailsServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Api(tags = "Auth Controller")
public class AuthController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping("/signin")
    @ApiOperation(value = "Signin User")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public ResponseEntity<?> authenticateUser(@ApiParam(name =  "loginRequest",
            type = "LoginRequest",
            value = "Login user",
            required = true) @Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userDetailsService.signin(loginRequest));
    }

    @PostMapping("/signup")
    @ApiOperation(value = "Signup User")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 422, message = "Username is already in use")})
    public ResponseEntity<?> registerUser(
            @ApiParam(name = "signUpRequest",
            type = "SignupRequest",
            value = "Signup user",
            required = true) @Valid @RequestBody SignupRequest signUpRequest) {
        if (userDetailsService.existsByUsername(signUpRequest.getUsername())) {
            throw new CustomException("Error: Username is already taken!", HttpStatus.CONFLICT);
        }
        if (userDetailsService.existsByEmail(signUpRequest.getEmail())) {
            throw new CustomException("Error: Email is already in use!", HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(userDetailsService.signup(signUpRequest));
    }
}
