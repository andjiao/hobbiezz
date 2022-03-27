package com.example.hobbiezz.security;


import com.example.hobbiezz.entity.Role;
import com.example.hobbiezz.entity.BaseUser;
//import com.example.hobbiezz.error.Client4xxException;
import com.example.hobbiezz.error.Client4xxException;
import com.example.hobbiezz.security.dto.SignupRequest;
import com.example.hobbiezz.security.dto.SignupResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SignupResponse createUser(SignupRequest request){

        if(userRepository.existsByUsername(request.getUsername())){
            throw new Client4xxException("Username is taken");
        }
        if(userRepository.existsByEmail(request.getEmail())){
            throw new Client4xxException("Email is used by another user");
        }

        BaseUser user = new BaseUser(request.getUsername(), request.getEmail(), request.getPassword());


        //All new users are by default given the role CUSTOMER. Comment out the lines below if this is not your required behaviour
        user.addRole(Role.USER);

        userRepository.save(user);
        List<String> roleNames = user.getRoles().stream().map(role -> role.toString()).collect(Collectors.toList());
        //No need to return a body since primary key is the provided userName
        return new SignupResponse(roleNames);

    }

}
