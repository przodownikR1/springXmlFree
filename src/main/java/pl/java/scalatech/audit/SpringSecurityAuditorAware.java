package pl.java.scalatech.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import pl.java.scalatech.domain.User;
import pl.java.scalatech.repository.UserRepository;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<User> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getCurrentAuditor() {
        User user = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if(securityContext.getAuthentication()!= null && securityContext.getAuthentication().getPrincipal()!= null){
           user = (User) securityContext.getAuthentication().getPrincipal();
        }
        return user;
    }
    
}