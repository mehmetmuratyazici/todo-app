package io.mmy.todoapp.service.auth;

import io.mmy.todoapp.model.User;
import io.mmy.todoapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){
        userRepository.save(User
                .builder()
                .username("mmy")
                .password(passwordEncoder.encode("mmy"))
                .email("mhmtmrtyzc@gmail.com")
                .firstName("Mehmet Murat")
                .lastName("YAZICI")
                .phoneNumber("5353638368")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                        .enabled(true)
                .build());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(s);

        if(!user.isPresent()){
            throw new UsernameNotFoundException("User not found with username : "+ s);
        }

        return user.get();
    }
}
