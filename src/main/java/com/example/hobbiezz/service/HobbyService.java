package com.example.hobbiezz.service;



import com.example.hobbiezz.dto.HobbyResponse;
import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.repository.HobbyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbyService {

    HobbyRepository hobbyRepo;
    HobbyResponse hobbyResponse;

    public HobbyService (HobbyRepository hobbyRepo){
        this.hobbyRepo = hobbyRepo;
    }

    public List<HobbyResponse> getHobbies(){
        List<Hobby> hobbies= hobbyRepo.findAll();
        return hobbyResponse.getHobbiesEntities(hobbies);
    }

    public HobbyResponse getHobby(String name) throws Exception{
        Hobby hobby= hobbyRepo.findById(name).orElseThrow(()-> new Exception("do not exist"));

        return new HobbyResponse(hobby);
    }

}
