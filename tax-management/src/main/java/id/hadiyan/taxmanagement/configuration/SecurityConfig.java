package id.hadiyan.taxmanagement.configuration;

import id.hadiyan.taxmanagement.enums.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    String jwkSetUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.POST, "/submit").hasAuthority(Role.MAKER.scope())
                        .requestMatchers(HttpMethod.POST, "/check").hasAuthority(Role.CHECKER.scope())
                        .requestMatchers(HttpMethod.POST, "/approve").hasAuthority(Role.APPROVER.scope())
                        .requestMatchers(HttpMethod.POST, "/reject").hasAnyAuthority(Role.CHECKER.scope(), Role.APPROVER.scope())
                        .requestMatchers("/**").hasAnyAuthority(Role.MAKER.scope(), Role.CHECKER.scope(), Role.APPROVER.scope())
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        // @formatter:on
        return http.build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer("http://localhost:8080");
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
        jwtDecoder.setJwtValidator(withIssuer);
        return jwtDecoder;
    }
}
