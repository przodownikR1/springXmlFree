package pl.java.scalatech.web;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.java.scalatech.annotation.CurrentUser;
import pl.java.scalatech.domain.User;

@Controller
@Slf4j
public class SecurityController {
    // Error page
    @RequestMapping("/error.html")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String errorMessage = null;
        if (throwable != null) {
            errorMessage = throwable.getMessage();
        }
        model.addAttribute("errorMessage", errorMessage.toString());
        return "error.html";
    }

    @RequestMapping("/currentUser")
    public ResponseEntity<User> currentUser(@CurrentUser User user) {
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping("secContext")
    public ResponseEntity<Map<String, Object>> secContext(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        model.addAttribute("principal", authentication.getName());

        return new ResponseEntity<>(model.asMap(), HttpStatus.OK);

    }
    @RequestMapping("/principal")
    public ResponseEntity<String> principal(Principal principal) {

        

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("++++    {}",auth.getAuthorities());
        
        return new ResponseEntity<>(principal.getName(), HttpStatus.OK);

    }
}
