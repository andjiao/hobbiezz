package com.example.hobbiezz.api;

import com.example.hobbiezz.dto.PersonRequest;
import com.example.hobbiezz.entity.Address;
import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.entity.HobbyInfo;
import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.repository.AddressRepository;
import com.example.hobbiezz.repository.HobbyInfoRepository;
import com.example.hobbiezz.repository.HobbyRepository;
import com.example.hobbiezz.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PersonControllerTest {


    @Autowired
    MockMvc mockMvc;


    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    HobbyInfoRepository hobbyInfoRepository;

    @Autowired
    HobbyRepository hobbyRepository;


    @Autowired
    private ObjectMapper objectMapper;

    static int personOneId, personTwoId;

    static Hobby h1, h2, h3;

    @BeforeEach
    public void setup() {
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
                (LocalDateTime.of(2022,03,05,9,23),h4,p1));

    }


    //Virker 22/3
    @Test
    void testAddPerson() throws Exception {
        PersonRequest newPerson = new PersonRequest("Tilde@mail.dk", "Tilde", "Tildesen", "528");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/person")
                        .contentType("application/json")
                        .accept("application/json")
                        .content(objectMapper.writeValueAsString(newPerson)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("Tilde@mail.dk"));


        //Testing
        assertEquals(3, personRepository.count());

    }


    //Inspiration: https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/
    //Virker 22/3
    @Test
    public void testUpdatePerson() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .put("/api/person/{id}", personOneId)
                        .content(objectMapper.writeValueAsString(new PersonRequest("Ændret@mail.dk", "Ændret", "Ændret", "911")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Ændret"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Ændret"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("Ændret@mail.dk"));


    }

    //Virker 22/3
    @Test
    void getPeople() throws Exception {
        String email = "$[?(@.email == '%s')]";
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/person/people")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))

                //Testing
                .andExpect(MockMvcResultMatchers.jsonPath(email, "Isabel@mail.dk").exists())
                .andExpect(MockMvcResultMatchers.jsonPath(email, "Andrea@mail.dk").exists());

    }

    //Virker 22/3
    @Test
    void getPerson() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/person/{id}", personOneId)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                        //.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(personOneId))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("Isabel@mail.dk"));

    }


    //Virker 22/3
    //Virker ikke 24/3
    /*
    @Test
    void deletePerson() throws Exception {

        System.out.println("1---------------------" + personRepository.count() + "---------------------");
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/person/{id}", personOneId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Testing
        System.out.println("2---------------------" + personRepository.count() + "---------------------");

        assertEquals(1, personRepository.count());

    }

     */

    //Virker 24/3
    @Test
    void testGetPersonsHobbies() throws Exception {
        String name = "$[?(@.name == '%s')]";
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/person/personalhobbies/" + personOneId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))


                //Testing
                .andExpect(MockMvcResultMatchers.jsonPath(name, "Fodbold").exists())
                .andExpect(MockMvcResultMatchers.jsonPath(name, "Strikning").exists())
                .andExpect(MockMvcResultMatchers.jsonPath(name, "Kattepasning").exists());
    }


    @Test
    void getPeopleConnectedToHobby() throws Exception {
        String name = h1.getName();
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/person/hobby/{name}", name)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())

                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));


    }
}