package com.example.hobbiezz.api;

import com.example.hobbiezz.dto.HobbyInfoRequest;
import com.example.hobbiezz.dto.HobbyInfoResponse;
import com.example.hobbiezz.dto.HobbyResponse;
import com.example.hobbiezz.dto.PersonResponse;
import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.entity.HobbyInfo;
import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.repository.HobbyRepository;
import com.example.hobbiezz.service.HobbyInfoService;
import com.example.hobbiezz.service.HobbyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController

@RequestMapping("api/personalHobbies")
public class HobbyInfoController {

    HobbyInfoService hobbyInfoService;
    HobbyService hobbyService;

    public HobbyInfoController(HobbyInfoService hobbyInfoService, HobbyService hobbyService) {
        this.hobbyInfoService = hobbyInfoService;
        this.hobbyService = hobbyService;
    }


    //Den virker 25/3
    @PutMapping("/{personId}/{hobbyName}")
    public HobbyInfoResponse addHobbyInfo(@PathVariable int personId, @PathVariable String hobbyName) throws Exception {
        HobbyInfoResponse info = hobbyInfoService.connectHobbyToPerson(hobbyName, personId);

        return info;
    }


    //Virker ikke 25/3
    @DeleteMapping("/{id}")
    public void deleteHobbyInfo(@PathVariable int id) {
        hobbyInfoService.deleteHobbyInfo(id);
    }

/*
    @GetMapping
    public List<HobbyInfoResponse> getHobbies() {
        return HobbyInfoService.;
    }

 */

    @GetMapping("/{id}")
    public HobbyInfoResponse getHobbyInfo (@PathVariable int id) throws Exception {
        return hobbyInfoService.getHobbyInfo(id);
    }

}
