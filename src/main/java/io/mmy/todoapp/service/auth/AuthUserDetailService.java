package io.mmy.todoapp.service.auth;

import io.mmy.todoapp.dto.UserDto;
import io.mmy.todoapp.model.Mession;
import io.mmy.todoapp.model.User;
import io.mmy.todoapp.repo.UserRepository;
import io.mmy.todoapp.service.mession.MessionServiceImpl;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthUserDetailService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(AuthUserDetailService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

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

    public JSONObject registerUser(UserDto userDto) {
        JSONObject result = new JSONObject();

        try{
            User user = modelMapper.map(userDto, User.class);
            userRepository.save(user);
            result.put("success", true);
            result.put("id", user.getId());
        }catch (Exception e){
            result.put("success", false);
            logger.debug(e.getMessage());
        }
        return result;
    }
}
