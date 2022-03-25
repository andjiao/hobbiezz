package com.example.hobbiezz.repository;

import com.example.hobbiezz.entity.Address;
import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.entity.HobbyInfo;
import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.service.HobbyInfoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class HobbyRepositoryTest {

    @Autowired
    HobbyInfoRepository hobbyInfoRepository;

    @Autowired
    HobbyRepository hobbyRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressRepository addressRepository;


    static int hobbyInfoOneId, hobbyInfoTwoId, person1Id, person2Id;
    static String hobby2Name;
    static Person p1, p2;
    static Hobby h1, h2;
    static HobbyInfo hi1;

    //Store som id's for the test methods
    //static int person1Id, person2Id;

    @BeforeAll
    static void setUp(@Autowired PersonRepository personRepository, @Autowired HobbyRepository hobbyRepository,
                      @Autowired AddressRepository addressRepository, @Autowired HobbyInfoRepository hobbyInfoRepository) {
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
        p1 = personRepository.save
                (new Person("Isabel@mail.dk", "Isabel", "Isabelsen", "911", a1));
        person1Id = p1.getId();

        p2 = personRepository.save
                (new Person("Andrea@mail.dk", "Andrea", "Andreasen", "88888888", a2));
        person2Id = p2.getId();


        //Makehobbies

        h1 = hobbyRepository.save(new Hobby
                ("Fodbold", "category", "fodbold.dk", "out"));

        h2 = hobbyRepository.save(new Hobby
                ("Håndbold", "category", "håndbold.dk", "out"));

        hobby2Name = h2.getName();


        Hobby h3 = hobbyRepository.save(new Hobby
                ("LARP", "category", "LARP.dk", "out"));

        Hobby h4 = hobbyRepository.save(new Hobby
                ("Strikning", "category", "strik.dk", "out"));

        Hobby h5 = hobbyRepository.save(new Hobby
                ("Kattepasning", "category", "kat.dk", "out"));


        //MakeHobbyInfos
        hi1 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022, 03, 01, 9, 23), h1, p1));
        hobbyInfoOneId = hi1.getId();

        HobbyInfo hi2 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022, 03, 02, 9, 23), h5, p1));
        hobbyInfoTwoId = hi2.getId();

        HobbyInfo hi3 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022, 03, 03, 9, 23), h3, p2));

        HobbyInfo hi4 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022, 03, 04, 9, 23), h1, p2));

        HobbyInfo hi5 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022, 03, 05, 9, 23), h4, p1));
    }

    @Test
    void findHobbyByHobbyInfos() {
        Hobby testHobby = hobbyRepository.findHobbyByHobbyInfos(hi1);
        assertEquals(testHobby, h1);
    }
}