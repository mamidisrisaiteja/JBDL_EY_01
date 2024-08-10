package com.training.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class RegisterStatus extends Status{
    private int registeredCustomerId;


}
