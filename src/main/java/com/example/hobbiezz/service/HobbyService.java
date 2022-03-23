package com.example.hobbiezz.service;



import com.example.hobbiezz.dto.HobbyResponse;
import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.error.Client4xxException;
import com.example.hobbiezz.repository.HobbyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbyService {

    HobbyRepository hobbyRepository;
    HobbyResponse hobbyResponse;

    public HobbyService (HobbyRepository hobbyRepo){
        this.hobbyRepository = hobbyRepo;
    }

    public List<HobbyResponse> getHobbies(){
        List<Hobby> hobbies= hobbyRepository.findAll();
        return hobbyResponse.getHobbiesEntities(hobbies);
    }

    public List<Hobby> getHobbies2(){
        List<Hobby> hobbies= hobbyRepository.findAll();
        return hobbies;
    }


    public HobbyResponse getHobby(String name){
        Hobby hobby= hobbyRepository.findById(name).orElseThrow(()-> new Client4xxException("do not exist"));

        return new HobbyResponse(hobby);
    }

}
