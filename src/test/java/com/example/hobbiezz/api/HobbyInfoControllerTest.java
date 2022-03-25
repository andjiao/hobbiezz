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
import com.example.hobbiezz.service.HobbyInfoService;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.BDDAssertions.and;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class HobbyInfoControllerTest {

    //Testene viser, at metoderne ikke virker, 22/3

    @Autowired
    MockMvc mockMvc;


    @Autowired
    HobbyInfoRepository hobbyInfoRepository;

    @Autowired
    HobbyRepository hobbyRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressRepository addressRepository;

    HobbyInfoService hobbyInfoService;


    @Autowired
    private ObjectMapper objectMapper;

    static int hobbyInfoOneId, hobbyInfoTwoId, personId;
    static Person p1, p2;
    static Hobby h1, h2;

/*
    public HobbyInfoControllerTest(HobbyInfoRepository hobbyInfoRepository, HobbyRepository hobbyRepository,
    PersonRepository personRepository) {
        this.hobbyInfoRepository = hobbyInfoRepository;
        this.hobbyRepository = hobbyRepository;
        this.personRepository = personRepository;
    }

 */




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
        p1 = personRepository.save
                (new Person("Isabel@mail.dk", "Isabel", "Isabelsen", "911", a1));
        personId = p1.getId();

        p2 = personRepository.save
                (new Person("Andrea@mail.dk", "Andrea", "Andreasen", "88888888", a2));


        //Makehobbies

        h1 = hobbyRepository.save(new Hobby
                ("Fodbold", "category", "fodbold.dk", "out"));

        h2 = hobbyRepository.save(new Hobby
                ("Håndbold", "category", "håndbold.dk", "out"));

        Hobby h3 = hobbyRepository.save(new Hobby
                ("LARP", "category", "LARP.dk", "out"));

        Hobby h4 = hobbyRepository.save(new Hobby
                ("Strikning", "category", "strik.dk", "out"));

        Hobby h5 = hobbyRepository.save(new Hobby
                ("Kattepasning", "category", "kat.dk", "out"));


        //MakeHobbyInfos
        HobbyInfo hi1 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022,03,01,9,23),h1,p1));
        hobbyInfoOneId = hi1.getId();

        HobbyInfo hi2 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022,03,02,9,23),h5,p1));
        hobbyInfoTwoId = hi2.getId();

        HobbyInfo hi3 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022,03,03,9,23),h3,p2));

        HobbyInfo hi4 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022,03,04,9,23),h1,p2));

        HobbyInfo hi5 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022,03,05,9,23),h4,p1));

    }


    //Virker ikke 22/3
    @Test
    void testDeleteHobbyInfo() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/personalHobbies/{id}", hobbyInfoOneId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        //Testing
        assertEquals(4, hobbyInfoRepository.count());

    }



/*
    @Test
    void testGetHObby() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/personalHobbies/{name}", hobbyInfoOneId)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(hobbyInfoOneId));
    }
*/


    //Virker 25/3
    @Test
    void testGetHobbyInfo() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/personalHobbies/{id}", hobbyInfoOneId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(hobbyInfoOneId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.personId").value(p1.getId()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.hobbyName").value(h1.getName()));

    }

//Jeg ved ikke, hvordan man indsætter flere parametre
    @Test
    void addHobbyInfo() throws Exception {
       mockMvc.perform(MockMvcRequestBuilders.post("/api/personalHobbies")
                        .contentType("application/json")
                        .accept("application/json")
                        .content(objectMapper.writeValueAsString(p1))
                       .content(objectMapper.writeValueAsString(h2)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hasHobbies").value(p2))
               .andExpect(MockMvcResultMatchers.jsonPath("$.hobbyObject").value(h2));


        //Testing
        assertEquals(6, hobbyInfoRepository.count());
    }

}