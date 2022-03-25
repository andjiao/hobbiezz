package com.example.hobbiezz.api;

import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.repository.AddressRepository;
import com.example.hobbiezz.repository.HobbyInfoRepository;
import com.example.hobbiezz.repository.HobbyRepository;
import com.example.hobbiezz.repository.PersonRepository;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class HobbyControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Autowired
    HobbyRepository hobbyRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    HobbyInfoRepository hobbyInfoRepository;

    /*
    @Autowired
    PersonRepository personRepository;

    @Autowired
    HobbyInfoRepository hobbyInfoRepository;

     */


    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void setup() {
        hobbyInfoRepository.deleteAll();
        hobbyRepository.deleteAll();
        Hobby hobbyOne = hobbyRepository.save(new Hobby("Name1", "Category1", "Link1.dk", "outside"));
        Hobby hobbyTwo = hobbyRepository.save(new Hobby("Name2", "Category2", "Link2.dk", "inside"));

    }


    //Virker ikke 22/3
    @Test
    void testGetHobbies() throws Exception {
        String name = "$[?(@.name == '%s')]";
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/hobbies/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))

                //Testing
                .andExpect(MockMvcResultMatchers.jsonPath(name, "Name1").exists())
                .andExpect(MockMvcResultMatchers.jsonPath(name, "Name2").exists());

    }


    //Virker ikke 22/3
    @Test
    void testGetHobby() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/hobbies/{name}", "Name1")
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Name1"));
    }


    @Test
    void testGetPersonsHobbies() {

    }

}