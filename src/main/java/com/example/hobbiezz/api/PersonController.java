package com.example.hobbiezz.api;

import com.example.hobbiezz.dto.PersonRequest;
import com.example.hobbiezz.dto.PersonResponse;
import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.repository.PersonRepository;
import com.example.hobbiezz.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@CrossOrigin
@RestController


@RequestMapping("api/krak")
public class PersonController {
    PersonService personService;
    PersonRepository personRepository;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    //HobbyInfoRepo hobbyInfoRepo;
    //HobbyRepo hobbyRepo;


    //Virker 21/3
    @PostMapping
    public PersonResponse addPerson(@RequestBody PersonRequest body) throws Exception {
        return personService.addPerson(body);
    }

    //Virker 21/3
    @PutMapping("/{id}")
    public PersonResponse updatePerson(@RequestBody PersonRequest body, @PathVariable int id) throws Exception {
        return personService.updatePerson(body,id);
    }


    //Virker 21/3
    @GetMapping("/people")
    public List<PersonResponse> getPeople (){
        return personService.getPeople();
    }


    //Virker 21/3
    @GetMapping("/{id}")
    public Person getPerson (@PathVariable int id) throws Exception {
        return personService.getPerson(id);
    }

    /*
    //Dette endpoint returnerer en liste over hobbyer, der er tilknyttet en person
    @GetMapping("/personalhobbies/{id}")
    public List<Hobby> getPersonsHobbies (@PathVariable int id){
        List<HobbyInfo> hobbyInfos = hobbyInfoRepo.findHobbyInfosByHasHobbies_Id(id);
        List<Hobby> hobbies = null;

        for (HobbyInfo hobbyInfo: hobbyInfos) {
            hobbies.add(hobbyRepo.findHobbyByHobbyInfos(hobbyInfo));
        }

        return hobbies;
    }

     */

    /*
    //Dette endpoint returnerer alle personer, der er tilknyttet en hobby.
    @GetMapping("/people/{name}")
    public List<Person> getPeopleConnectedToHobby (@PathVariable String name){
        Hobby hobby = hobbyRepo.getById(name); //Skal ændres

        List<HobbyInfo> hobbyInfos = hobbyInfoRepo.findHobbyInfosByHobbyAdded(hobby);

        List<Person> people = null;

        for (HobbyInfo hobbyInfo: hobbyInfos) {
            people.add(personRepository.findPersonByHobbyInfos(hobbyInfo));
        }

        return people;
    }

     */


    //Lavet af Isabel
    //Virker 21/3
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable int id) {
        personService.deletePerson(id);
    }

}

