package com.example.hobbiezz.service;

import com.example.hobbiezz.dto.HobbyInfoResponse;
import com.example.hobbiezz.dto.PersonRequest;
import com.example.hobbiezz.dto.PersonResponse;
import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.entity.HobbyInfo;
import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.repository.HobbyInfoRepository;
import com.example.hobbiezz.repository.HobbyRepository;
import com.example.hobbiezz.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    PersonRepository personRepository;
    HobbyRepository hobbyRepository;
    HobbyInfoRepository hobbyInfoRepository;

    public PersonService(PersonRepository personRepository, HobbyRepository hobbyRepository, HobbyInfoRepository hobbyInfoRepository) {
        this.personRepository = personRepository;
        this.hobbyRepository = hobbyRepository;
        this.hobbyInfoRepository = hobbyInfoRepository;
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
        personUpdated.setConnectedAddress(personToEdit.getAddress());

        return new PersonResponse(personRepository.save(personUpdated));
    }


    public void deletePerson(int id){
        personRepository.deleteById(id);
    }

    public List<PersonResponse> getPeopleConnectedToHobby (String name){
        Hobby hobby = hobbyRepository.getById(name);

        List<HobbyInfo> hobbyInfos = hobbyInfoRepository.findHobbyInfosByHobbyObject(hobby);

        List<Person> people = new ArrayList();

        for (HobbyInfo hobbyInfo: hobbyInfos) {
            people.add(personRepository.findPersonByHobbyInfos(hobbyInfo));
        }

        List<PersonResponse> personResponses = new ArrayList<>();

        people.forEach((person) -> personResponses.add(new PersonResponse(person)));

        return personResponses;
    }

}
