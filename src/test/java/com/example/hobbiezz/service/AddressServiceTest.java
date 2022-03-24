package com.example.hobbiezz.service;

import com.example.hobbiezz.dto.AddressResponse;
import com.example.hobbiezz.entity.Address;
import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.repository.AddressRepository;
import com.example.hobbiezz.repository.PersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
class AddressServiceTest {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressRepository addressRepository;

    AddressService addressService;


    @BeforeAll
    static void setup(@Autowired AddressRepository addressRepository, @Autowired PersonRepository personRepository) {
        addressRepository.deleteAll();
        Address addressOne = addressRepository.save(new Address
                ("Street1", "AddInfo1", "Zip1", "City1"));
        Address addressTwo = addressRepository.save(new Address
                ("Street2", "AddInfo2", "Zip2", "City2"));

        personRepository.deleteAll();
        Person person1 = personRepository.save(new Person("Isabel@mail.dk", "Isabel",
                "Isabelsen", "911", addressOne));

    }


    @BeforeEach
    public void setupService() {
        addressService = new AddressService(addressRepository);
    }


    //Virker 22/3
    @Test
    void getAddressById() throws Exception {
        //Tests whether the correct exception is thrown when the id doesn't exist

        Exception exception = assertThrows(Exception.class, () -> {
            addressService.getAddressById(3);
        });

        String expectedMessage = "No address with this id exists";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        //Tester om metoden virker med et eksisterende id
        AddressResponse testAddress = addressService.getAddressById(1);
        assertEquals("Street1", testAddress.getStreet());
        assertNotEquals("Street2", testAddress.getStreet());
    }


    //Virker ikke 23/3
    @Test
    void getAddressConnectedToPerson() throws Exception {
        AddressResponse testAddress = addressService.getAddressConnectedToPerson(1);
        assertEquals("Street1", testAddress.getStreet());
        assertNotEquals("Street2", testAddress.getStreet());
    }

}