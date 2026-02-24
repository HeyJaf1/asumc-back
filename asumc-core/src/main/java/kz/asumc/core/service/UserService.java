package kz.asumc.core.service;

import kz.asumc.dto.LoginRequestDto;
import kz.asumc.dto.LoginResponseDto;
import kz.asumc.dto.UserDto;
import kz.asumc.storage.entity.AuthProvider;
import kz.asumc.storage.entity.UserEntity;
import kz.asumc.storage.entity.UserRole;
import kz.asumc.storage.entity.UserType;
import kz.asumc.storage.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .disabled(!user.isActive())
                .build();
    }

    public LoginResponseDto login(LoginRequestDto dto) {
        UserEntity user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new UsernameNotFoundException("Invalid credentials");
        }
        String token = jwtService.generateToken(user);
        return new LoginResponseDto(token, user.getUserType().name(), user.getRole().name());
    }

    public UserDto createUser(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getUsername());
        entity.setPassword(passwordEncoder.encode(dto.getUsername()));
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setUserType(UserType.valueOf(dto.getUserType()));
        entity.setRole(UserRole.valueOf(dto.getRole()));
        entity.setAuthProvider(AuthProvider.LOCAL);
        entity.setActive(true);

        UserEntity saved = userRepository.save(entity);

        dto.setId(saved.getId());
        return dto;
    }
}
