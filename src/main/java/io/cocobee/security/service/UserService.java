package io.cocobee.security.service;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import io.cocobee.security.model.User;

@Service
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private List<User> users = new ArrayList<>();

    public List<User> findAll() {
        return users;
    }

    public User findByUsername(String username) {
        return users.stream().filter(u -> StringUtils.equals(u.getUsername(), username)).findFirst().orElse(null);
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        users.add(user);
        return user;
    }

    public String delete(String username) {
        for (User user : users) {
            if (Objects.equals(username, user.getUsername())) {
                users.remove(user);
                return username;
            }
        }
        return null;
    }

    @PostConstruct
    public void init() {
        User user = new User();
        user.setUsername("cocobee");
        user.setPassword("secret");
        save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
    }

    private List getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
