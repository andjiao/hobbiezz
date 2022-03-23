package com.example.hobbiezz.configuration;

import com.example.hobbiezz.dto.AddressRequest;
import com.example.hobbiezz.dto.AddressResponse;
import com.example.hobbiezz.dto.PersonResponse;
import com.example.hobbiezz.entity.Address;
import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.entity.HobbyInfo;
import com.example.hobbiezz.entity.Person;
import com.example.hobbiezz.repository.AddressRepository;
import com.example.hobbiezz.repository.HobbyInfoRepository;
import com.example.hobbiezz.repository.HobbyRepository;
import com.example.hobbiezz.repository.PersonRepository;
import com.example.hobbiezz.service.AddressService;
import com.example.hobbiezz.service.HobbyInfoService;
import com.example.hobbiezz.service.HobbyService;
import com.example.hobbiezz.service.PersonService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Profile("!test")
public class MakeTestData implements ApplicationRunner {

    PersonRepository memberRepository;
    PersonService personService;
    HobbyInfoService hobbyInfoService;
    HobbyRepository hobbyRepository;
    HobbyService hobbyService;
    HobbyInfoRepository hobbyInfoRepository;
    AddressRepository addressRepository;
    AddressService addressService;


    public MakeTestData(PersonRepository memberRepository, PersonService personService,
                        HobbyInfoService hobbyInfoService, HobbyRepository hobbyRepository,
                        HobbyService hobbyService, HobbyInfoRepository hobbyInfoRepository, AddressRepository addressRepository, AddressService addressService) {
        this.memberRepository = memberRepository;
        this.personService = personService;
        this.hobbyInfoService = hobbyInfoService;
        this.hobbyRepository = hobbyRepository;
        this.hobbyService = hobbyService;
        this.hobbyInfoRepository = hobbyInfoRepository;

        this.addressService=addressService;
        this.addressRepository=addressRepository;
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
                ("amanda2@amanda.dk", "Amanda", "Amandasen", "70121416");
        memberRepository.save(m1);

        HobbyInfo hi1 = new HobbyInfo(LocalDateTime.of(2022,03,01,9,23),h1,m1);

        hobbyInfoRepository.save(hi1);


        System.out.println("--------------makeHobbyInfos testdata kørt--------------");
    }

    public void makeAddress() throws Exception {
        //Ja den gemmer data
        Address a1 = new Address("Amagergaade", "23,3 th", "2300","København");
        addressRepository.save(a1);

        //ja, den kan finde en address ud fra dets id
        AddressResponse ar = addressService.getAddressById(1);
        System.out.println(ar);

        System.out.println("--------------makeAddress testdata kørt--------------");
    }

    public void getPerson() throws Exception {
        Person m1 = new Person
                ("amanda@amanda.dk", "Amanda", "Amandasen", "70121416");
        memberRepository.save(m1);

        PersonResponse p1 = personService.getPerson(1);

        System.out.println(p1);

        System.out.println("--------------getPerson testdata kørt--------------");
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {

        makeMembers();

        makeHobbies();

        System.out.println(hobbyService.getHobbies2());
        System.out.println(hobbyService.getHobby("Fodbold"));
 makeTestData_feature

        makeHobbyInfos();

        */
        //makeHobbyInfos();

        //makeAddress();

        getPerson();


 main



    }
}
