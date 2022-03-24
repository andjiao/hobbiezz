package com.example.hobbiezz.service;

import com.example.hobbiezz.dto.HobbyInfoResponse;
import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.entity.HobbyInfo;
import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.error.Client4xxException;
import com.example.hobbiezz.repository.HobbyInfoRepository;
import com.example.hobbiezz.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HobbyInfoService {

    private static HobbyInfoRepository hobbyInfoRepo;

    HobbyInfoRepository hobbyInfoRepository;
    //HobbyRepo hobbyRepo;
    PersonRepository personRepository;
    Person person;
    private Object Hobby;

    public HobbyInfoService(HobbyInfoRepository hobbyInfoRepo, PersonRepository personRepository){
        this.hobbyInfoRepo= hobbyInfoRepo;
        this.personRepository=personRepository;
    }

   /* public List<Hobby> getPersonalHobbyList(int id){

        List<HobbyInfo> hobbyInfos = hobbyInfoRepo.findHobbyInfosByHasHobbies_Id(id);

        Hobby hobby = new Hobby();

        while(hobbyInfos.contains(hobby))
        {
            HobbyInfo hobbyInfo = new HobbyInfo();

            hobbyInfos.contains(hobby.getName());
            hobbyInfos.contains(hobby.getCategory());
            hobbyInfos.contains(hobby.getLink());
            hobbyInfos.contains(hobby.getInOut());

            hobbyInfos.add(hobbyInfo);
        }

        return hobbyInfoRepo.save(new HobbyInfo(hobby));

    }*/

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
    public HobbyInfo connectHobbyToPerson(Hobby hobby, Person person){
        HobbyInfo newHobbyInfo = hobbyInfoRepo.save(new HobbyInfo(hobby, person));

        return newHobbyInfo;
    }



    //
    public void getPersonsHobbies(Person person){

    }

    public void deleteHobbyInfo(int id){
        hobbyInfoRepo.deleteById(id);
    }


    /*En liste skal være tilknyttet en bestemt mail
    Ved at have indtastet/opgivet en mail er det muligt at få vist/oprette en hobbyliste
    samtidig skal det være muligt at redigere i denne liste, altså tilføje/fjerne hobbyer*/

}
