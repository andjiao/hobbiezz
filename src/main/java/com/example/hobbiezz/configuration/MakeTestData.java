package com.example.hobbiezz.configuration;

import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.repository.PersonRepository;
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
    //HobbyRepo hobbyRepo;

    public MakeTestData(PersonRepository memberRepository) {
        this.memberRepository = memberRepository;
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

    @Override
    public void run(ApplicationArguments args) throws Exception {

        makeMembers();

    }
}
