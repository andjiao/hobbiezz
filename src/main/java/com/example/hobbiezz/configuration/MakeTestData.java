package com.example.hobbiezz.configuration;

import com.example.hobbiezz.entity.*;
import com.example.hobbiezz.repository.*;
import com.example.hobbiezz.security.UserRepository;
import com.example.hobbiezz.service.AddressService;
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

    PersonRepository personRepository;
    PersonService personService;
    HobbyInfoService hobbyInfoService;
    HobbyRepository hobbyRepository;
    HobbyService hobbyService;
    HobbyInfoRepository hobbyInfoRepository;
    AddressRepository addressRepository;
    AddressService addressService;
    UserRepository userRepository;

    public MakeTestData(PersonRepository personRepository, PersonService personService, HobbyInfoService hobbyInfoService, HobbyRepository hobbyRepository, HobbyService hobbyService, HobbyInfoRepository hobbyInfoRepository, AddressRepository addressRepository, AddressService addressService, UserRepository userRepository) {
        this.personRepository = personRepository;
        this.personService = personService;
        this.hobbyInfoService = hobbyInfoService;
        this.hobbyRepository = hobbyRepository;
        this.hobbyService = hobbyService;
        this.hobbyInfoRepository = hobbyInfoRepository;
        this.addressRepository = addressRepository;
        this.addressService = addressService;
        this.userRepository = userRepository;
    }


    public void makeTestData(){

        //MakeAdresses
        Address a1 = new Address
                ("GadeVænget 1", "3. tv", "2200", "København");
        addressRepository.save(a1);

        Address a2 = new Address
                ("GadeVænget 2", "2. tv", "2200", "København");
        addressRepository.save(a2);

        Address a3 = new Address
                ("Gadeallé 200", null, "3402", "By");
        addressRepository.save(a3);

        Address a4 = new Address
                ("Vej 12", "4. th", "1200", "By2");
        addressRepository.save(a4);

        Address a5 = new Address
                ("Vejvej 14", null, "4400", "By3");
        addressRepository.save(a5);


        //MakePeople
        Person p1 = personRepository.save(new Person
                ("amanda@mail.dk", "Amanda", "Amandasen", "70121416", a1));
        Person p2 = personRepository.save(new Person
                ("casper@mail.co.uk", "Casper", "Caspersen", "88888888", a2));
        Person p3 = personRepository.save(new Person
                ("test@mail.com", "Test", "Test", "125", a3));

        Person p4 = personRepository.save(new Person
                ("sandy@mail.dk", "Sandy", "Svendsson", "76543", a4));
        Person p5 = personRepository.save(new Person
                ("karlsson@mail.co.uk", "Karl", "Karlsson", "2543", a5));


        //Makehobbies

        Hobby h1 = hobbyRepository.save(new Hobby
                ("Fodbold", "category", "fodbold.dk", "out"));

        Hobby h2 = hobbyRepository.save(new Hobby
                ("Håndbold", "category", "håndbold.dk", "out"));

        Hobby h3 = hobbyRepository.save(new Hobby
                ("LARP", "category", "LARP.dk", "out"));

        Hobby h4 = hobbyRepository.save(new Hobby
                ("Strikning", "category", "strik.dk", "out"));

        Hobby h5 = hobbyRepository.save(new Hobby
                ("Kattepasning", "category", "kat.dk", "out"));


        //MakeHobbyInfos
        HobbyInfo hi1 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022,03,01,9,23),h1,p1));

        HobbyInfo hi2 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022,03,02,9,23),h5,p1));

        HobbyInfo hi3 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022,03,03,9,23),h3,p2));

        HobbyInfo hi4 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022,03,04,9,23),h1,p2));

        HobbyInfo hi5 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022,03,05,9,23),h4,p3));

        HobbyInfo hi6 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022,03,06,9,23),h5,p3));

        HobbyInfo hi7 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022,03,07,9,23),h2,p3));

        HobbyInfo hi8 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022,03,8,9,23),h1,p5));

        HobbyInfo hi9 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022,03,9,9,23),h2,p5));

        HobbyInfo hi10 = hobbyInfoRepository.save(new HobbyInfo
                (LocalDateTime.of(2022,03,10,9,23),h3,p5));

        //Make users
        BaseUser user = new BaseUser("user", "user@a.dk", "test12");
        user.addRole(Role.USER);
        BaseUser admin = new BaseUser("admin", "admin@a.dk", "test12");
        admin.addRole(Role.ADMIN);

        BaseUser both = new BaseUser("user_admin", "both@a.dk", "test12");
        both.addRole(Role.USER);
        both.addRole(Role.ADMIN);

        userRepository.save(user);
        userRepository.save(admin);
        userRepository.save(both);


    }



    @Override
    public void run(ApplicationArguments args) throws Exception {

        makeTestData();


    }
}
