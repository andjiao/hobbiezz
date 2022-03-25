package com.example.hobbiezz.service;

import com.example.hobbiezz.dto.PersonRequest;
import com.example.hobbiezz.dto.PersonResponse;
import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    PersonRepository personRepository;


    public PersonService(PersonRepository personRepository) {
        this.personRepository=personRepository;
    }


    public PersonResponse addPerson(PersonRequest body) throws Exception {

        //Kun, hvis e-mail skal være unik
        if (personRepository.emailExists(body.getEmail())) {
            throw new Exception("Provided email is taken");
        }
        Person personNew = personRepository.save(new Person(body));
        return new PersonResponse(personNew,true,true);
    }


    //Omskriv med stream
    public List<PersonResponse> getPeople(){
        List<Person> people = personRepository.findAll();
        return PersonResponse.getPersonEntities(people);
    }

    public PersonResponse getPerson(int id) throws Exception {
        Person person = personRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
        return new PersonResponse(person);
    }

    //Andrea
    //Til denne metode skal der huskes at lave en constructure i PersonResponse
    public PersonResponse updatePerson (PersonRequest personToEdit, int personId) throws Exception {
        Person personUpdated = personRepository.findById(personId).orElseThrow(()-> new Exception("No person with provided ID found" + personId));
        personUpdated.setEmail(personToEdit.getEmail());
        personUpdated.setFirstName(personToEdit.getFirstName());
        personUpdated.setLastName(personToEdit.getLastName());
        personUpdated.setPhone(personToEdit.getPhone());

        return new PersonResponse(personRepository.save(personUpdated));
    }


    public void deletePerson(int id){
        personRepository.deleteById(id);
    }

}
