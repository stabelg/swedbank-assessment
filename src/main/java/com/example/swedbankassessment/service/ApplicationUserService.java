package com.example.swedbankassessment.service;

import com.example.swedbankassessment.entity.ApplicationUser;
import com.example.swedbankassessment.model.UserRequest;
import com.example.swedbankassessment.repository.ApplicationUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplicationUserService {

    private ApplicationUserRepository applicationUserRepository;
    private PasswordEncoder passwordEncoder;
    public ApplicationUser saveApplicationUser(UserRequest userRequest){
        return saveApplicationUser(userRequest.toApplicationUser());
    }

    public ApplicationUser saveApplicationUser(ApplicationUser applicationUser){
        ApplicationUser duplicated = applicationUserRepository.findByUsernameOrEmail
                (applicationUser.getUsername(), applicationUser.getEmail());
        if(duplicated != null && applicationUser.getId() != duplicated.getId()) {
            throw new RuntimeException("User email/username already exists");
        }
        applicationUser.setPassword(encryptPassword(applicationUser.getPassword()));
        return applicationUserRepository.save(applicationUser);
    }

    public UserRequest updateApplicationUser(UserRequest userRequest){
        if (userRequest.getId() == null){
            throw new RuntimeException("User id cannot be null");
        }
        findApplicationUserById(userRequest.getId());
        return converToUserRequest(saveApplicationUser(userRequest));
    }

    public List<UserRequest> findAllApplicationUser(){
        Iterable<ApplicationUser> applicationUserIterable = applicationUserRepository.findAll();
        List<UserRequest> result = new ArrayList<>();
        for (ApplicationUser applicationUser: applicationUserIterable) {
            result.add(converToUserRequest(applicationUser));
        }
        return result;
    }

    public UserRequest findApplicationUserByIdUserModel(Long applicationUserId){
        ApplicationUser applicationUser = findApplicationUserById(applicationUserId);
        return converToUserRequest(applicationUser);
    }
    public ApplicationUser findApplicationUserById(Long applicationUserId){
        Optional<ApplicationUser> applicationUser = applicationUserRepository.findById(applicationUserId);
        if(applicationUser.isEmpty()){
            throw new RuntimeException("User with id wasnt found");
        } else {
            return applicationUser.get();
        }
    }

    public ApplicationUser findApplicationUserByUsername(String username){
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if(applicationUser == null){
            throw new RuntimeException("User with id wasnt found");
        } else {
            return applicationUser;
        }
    }

    public void deleteApplicationUser(Long applicationUserId){
        //Checks if user exists
        ApplicationUser applicationUser = findApplicationUserById(applicationUserId);
        applicationUserRepository.delete(applicationUser);
    }

    public UserRequest converToUserRequest(ApplicationUser applicationUser){
        return UserRequest.builder()
                .id(applicationUser.getId())
                .email(applicationUser.getEmail())
                //JUST IN CASE YOU WANT TO SEE THE PASSWORD OF USERS
                //.password(applicationUser.getPassword())
                .name(applicationUser.getUserDetail().getName())
                .shippingAddress(applicationUser.getUserDetail().getShippingAddress())
                .billingAddress(applicationUser.getUserDetail().getBillingAddress())
                .username(applicationUser.getUsername())
                .build();
    }

    private String encryptPassword(String password){
        return passwordEncoder.encode(password);
    }
}
