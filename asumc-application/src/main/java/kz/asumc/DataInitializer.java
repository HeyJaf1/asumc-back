package kz.asumc;

import kz.asumc.storage.entity.AuthProvider;
import kz.asumc.storage.entity.UserEntity;
import kz.asumc.storage.entity.UserRole;
import kz.asumc.storage.entity.UserType;
import kz.asumc.storage.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.findByUsername("admin").isEmpty()) {
            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFullName("Admin User");
            admin.setEmail("admin@asumc.kz");
            admin.setUserType(UserType.INTERNAL);
            admin.setRole(UserRole.ADMIN_CMC);
            admin.setAuthProvider(AuthProvider.LOCAL);
            admin.setActive(true);
            userRepository.save(admin);
            System.out.println(">>> Admin user created: admin / admin123");
        }
    }
}
