package com.codewithjona.sistemalibros.user;

import com.codewithjona.sistemalibros.enums.Roles;
import com.codewithjona.sistemalibros.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public AuthResponse register(RegisterRequest request){

        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("Username already exits");
        }
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);

        if(request.getRoles() != null){
            user.setRoles(request.getRoles());
        }else{
            user.setRoles(Roles.USER);
        }

        User userSaved = userRepository.save(user);
        //GenerateToken
        String token = jwtService.generateToken(userSaved);

        return new AuthResponse(token,userSaved.getUsername(),userSaved.getEmail(),userSaved.getRoles().name());
    }

    public AuthResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid User");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponse(token,user.getUsername(),user.getEmail(),user.getRoles().name());
    }
}
