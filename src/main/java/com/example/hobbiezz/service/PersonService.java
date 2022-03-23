package com.example.hobbiezz.service;

import com.example.hobbiezz.dto.PersonRequest;
import com.example.hobbiezz.dto.PersonResponse;
import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.error.Client4xxException;
import com.example.hobbiezz.repository.PersonRepository;
import org.springframework.http.HttpStatus;
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
        if (personRepository.emailExist(body.getEmail())) {
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

    public PersonResponse getPerson(int id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new Client4xxException("User not found"));
        return new PersonResponse(person);
    }

    /*
    *  public CarResponse getCar(int id,boolean all) throws Exception {
            //Car car = carRepository.findById(id).orElseThrow(()->new Exception("not found"));

            Car car2 = carRepository.findById(id).orElseThrow(()->new Client4xxException("not found"));
            return new CarResponse(car2, false);

        }*/

    //Andrea
    //Til denne metode skal der huskes at lave en constructure i PersonResponse
    public PersonResponse updatePerson (PersonRequest personToEdit, int personId) {
        Person personUpdated = personRepository.findById(personId).orElseThrow(()-> new Client4xxException("No person with provided ID found" + personId));
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
