package com.example.hobbiezz.api;

import com.example.hobbiezz.dto.HobbyResponse;
import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.entity.HobbyInfo;
import com.example.hobbiezz.repository.HobbyRepository;
import com.example.hobbiezz.service.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController

@RequestMapping("api/hobbies")
public class HobbyController {

    HobbyService hobbyService;
    HobbyRepository hobbyRepository;

    public HobbyController(HobbyService hobbyService, HobbyRepository hobbyRepository) {
        this.hobbyService = hobbyService;
        this.hobbyRepository = hobbyRepository;
    }

    //Metoden virker for sig selv, men controlleren virker ikke 21/3
    @GetMapping("/all")
    public List<HobbyResponse> getHobbies (){
        return hobbyService.getHobbies();
    }

    //Metoden virker for sig selv, men controlleren virker ikke 21/3
    @GetMapping("/{name}")
    public HobbyResponse getHobby(@PathVariable String name){
        return hobbyService.getHobby(name);
    }

    //Dette endpoint returnerer den hobby, der er tilknyttet en hobbInfo. Skal nok ikke bruges, men demonstrerer metoden.
    @GetMapping
    public Hobby getPersonsHobbies (@PathVariable HobbyInfo hobbyInfo){
        return hobbyRepository.findHobbyByHobbyInfos(hobbyInfo);
    }

}
