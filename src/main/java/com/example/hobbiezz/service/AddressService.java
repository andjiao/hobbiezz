package com.example.hobbiezz.service;



import com.example.hobbiezz.dto.AddressRequest;
import com.example.hobbiezz.dto.AddressResponse;
import com.example.hobbiezz.entity.Address;
import com.example.hobbiezz.repository.AddressRepository;
import com.example.hobbiezz.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    AddressRepository addressRepository;

    PersonRepository personRepository;


    //Constructor
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository=addressRepository;
    }

/*
    public AddressResponse addAddress(AddressRequest body){
        if (addressRepository.addressExists(body)) {
            System.out.println("Address is already in database");
            return new AddressResponse(body);
        }
        Address addressNew = addressRepository.save(new Address(body));
        return new AddressResponse(addressNew);
    }

 */

    public AddressResponse getAddressById(int id) throws Exception {
        Address address = addressRepository.findById(id).orElseThrow(()->new Exception(
                "No address with this id exists"));
        return new AddressResponse(address);
    }

    public AddressResponse getAddressConnectedToPerson(int personId) throws Exception {
        Address address = personRepository.findById(personId).get().getConnectedAddress();
        //orElseThrow(()->new Client4xxException("No address with this id exists"));
        return new AddressResponse(address);
    }




    //Skal man kunne slette addresser?
}
