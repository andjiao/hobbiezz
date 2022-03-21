package com.example.hobbiezz.configuration;

import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.repository.HobbyRepository;
import com.example.hobbiezz.repository.PersonRepository;
import com.example.hobbiezz.service.HobbyService;
import com.example.hobbiezz.service.PersonService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

@Controller
@Profile("!test")
public class MakeTestData implements ApplicationRunner {

    PersonRepository memberRepository;
    PersonService personService;
    //HobbyInfoService hobbyInfoService;
    HobbyRepository hobbyRepository;
    HobbyService hobbyService;

    public MakeTestData(PersonRepository memberRepository, PersonService personService, HobbyRepository hobbyRepository, HobbyService hobbyService) {
        this.memberRepository = memberRepository;
        this.personService = personService;
        this.hobbyRepository = hobbyRepository;
        this.hobbyService = hobbyService;
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

        System.out.println("--------------Testdata kørt--------------");
    }

    public void makeHobbies(){
        Hobby h1 = hobbyRepository.save(new Hobby
                ("Fodbold", "fodbold.dk", "Kategori", "out"));

        /*
        Hobby h1 = hobbyRepo.save(new Hobby("test", "test", "test", "test"));

        hobbyInfoService.connectHobbyToPerson(m1, h1);

         */

        System.out.println("--------------Testdata kørt--------------");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        makeMembers();

        makeHobbies();
        System.out.println(hobbyService.getHobbies2());
        System.out.println(hobbyService.getHobby("Fodbold"));

    }
}
