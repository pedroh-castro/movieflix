package com.phc.movieflix.service;

import com.phc.movieflix.config.TokenService;
import com.phc.movieflix.dtos.request.LoginRequest;
import com.phc.movieflix.dtos.request.UserRequest;
import com.phc.movieflix.dtos.response.LoginResponse;
import com.phc.movieflix.dtos.response.UserResponse;
import com.phc.movieflix.entity.User;
import com.phc.movieflix.exceptions.UsernameOrPasswordInvalidException;
import com.phc.movieflix.mapper.UserMapper;
import com.phc.movieflix.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Transactional
    public UserResponse saveUser(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(
                    loginRequest.email(),
                    loginRequest.password()
            );

            Authentication authenticate = authenticationManager.authenticate(userAndPass);

            User user = (User) authenticate.getPrincipal();
            String token = tokenService.generateToken(user);
            return new LoginResponse(token);

        } catch (BadCredentialsException ex) {
            throw new UsernameOrPasswordInvalidException("Usuário ou Password inválido");
        }

    }
}
