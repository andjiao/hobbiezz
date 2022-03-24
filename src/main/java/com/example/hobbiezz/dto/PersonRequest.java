package com.example.hobbiezz.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonRequest {
    String email;
    String firstName;
    String lastName;
    String phone;

}