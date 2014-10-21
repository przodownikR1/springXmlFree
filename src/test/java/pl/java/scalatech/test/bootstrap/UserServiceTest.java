package pl.java.scalatech.test.bootstrap;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import pl.java.scalatech.domain.Role;
import pl.java.scalatech.domain.User;
import pl.java.scalatech.repository.UserRepository;
import pl.java.scalatech.service.user.UserService;
import pl.java.scalatech.service.user.impl.UserServiceImpl;

import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService = new UserServiceImpl();
    @Mock
    private UserRepository userRepositoryMock;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldInitializeWithTwoDemoUsers() {
        // act
        userService.populate();
        // assert
    }

    @Test
    public void shouldThrowExceptionWhenUserNotFound() {
        // arrange
        thrown.expect(UsernameNotFoundException.class);
        thrown.expectMessage("user not found");
        when(userRepositoryMock.findUserByLogin("przodownik")).thenReturn(null);
        // act
        userRepositoryMock.findUserByLogin("przodownik");
    }

    @Test
    public void shouldReturnUserDetails() {
        // arrange
        Role user = Role.builder().name("USER").build();
        User demoUser = User.builder().firstName("slawek").lastName("borowiec").enabled(true).login("przodownik").password(passwordEncoder.encode("test")).roles(Lists.newArrayList(user)).build();
        when(userRepositoryMock.findUserByLogin("user@example.com")).thenReturn(demoUser);
        // act
        UserDetails userDetails = userRepositoryMock.findUserByLogin("przodownik");
        // assert
        assertThat(demoUser.getLogin()).isEqualTo(userDetails.getUsername());
        assertThat(demoUser.getPassword()).isEqualTo(userDetails.getPassword());
        assertThat(hasAuthority(userDetails, user.getName()));
    }

    private boolean hasAuthority(UserDetails userDetails, String role) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(role)) { return true; }
        }
        return false;
    }
}