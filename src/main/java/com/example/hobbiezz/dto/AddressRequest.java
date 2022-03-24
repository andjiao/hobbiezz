package com.example.hobbiezz.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressRequest {
    String street;
    String additionalInfo;
    String zipCode;
    String city;
}

