package pl.slowikowski.demo.crud.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.slowikowski.demo.security.services.UserDetailsImpl;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        var userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return Optional.ofNullable(userDetails.getId());
    }
}
