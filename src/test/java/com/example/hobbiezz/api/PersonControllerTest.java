package com.example.hobbiezz.api;

import com.example.hobbiezz.dto.PersonRequest;
import com.example.hobbiezz.entity.Person;
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

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    private ObjectMapper objectMapper;

    static int personOneId, personTwoId;

    @BeforeEach
    public void setup() {
        personRepository.deleteAll();
        personOneId = personRepository.save(new Person("Isabel@mail.dk", "Isabel", "Isabelsen", "911")).getId();
        personTwoId = personRepository.save(new Person("Andrea@mail.dk", "Andrea", "Andreasen", "88888888")).getId();

    }


    //Virker 22/3
        @Test
    void testAddPerson() throws Exception {
            PersonRequest newPerson = new PersonRequest("Tilde@mail.dk", "Tilde", "Tildesen", "528");
            mockMvc.perform(MockMvcRequestBuilders.post("/api/krak")
                            .contentType("application/json")
                            .accept("application/json")
                            .content(objectMapper.writeValueAsString(newPerson)))
                            .andDo(print())
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

            //Testing
            assertEquals(3, personRepository.count());

    }


    //Inspiration: https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/
    //Virker 22/3
    @Test
    public void testUpdatePerson() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .put("/api/krak/{id}", 1)
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
                        .get("/api/krak/people")
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
                        .get("/api/krak/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));

    }


    //Virker 22/3
    @Test
    void deleteMember() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/krak/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        //Testing
        assertEquals(1, personRepository.count());

    }
}