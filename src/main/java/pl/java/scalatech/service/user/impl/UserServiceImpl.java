package pl.java.scalatech.service.user.impl;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import pl.java.scalatech.domain.Role;
import pl.java.scalatech.domain.User;
import pl.java.scalatech.repository.UserRepository;
import pl.java.scalatech.service.user.UserService;

@Service
@Transactional(readOnly = true)
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    private  UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    @Transactional
    public User save(User user) {

        return userRepository.save(user);
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void populate() {
       Role user = Role.builder().name("USER").build();
       Role admin = Role.builder().name("ADMIN").build();
       
       User userOne = User.builder().firstName("slawek").lastName("borowiec").enabled(true).login("przodownik").password(passwordEncoder.encode("test")).roles(Lists.newArrayList(user)).build();
       User userTwo = User.builder().firstName("kalina").lastName("borowiec").enabled(true).login("bak").password(passwordEncoder.encode("test")).roles(Lists.newArrayList(admin)).build();
       
       save(userOne);
       save(userTwo);
        
    }
}
