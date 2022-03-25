package com.example.hobbiezz.configuration;

import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.repository.HobbyRepository;

public class makeHobby {

    Hobby hobby;
    HobbyRepository hobbyRepository;

    public makeHobby(HobbyRepository hobbyRepository) {
        this.hobbyRepository = hobbyRepository;
    }

    public void insertHobbiezz() {
       Hobby h2= hobbyRepository.save(new Hobby("Birdwatching", "https://en.wikipedia.org/wiki/Birdwatching", " Observation hobbyer", "Udendørs"));
        Hobby h3= hobbyRepository.save(new Hobby("Bus spotting", "https://en.wikipedia.org/wiki/Bus_spotting", " Observation hobbyer", "Udendørs"));
        Hobby h4=hobbyRepository.save(new Hobby("Geocaching", "https://en.wikipedia.org/wiki/Geocaching", " Observation hobbyer", "Udendørs"));
        Hobby h5=hobbyRepository.save(new Hobby("Gongoozler", "https://en.wikipedia.org/wiki/Gongoozler", " Observation hobbyer", "Udendørs"));
        Hobby h6=hobbyRepository.save(new Hobby("Herping", "https://en.wikipedia.org/wiki/Herping", " Observation hobbyer", "Udendørs"));
        Hobby h7=hobbyRepository.save(new Hobby("Hiking", "https://en.wikipedia.org/wiki/Hiking", " Observation hobbyer", "Udendørs"));
        Hobby h8=hobbyRepository.save(new Hobby("Backpacking (wilderness)", "https://en.wikipedia.org/wiki/Backpacking_(wilderness)", " Observation hobbyer", "Udendørs"));
        Hobby h9=hobbyRepository.save(new Hobby("Meteorology", "https://en.wikipedia.org/wiki/Meteorology", " Observation hobbyer", "Udendørs"));
        Hobby h10=hobbyRepository.save(new Hobby("Photography", "https://en.wikipedia.org/wiki/Photography", " Observation hobbyer", "Udendørs"));
        Hobby h11=hobbyRepository.save(new Hobby("Satellite watching", "https://en.wikipedia.org/wiki/Satellite_watching", " Observation hobbyer", "Udendørs"));
        Hobby h12=hobbyRepository.save(new Hobby("Trainspotting (hobby)", "https://en.wikipedia.org/wiki/Trainspotting_(hobby)", " Observation hobbyer", "Udendørs"));
        Hobby h13=hobbyRepository.save(new Hobby("Whale watching", "https://en.wikipedia.org/wiki/Whale_watching", " Observation hobbyer", "Udendørs"));
    }
}
