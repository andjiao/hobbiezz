package com.example.hobbiezz.dto;

import com.example.hobbiezz.entity.Address;
import com.example.hobbiezz.entity.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PersonResponse {
    int id;
    String email;
    String firstName;
    String lastName;
    String phone;
    Address address;


    public PersonResponse(Person person, boolean includeId, boolean includeContactInfo) {
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.phone = person.getPhone();

        if(includeId){
            this.id = person.getId();
        }
        if(includeContactInfo){
            this.phone = person.getPhone();
            this.email = person.getEmail();
            this.address= person.getConnectedAddress();
        }
    }

    public PersonResponse(Person person) {
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.phone = person.getPhone();
        this.address= person.getConnectedAddress();
    }


    public static List<PersonResponse> getPersonEntities (List<Person> persons){
        return persons.stream().map(person -> new PersonResponse(person)).collect(Collectors.toList());

    }




}

