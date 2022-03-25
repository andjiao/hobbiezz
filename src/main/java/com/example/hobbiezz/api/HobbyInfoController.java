package com.example.hobbiezz.api;

import com.example.hobbiezz.dto.HobbyInfoResponse;
import com.example.hobbiezz.dto.HobbyResponse;
import com.example.hobbiezz.dto.PersonResponse;
import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.entity.HobbyInfo;
import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.service.HobbyInfoService;
import com.example.hobbiezz.service.HobbyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/personalHobbies")
public class HobbyInfoController {

    HobbyInfoService hobbyInfoService;
    HobbyService hobbyService;

    //Nedenstående skal ændres til, at indput er PersonRequest og HobbyRequest output er HobbyInfoRequest
    @PostMapping
    public HobbyInfo addHobbyInfo(@RequestBody Person person, Hobby hobby) throws Exception {
        return hobbyInfoService.connectHobbyToPerson(hobby, person);
    }


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

    /* Denne metode giver ingen mening, skal den være i HobbyController? - Amanda
    @GetMapping("/{name}")
    public HobbyResponse getHObby(@PathVariable String name) throws Exception {
        return hobbyService.getHobby(name);
    }

     */

    @GetMapping("/{id}")
    public HobbyInfoResponse getHobbyInfo (@PathVariable int id) throws Exception {
        return hobbyInfoService.getHobbyInfo(id);
    }

}
