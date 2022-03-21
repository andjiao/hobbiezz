package com.example.hobbiezz.dto;

import com.example.hobbiezz.entity.Address;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)

public class AddressResponse {
    int id;
    String street;
    String additionalInfo;
    String zipCode;
    String city;

    public AddressResponse(String street, String additionalInfo, String zipCode, String city) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.zipCode = zipCode;
        this.city = city;
    }

    public AddressResponse(AddressRequest body) {
        this.id= body.getId();
        this.street = body.getStreet();
        this.additionalInfo = body.getAdditionalInfo();
        this.zipCode = body.getZipCode();
        this.city = body.getCity();
    }

    public AddressResponse(Address address) {
        this.id= address.getId();
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        this.zipCode = address.getZipCode();
        this.city = address.getCity();
    }
}
