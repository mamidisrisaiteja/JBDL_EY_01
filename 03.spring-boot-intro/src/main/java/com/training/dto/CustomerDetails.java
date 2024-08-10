package com.training.dto;

import com.training.entity.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDetails {
    private int id;
    private String name;
    private String email;
    private String password;
    private MultipartFile profilePic;

    private Address address;
}
