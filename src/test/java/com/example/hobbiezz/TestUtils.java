package com.example.hobbiezz;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.hobbiezz.entity.BaseUser;
import com.example.hobbiezz.entity.Role;
import com.example.hobbiezz.security.UserDetailsImp;
import com.example.hobbiezz.security.dto.LoginRequest;
import com.example.hobbiezz.security.dto.LoginResponse;
import com.example.hobbiezz.security.jwt.JwtTokenUtil;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class TestUtils {
    public static String login(String username, String pw, MockMvc mockMvc) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        MvcResult response = mockMvc.perform(post("/api/auth/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(new LoginRequest(username, pw))))
                .andReturn();
        return objectMapper.readValue(response.getResponse().getContentAsString(), LoginResponse.class).getToken();
    }
}
