package com.example.hobbiezz.configuration;

import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.entity.HobbyInfo;
import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.repository.HobbyInfoRepository;
import com.example.hobbiezz.repository.HobbyRepository;
import com.example.hobbiezz.repository.PersonRepository;
import com.example.hobbiezz.service.HobbyInfoService;
import com.example.hobbiezz.service.HobbyService;
import com.example.hobbiezz.service.PersonService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@Profile("!test")
public class MakeTestData implements ApplicationRunner {

    PersonRepository memberRepository;
    PersonService personService;
    HobbyInfoService hobbyInfoService;
    HobbyRepository hobbyRepository;
    HobbyService hobbyService;
    HobbyInfoRepository hobbyInfoRepository;

    public MakeTestData(PersonRepository memberRepository, PersonService personService,
                        HobbyInfoService hobbyInfoService, HobbyRepository hobbyRepository,
                        HobbyService hobbyService, HobbyInfoRepository hobbyInfoRepository) {
        this.memberRepository = memberRepository;
        this.personService = personService;
        this.hobbyInfoService = hobbyInfoService;
        this.hobbyRepository = hobbyRepository;
        this.hobbyService = hobbyService;
        this.hobbyInfoRepository = hobbyInfoRepository;
    }


    public void makeMembers(){
        Person m1 = memberRepository.save(new Person
                ("amanda@amanda.dk", "Amanda", "Amandasen", "70121416"));
        Person m2 = memberRepository.save(new Person
                ("casper@casper.co.uk", "Casper", "Caspersen", "88888888"));
        Person m3 = memberRepository.save(new Person
                ("test@test.com", "Test", "Test", "125"));

        /*
        Hobby h1 = hobbyRepo.save(new Hobby("test", "test", "test", "test"));

        hobbyInfoService.connectHobbyToPerson(m1, h1);

         */

        System.out.println("--------------makeMembers testdata kørt--------------");
    }

    public void makeHobbies(){
        Hobby h1 = hobbyRepository.save(new Hobby
                ("Fodbold", "fodbold.dk", "Kategori", "out"));

        /*
        Hobby h1 = hobbyRepo.save(new Hobby("test", "test", "test", "test"));

        hobbyInfoService.connectHobbyToPerson(m1, h1);

         */

        System.out.println("--------------makeHobbies testdata kørt--------------");
    }


    //Virker!!!
    public void makeHobbyInfos(){
        Hobby h1 = new Hobby
                ("Fodbold", "fodbold.dk", "Kategori", "out");
        hobbyRepository.save(h1);

        Person m1 = new Person
                ("amanda@amanda.dk", "Amanda", "Amandasen", "70121416");
        memberRepository.save(m1);

        HobbyInfo hi1 = new HobbyInfo(LocalDateTime.of(2022,03,01,9,23),h1,m1);

        hobbyInfoRepository.save(hi1);


        System.out.println("--------------makeHobbyInfos testdata kørt--------------");
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {

        /*makeMembers();

        makeHobbies();

        System.out.println(hobbyService.getHobbies2());
        System.out.println(hobbyService.getHobby("Fodbold"));
        */
        makeHobbyInfos();



    }
}
