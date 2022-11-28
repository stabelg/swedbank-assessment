package com.example.swedbankassessment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "application_user_detail")
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String billingAddress;

    @Column
    private String shippingAddress;
}
