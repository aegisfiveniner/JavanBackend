package com.maria.usermanagement.config.audit;

import com.maria.usermanagement.security.CustomUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String id = null;

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated()) {
                return Optional.ofNullable(id);
            }

            CustomUserDetails userDetails= (CustomUserDetails) authentication.getPrincipal();
            id = userDetails.getUsername();
        }
        catch(Exception e) {
            id = null;
        }

        return Optional.ofNullable(id);
    }
}

