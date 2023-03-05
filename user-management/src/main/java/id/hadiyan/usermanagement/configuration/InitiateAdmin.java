package id.hadiyan.usermanagement.configuration;

import id.hadiyan.usermanagement.enums.Role;
import id.hadiyan.usermanagement.user.entities.User;
import id.hadiyan.usermanagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InitiateAdmin implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findByUsername("admin").isEmpty()){
            User user = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .name("System Administrator")
                    .email("admin@gmail.com")
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(user);
        }
    }
}
