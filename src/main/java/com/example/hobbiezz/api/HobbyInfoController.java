package com.example.hobbiezz.api;

import com.example.hobbiezz.entity.HobbyInfo;
import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.service.HobbyInfoService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/personalHobbies")
public class HobbyInfoController {

    HobbyInfoService hobbyInfoService;

    //Nedenstående skal ændres til, at indput er PersonRequest og HobbyRequest output er HobbyInfoRequest

    /*
    @PostMapping
    public HobbyInfo addHobbyInfo(@RequestBody Person person, Hobby hobby) throws Exception {
        return hobbyInfoService.connectHobbyToPerson(person, hobby);
    }

     */

    @DeleteMapping("/{id}")
    public void deleteHobbyInfo(@PathVariable int id) {
        hobbyInfoService.deleteHobbyInfo(id);
    }


/*
    HobbyInfoService;

    @GetMapping
    public List<HobbyInfoResponse> getHobbies() {
        return HobbyInfoService.;
    }

    @GetMapping("/{name}")
    public HobbyResponse getHObby(@PathVariable String name) throws Exception {
        return hobbyService.getHobby(name);
    }

 */
}
