package com.example.hobbiezz.service;

import com.example.hobbiezz.dto.HobbyInfoResponse;
import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.entity.HobbyInfo;
import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.repository.HobbyInfoRepository;
import com.example.hobbiezz.repository.HobbyRepository;
import com.example.hobbiezz.repository.PersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class HobbyInfoServiceTest {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    HobbyRepository hobbyRepository;

    @Autowired
    HobbyInfoRepository hobbyInfoRepository;

    HobbyInfoService hobbyInfoService;

    static int hobbyInfoOneId, hobbyInfoTwoId;

    static LocalDateTime now = LocalDateTime.now();
    static LocalDateTime later = LocalDateTime.now().minusDays(100);

    @BeforeAll
    static void setup(@Autowired PersonRepository personRepository, @Autowired HobbyRepository hobbyRepository,
                      @Autowired HobbyInfoRepository hobbyInfoRepository) {
        //Person
        personRepository.deleteAll();
        Person personOne = personRepository.save(new Person("Isabel@mail.dk", "Isabel", "Isabelsen", "911"));
        Person personTwo = personRepository.save(new Person("Andrea@mail.dk", "Andrea", "Andreasen", "88888888"));


        //Hobby
        hobbyRepository.deleteAll();
        Hobby hobbyOne = hobbyRepository.save(new Hobby("Name", "Category", "Link.dk", "out"));

        //Hobbyinfo
        hobbyInfoRepository.deleteAll();

        hobbyInfoOneId = hobbyInfoRepository.save(new HobbyInfo(now, hobbyOne, personOne)).getId();
        hobbyInfoTwoId = hobbyInfoRepository.save(new HobbyInfo(later, hobbyOne, personTwo)).getId();
        System.out.println("-----------Setup completed-----------");
    }


    @BeforeEach
    public void setupService() {
        hobbyInfoService = new HobbyInfoService(hobbyInfoRepository, personRepository);
    }

    //Virker 23/3
    @Test
    void getHobbyInfo() throws Exception {
        HobbyInfoResponse testHobbyInfo = hobbyInfoService.getHobbyInfo(hobbyInfoOneId);
        assertEquals(hobbyInfoOneId, testHobbyInfo.getId());
        assertNotEquals(hobbyInfoTwoId, testHobbyInfo.getId());

        //Tests whether the correct exception is thrown when the id doesn't exist
        Exception exception = assertThrows(Exception.class, () -> {
            hobbyInfoService.getHobbyInfo(3);
        });

        String expectedMessage = "Could not find Hobby";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    /* Id skal ikke gættes
    //Virker 23/3
    @Test
    void connectHobbyToPerson() {
        Hobby hobbyTwo = hobbyRepository.save(new Hobby("Name2", "Category2", "Link2.dk", "inside"));
        Person personThree = personRepository.save(new Person("Karl@mail.dk", "Karl", "Karlsen", "88899888"));

        HobbyInfo newHobbyInfo = hobbyInfoService.connectHobbyToPerson(hobbyTwo, personThree);

        assertEquals(3, newHobbyInfo.getId());
        assertNotEquals(1, newHobbyInfo.getId());

        assertEquals(personThree, newHobbyInfo.getHasHobbies());

        assertEquals(hobbyTwo, newHobbyInfo.getHobbyObject());

    }

     */


    //Metoden er ikke færdig
    @Test
    void getPersonsHobbies() {
    }

    //Virker 23/3
    @Test
    void deleteHobbyInfo() throws Exception {

        //Før hobbyInfo slettes
        HobbyInfoResponse testHobbyInfo = hobbyInfoService.getHobbyInfo(hobbyInfoOneId);

        //HobbyInfo slettes
        hobbyInfoService.deleteHobbyInfo(hobbyInfoOneId);

        //Efter hobbyInfo slettes
        Exception exception = assertThrows(Exception.class, () -> {
            hobbyInfoService.getHobbyInfo(hobbyInfoOneId);
        });

        String expectedMessage = "Could not find Hobby";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}