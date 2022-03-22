package com.example.hobbiezz.dto;

import com.example.hobbiezz.entity.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {

    String street;
    String additionalInfo;
    String zipCode;
    String city;

    public AddressResponse(AddressRequest body) {
        this.street = body.getStreet();
        this.additionalInfo = body.getAdditionalInfo();
        this.zipCode = body.getZipCode();
        this.city = body.getCity();
    }

    public AddressResponse(Address address) {
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        this.zipCode = address.getZipCode();
        this.city = address.getCity();
    }
}
