package com.example.hobbiezz.api;

import com.example.hobbiezz.dto.HobbyResponse;
import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.entity.HobbyInfo;
import com.example.hobbiezz.repository.HobbyRepository;
import com.example.hobbiezz.service.HobbyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping("api/hobbies")
public class HobbyController {

    HobbyService hobbyService;
    HobbyRepository hobbyRepository;

    //Metoden virker for sig selv, men controlleren virker ikke 21/3
    @GetMapping("/all")
    public List<HobbyResponse> getHobbies (){
        return hobbyService.getHobbies();
    }

    //Metoden virker for sig selv, men controlleren virker ikke 21/3
    @GetMapping("/{name}")
    public HobbyResponse getHobby(@PathVariable String name) throws Exception{
        return hobbyService.getHobby(name);
    }

    //Dette endpoint returnerer den hobby, der er tilknyttet en hobbInfo. Skal nok ikke bruges, men demonstrerer metoden.
    @GetMapping
    public Hobby getPersonsHobbies (@PathVariable HobbyInfo hobbyInfo){
        return hobbyRepository.findHobbyByHobbyInfos(hobbyInfo);
    }

}
