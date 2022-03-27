package com.example.hobbiezz.api;

import com.example.hobbiezz.dto.HobbyInfoResponse;
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

import javax.annotation.security.RolesAllowed;
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
    @RolesAllowed("ADMIN")
    public PersonResponse updatePerson(@RequestBody PersonRequest body, @PathVariable int id) throws Exception {
        return personService.updatePerson(body,id);
    }


    //Virker 21/3
    @GetMapping("/people")
    @RolesAllowed("ADMIN")
    public List<PersonResponse> getPeople (){
        return personService.getPeople();
    }


    //Virker 21/3
    @GetMapping("/{id}")
    @RolesAllowed("ADMIN")
    public PersonResponse getPerson (@PathVariable int id) throws Exception {
        return personService.getPerson(id);
    }


    //Dette endpoint returnerer en liste over hobbyer, der er tilknyttet en person
    //Der skal skrives en metode i service, der erstatter den kode, der er skrevet her.
    // Controlleren skal blot kalde metoden.
    @GetMapping("/personalhobbies/{id}")
    @RolesAllowed("ADMIN")
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

    //Ikke testet
    //Dette endpoint returnerer alle personer, der er tilknyttet en hobby.
    @GetMapping("/hobby/{name}")
    @RolesAllowed("ADMIN")
    public List<PersonResponse> getPeopleConnectedToHobby (@PathVariable String name){
        return personService.getPeopleConnectedToHobby(name);
    }


    //Lavet af Isabel
    //Virker 21/3
    //Virker ikke 24/3
    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")
    public void deletePerson(@PathVariable int id) {
        personService.deletePerson(id);
    }

}

