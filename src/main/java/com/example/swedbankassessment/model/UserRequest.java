package com.example.swedbankassessment.model;

import com.example.swedbankassessment.entity.ApplicationUser;
import com.example.swedbankassessment.entity.UserDetail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    private Long id;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_.-]*$", message = "Username cannot contain spaces")
    @Size(max = 50)
    private String username;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_.-]*$", message = "Password cannot contain spaces")
    @Size(max = 50)
    private String password;
    @NotBlank
    @Size(max = 50)
    private String name;
    @Email
    @NotBlank
    @Size(max = 50)
    private String email;
    @Size(max = 150)
    private String billingAddress;
    @Size(max = 150)
    private String shippingAddress;

    public ApplicationUser toApplicationUser(){
        return ApplicationUser.builder()
                .id(id)
                .email(email)
                .password(password)
                .username(username)
                .userDetail(UserDetail.builder()
                        .name(name)
                        .billingAddress(billingAddress)
                        .shippingAddress(shippingAddress)
                        .build())
                .build();
    }
}
