package com.example.hobbiezz.service;

import com.example.hobbiezz.dto.HobbyInfoResponse;
import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.entity.HobbyInfo;
import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.error.Client4xxException;
import com.example.hobbiezz.repository.HobbyInfoRepository;
import com.example.hobbiezz.repository.HobbyRepository;
import com.example.hobbiezz.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HobbyInfoService {

    HobbyInfoRepository hobbyInfoRepo;
    HobbyRepository hobbyRepository;
    PersonRepository personRepository;
    Person person;

    public HobbyInfoService(HobbyInfoRepository hobbyInfoRepo, HobbyRepository hobbyRepository,
                            PersonRepository personRepository) {
        this.hobbyInfoRepo = hobbyInfoRepo;
        this.hobbyRepository = hobbyRepository;
        this.personRepository = personRepository;
    }


    /*
    public List<HobbyInfo> getPersonalHobbyList(int id){
        personRepository.getById(id).get;


        person.getEmail()

        return hobbyInfoRepo.findAll();
    }

     */


    public HobbyInfoResponse getHobbyInfo(int id) {
        return new HobbyInfoResponse(hobbyInfoRepo.findById(id).orElseThrow(()-> new Client4xxException("Could not find Hobby")));
    }


    //Denne metode opretter en ny HobbyInfo, der forbinder en hobby med en person.
    public HobbyInfoResponse connectHobbyToPerson(String hobbyName, int personId){
        Hobby hobby = hobbyRepository.getById(hobbyName);
        Person person = personRepository.getById(personId);
        HobbyInfo newHobbyInfo = hobbyInfoRepo.save(new HobbyInfo(hobby, person));

        return new HobbyInfoResponse(newHobbyInfo);
    }



    //
    public void getPersonsHobbies(Person person){

    }

    public List<HobbyInfoResponse> findHobbyInfosConnectedToHobby(Hobby hobby){
        List<HobbyInfo> hobbyInfos = hobbyInfoRepo.findHobbyInfosByHobbyObject(hobby);

        List<HobbyInfoResponse> hobbyInfoResponses = new ArrayList<>();

        for (HobbyInfo hobbyInfo : hobbyInfos) {
            hobbyInfoResponses.add(new HobbyInfoResponse(hobbyInfo));
        }

        return hobbyInfoResponses;
    }


    public void deleteHobbyInfo(int id){
        hobbyInfoRepo.deleteById(id);
    }


    /*En liste skal være tilknyttet en bestemt mail
    Ved at have indtastet/opgivet en mail er det muligt at få vist/oprette en hobbyliste
    samtidig skal det være muligt at redigere i denne liste, altså tilføje/fjerne hobbyer*/

}
