package com.example.hobbiezz.api;

import com.example.hobbiezz.dto.HobbyResponse;
import com.example.hobbiezz.dto.PersonRequest;
import com.example.hobbiezz.dto.PersonResponse;
import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.entity.HobbyInfo;
import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.repository.HobbyInfoRepository;
import com.example.hobbiezz.repository.HobbyRepository;
import com.example.hobbiezz.repository.PersonRepository;
import com.example.hobbiezz.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@CrossOrigin
@RestController


@RequestMapping("api/person")
public class PersonController {
    PersonService personService;
    PersonRepository personRepository;
    HobbyInfoRepository hobbyInfoRepository;
    HobbyRepository hobbyRepository;

    public PersonController(PersonService personService, PersonRepository personRepository, HobbyInfoRepository hobbyInfoRepository, HobbyRepository hobbyRepository) {
        this.personService = personService;
        this.personRepository = personRepository;
        this.hobbyInfoRepository = hobbyInfoRepository;
        this.hobbyRepository = hobbyRepository;
    }


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
    public PersonResponse getPerson (@PathVariable int id) throws Exception {
        return personService.getPerson(id);
    }


    //Dette endpoint returnerer en liste over hobbyer, der er tilknyttet en person
    @GetMapping("/personalhobbies/{id}")
    public List<HobbyResponse> getPersonsHobbies (@PathVariable int id){
        List<HobbyInfo> hobbyInfos = hobbyInfoRepository.findHobbyInfosByHasHobbies_Id(id);
        List<Hobby> hobbies = new ArrayList();

        for (HobbyInfo hobbyInfo: hobbyInfos) {
            hobbies.add(hobbyRepository.findHobbyByHobbyInfos(hobbyInfo));
        }

        List<HobbyResponse> hobbieResponses = new ArrayList<>();

        hobbies.forEach((hobby) -> hobbieResponses.add(new HobbyResponse(hobby)));

        return hobbieResponses;
    }

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

