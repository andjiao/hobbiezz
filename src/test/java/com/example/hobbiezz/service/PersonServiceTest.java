package com.example.hobbiezz.service;

import com.example.hobbiezz.dto.PersonRequest;
import com.example.hobbiezz.dto.PersonResponse;
import com.example.hobbiezz.entity.Address;
import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.repository.AddressRepository;
import com.example.hobbiezz.repository.HobbyInfoRepository;
import com.example.hobbiezz.repository.HobbyRepository;
import com.example.hobbiezz.repository.PersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonServiceTest {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    HobbyInfoRepository hobbyInfoRepository;

    @Autowired
    HobbyRepository hobbyRepository;


    PersonService personService;

    static int person1Id, person2Id;

    static int addressOne;

    @BeforeAll
    static void setup(@Autowired PersonRepository personRepository, @Autowired AddressRepository addressRepository,
                      @Autowired HobbyInfoRepository hobbyInfoRepository, @Autowired HobbyRepository hobbyRepository) {
        addressRepository.deleteAll();
        hobbyInfoRepository.deleteAll();
        hobbyRepository.deleteAll();
        personRepository.deleteAll();
        person1Id = personRepository.save(new Person("Isabel@mail.dk", "Isabel", "Isabelsen", "911")).getId();
        person2Id = personRepository.save(new Person("Andrea@mail.dk", "Andrea", "Andreasen", "88888888")).getId();
    }


    @BeforeEach
    public void setupService() {
        personService = new PersonService(personRepository, hobbyRepository, hobbyInfoRepository);
    }

    //Virker 22/3
    @Test
    void addPerson() throws Exception {
        Address a1 = new Address
                ("GadeVænget 1", "3. tv", "2200", "København");
        addressRepository.save(a1);
        addressOne= a1.getId();

        PersonRequest pr1 = new PersonRequest("Isabel@mail.dk", "aa", "aa", "aa",a1);
        PersonRequest pr2 = new PersonRequest("Unik@mail.dk", "aa", "aa", "aa",a1);

        //Tests whether the correct exception is thrown when the email is not unique

        Exception exception = assertThrows(Exception.class, () -> {
            personService.addPerson(pr1);
        });

        String expectedMessage = "Provided email is taken";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


        //Tester om metoden virker med en unik email
        PersonResponse PRes = personService.addPerson(pr2);
        assertEquals("Unik@mail.dk", PRes.getEmail());
        assertNotEquals("Isabel@mail.dk", PRes.getEmail());

    }


    //Virker 23/3
    @Test
    void testGetPeople() {
        List<PersonResponse> people = personService.getPeople();
        assertEquals(2, people.size());
        assertInstanceOf(PersonResponse.class, people.get(0));
        assertEquals("Isabel@mail.dk", people.get(0).getEmail());
        assertNotEquals("Isabel@mail.dk", people.get(1).getEmail());
        assertThat(people, containsInAnyOrder(hasProperty("email", is("Isabel@mail.dk")),
                hasProperty("email", is("Andrea@mail.dk"))));
    }


    //Virker 22/3
    @Test
    void testGetPerson() throws Exception {
        PersonResponse testPerson = personService.getPerson(person1Id);
        assertEquals("Isabel@mail.dk", testPerson.getEmail());
        assertNotEquals("Andrea@mail.dk", testPerson.getEmail());
    }


    //Virker 22/3
    @Test
    void testUpdatePerson() throws Exception {
        Address a1 = new Address
                ("GadeVænget 1", "3. tv", "2200", "København");
        addressRepository.save(a1);
        addressOne= a1.getId();

        //Ændret person
        PersonRequest updatedPersonRequest= new PersonRequest
                ("ændret@mail.dk", "ændret", "ændret", "ændret",a1);

        //Metoden kaldes
        personService.updatePerson(updatedPersonRequest, person1Id);


        assertEquals("ændret@mail.dk", personService.getPerson(person1Id).getEmail());
        assertNotEquals("Isabel@mail.dk", personService.getPerson(person1Id).getEmail());



    }


    //Virker 23/3
    @Test
    void testDeletePerson() throws Exception {
        List<PersonResponse> people = personService.getPeople();
        int sizeBeforeDelete = people.size();
        //assertEquals(2, people.size());
        personService.deletePerson(person1Id);

        people = personService.getPeople();
        assertEquals(sizeBeforeDelete-1, people.size());

    }

}