package com.example.hobbiezz.service;

import com.example.hobbiezz.dto.HobbyResponse;
import com.example.hobbiezz.entity.Hobby;
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


/*
@DataJpaTest
class HobbyServiceTest {
    @Autowired
    HobbyRepository hobbyRepository;

    HobbyService hobbyService;

    @Autowired
    HobbyInfoRepository hobbyInfoRepository;


    @BeforeAll
    static void setup(@Autowired HobbyRepository hobbyRepository, @Autowired HobbyInfoRepository hobbyInfoRepository) {
        hobbyInfoRepository.deleteAll();
        hobbyRepository.deleteAll();
        Hobby hobbyOne = hobbyRepository.save(new Hobby("Name1", "Category1", "Link1.dk", "outside"));
        Hobby hobbyTwo = hobbyRepository.save(new Hobby("Name2", "Category2", "Link2.dk", "inside"));
    }


    @BeforeEach
    public void setupService() {
        hobbyService = new HobbyService(hobbyRepository);
    }

    //Virker 22/3
    @Test
    void testGetHobbies() {
        List<HobbyResponse> hobbies = hobbyService.getHobbies();
        assertEquals(2, hobbies.size());
        assertInstanceOf(HobbyResponse.class, hobbies.get(0));
        assertEquals("Name1", hobbies.get(0).getName());
        assertNotEquals("Name1", hobbies.get(1).getName());
        assertThat(hobbies, containsInAnyOrder(hasProperty("name", is("Name1")),
                hasProperty("name", is("Name2"))));
    }

    //Virker 22/3
    @Test
    void testGetHobbies2() {
        List<Hobby> hobbies = hobbyService.getHobbies2();
        assertEquals(2, hobbies.size());
        assertInstanceOf(Hobby.class, hobbies.get(0));
        assertEquals("Name1", hobbies.get(0).getName());
        assertNotEquals("Name1", hobbies.get(1).getName());
        assertThat(hobbies, containsInAnyOrder(hasProperty("name", is("Name1")),
                hasProperty("name", is("Name2"))));
    }


    //Virker 22/3
    @Test
    void getHobby() throws Exception {
        HobbyResponse testHobby = hobbyService.getHobby("Name1");
        assertEquals("Name1", testHobby.getName());
        assertNotEquals("Name2", testHobby.getName());
        assertEquals("Category1", testHobby.getCategory());
        assertNotEquals("Category2", testHobby.getCategory());
    }

}*/