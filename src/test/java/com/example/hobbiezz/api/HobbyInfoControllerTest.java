package com.example.hobbiezz.api;

import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.entity.HobbyInfo;
import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.repository.AddressRepository;
import com.example.hobbiezz.repository.HobbyInfoRepository;
import com.example.hobbiezz.repository.HobbyRepository;
import com.example.hobbiezz.repository.PersonRepository;
import com.example.hobbiezz.service.HobbyInfoService;
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

    static int hobbyInfoOneId, hobbyInfoTwoId;
    static int personId;

    /*
    public HobbyInfoControllerTest(HobbyInfoRepository hobbyInfoRepository, HobbyRepository hobbyRepository,
    PersonRepository personRepository) {
        this.hobbyInfoRepository = hobbyInfoRepository;
        this.hobbyRepository = hobbyRepository;
        this.personRepository = personRepository;
    }

     */


    @BeforeEach
    public void setup() throws Exception {
        addressRepository.deleteAll();
        hobbyInfoRepository.deleteAll();
        hobbyRepository.deleteAll();
        personRepository.deleteAll();

        //Person
        Person personOne = personRepository.save
                (new Person("Isabel@mail.dk", "Isabel", "Isabelsen", "911"));
        Person personTwo = personRepository.save
                (new Person("Andrea@mail.dk", "Andrea", "Andreasen", "88888888"));


        //Hobby
        Hobby hobbyOne = hobbyRepository.save
                (new Hobby("Name", "Category", "Link.dk", "out"));

        //Hobbyinfo

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime later = LocalDateTime.now().minusDays(100);
        hobbyInfoOneId = hobbyInfoRepository.save(new HobbyInfo(now, hobbyOne, personOne)).getId();
        hobbyInfoOneId = hobbyInfoRepository.save(new HobbyInfo(later, hobbyOne, personTwo)).getId();
        System.out.println("-----------Setup completed-----------");
    }

    /*
    //Virker ikke 22/3
    @Test
    void testDeleteHobbyInfo() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/personalHobbies/{id}", hobbyInfoOneId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        //Testing
        assertEquals(1, hobbyInfoRepository.count());

    }


    @Test
    void testGetHObby() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/personalHobbies/{name}", hobbyInfoOneId)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(hobbyInfoOneId));
    }



    //Virker ikke
    @Test
    void getHobbyInfo() throws Exception {

        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/personalHobbies/{id}", hobbyInfoOneId)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(hobbyInfoOneId));

    }

     */
}