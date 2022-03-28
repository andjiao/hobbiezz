package com.example.hobbiezz.service;

import com.example.hobbiezz.dto.PersonRequest;
import com.example.hobbiezz.dto.PersonResponse;
import com.example.hobbiezz.entity.Address;
import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.entity.HobbyInfo;
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

import java.time.LocalDateTime;
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

    static int person1Id, person2Id, hobbyInfoId;

    @BeforeAll
    static void setup(@Autowired PersonRepository personRepository, @Autowired AddressRepository addressRepository,
                      @Autowired HobbyInfoRepository hobbyInfoRepository, @Autowired HobbyRepository hobbyRepository) {
        hobbyRepository.deleteAll();
        personRepository.deleteAll();
        addressRepository.deleteAll();
        hobbyInfoRepository.deleteAll();

        //MakeAdresses
        Address a1 = new Address
                ("GadeVænget 1", "3. tv", "2200", "København");
        addressRepository.save(a1);

        Address a2 = new Address
                ("GadeVænget 2", "2. tv", "2200", "København");
        addressRepository.save(a2);



        //MakePeople
        Person p1 = personRepository.save
                (new Person("Isabel@mail.dk", "Isabel", "Isabelsen", "911", a1));
        personOneId = p1.getId();

        Person p2 = personRepository.save
                (new Person("Andrea@mail.dk", "Andrea", "Andreasen", "88888888", a2));
        personTwoId = p2.getId();


        //Makehobbies

        h1 = hobbyRepository.save(new Hobby
                ("Fodbold", "category", "fodbold.dk", "out"));

        h2 = hobbyRepository.save(new Hobby
                ("Håndbold", "category", "håndbold.dk", "out"));

        h3 = hobbyRepository.save(new Hobby
                ("LARP", "category", "LARP.dk", "out"));

        Hobby h4 = hobbyRepository.save(new Hobby
                ("Strikning", "category", "strik.dk", "out"));

        Hobby h5 = hobbyRepository.save(new Hobby
                ("Kattepasning", "category", "kat.dk", "out"));


        //MakeHobbyInfos
        HobbyInfo hi1 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022,03,01,9,23),h1,p1));

        HobbyInfo hi2 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022,03,02,9,23),h5,p1));

        HobbyInfo hi3 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022,03,03,9,23),h3,p2));

        HobbyInfo hi4 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022,03,04,9,23),h1,p2));

        HobbyInfo hi5 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022,03,05,9,23),h4,p1)); }


    @BeforeEach
    public void setupService() {
        personService = new PersonService(personRepository, hobbyRepository, hobbyInfoRepository);
    }

    //Virker 22/3
    @Test
    void addPerson() throws Exception {
        PersonRequest pr1 = new PersonRequest("Isabel@mail.dk", "aa", "aa", "aa");
        PersonRequest pr2 = new PersonRequest("Unik@mail.dk", "aa", "aa", "aa");

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
        //Ændret person
        PersonRequest updatedPersonRequest= new PersonRequest
                ("ændret@mail.dk", "ændret", "ændret", "ændret");

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

    @Test
    void deleteHobbiesConnectedToPerson() {


    }

    }
