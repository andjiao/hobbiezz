package com.example.hobbiezz.service;



import com.example.hobbiezz.dto.AddressRequest;
import com.example.hobbiezz.dto.AddressResponse;
import com.example.hobbiezz.entity.Address;
import com.example.hobbiezz.error.Client4xxException;
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


    //virker
    public AddressResponse addAddress(AddressRequest body){

        Address addressNew = addressRepository.save(new Address(body));
        return new AddressResponse(addressNew);
    }


    //Virker
    public AddressResponse getAddressById(int id) {
        Address address = addressRepository.findById(id).orElseThrow(()-> new Client4xxException(
                "No address with this id exists"));
        return new AddressResponse(address);
    }

    public AddressResponse getAddressConnectedToPerson(int personId) {
        Address address = personRepository.findById(personId).get().getConnectedAddress();
        //orElseThrow(()->new Client4xxException("No address with this id exists"));
        return new AddressResponse(address);
    }




    //Skal man kunne slette addresser?
}
