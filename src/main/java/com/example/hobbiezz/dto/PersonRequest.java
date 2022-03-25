package com.example.hobbiezz.dto;

import com.example.hobbiezz.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {
    String email;
    String firstName;
    String lastName;
    String phone;
    Address address;

}