package tn.esprit.healthcloud.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.config.CustomUserDetails;
import tn.esprit.healthcloud.config.JwtUtils;

import tn.esprit.healthcloud.config.request.LoginRequest;
import tn.esprit.healthcloud.config.request.SignupRequest;
import tn.esprit.healthcloud.config.response.JwtResponse;
import tn.esprit.healthcloud.config.response.MessageResponse;
import tn.esprit.healthcloud.entities.ERole;
import tn.esprit.healthcloud.entities.Role;
import tn.esprit.healthcloud.entities.User;
import tn.esprit.healthcloud.repositories.RoleRepository;
import tn.esprit.healthcloud.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")

public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        if (!userDetails.getStatut()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Votre Compte est desactiver contacter un Admin"));
        }
        else {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomUserDetails userDetails1 = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles,
                        userDetails.getStatut()));
    }}
    @DeleteMapping("/signout")
    public ResponseEntity<?> signoutUser(HttpServletRequest request, HttpServletResponse response) {
        // Retrieve the JWT token from the request headers
        String jwt = jwtUtils.parseJwt(request.getHeader(HttpHeaders.AUTHORIZATION).substring(7));

        // Invalidate the JWT token by adding an expired token as a cookie
        ResponseCookie cookie = ResponseCookie.from("jwt", "expired")
                .httpOnly(true)
                .maxAge(0)
                .secure(request.isSecure())
                .path("/")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(new MessageResponse("Aurevoir!!"));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();


            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "med":
                        Role medRole = roleRepository.findByName(ERole.ROLE_MEDECIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(medRole);

                        break;
                    case "chirur":
                        Role chirurRole = roleRepository.findByName(ERole.ROLE_CHIRURGIEN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(chirurRole);
                        break;
                    case "infi":
                        Role infiRole = roleRepository.findByName(ERole.ROLE_INFIRMIER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(infiRole);
                        break;
                    case "stag":
                        Role stagRole = roleRepository.findByName(ERole.ROLE_STAGIAIRE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(stagRole);
                        break;


                }
            });


        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Utilisateur creer avec succes!"));
    }
}

