package com.example.hobbiezz.entity;

import com.example.hobbiezz.dto.HobbyRequest;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
//@AllArgsConstructor
@NoArgsConstructor

public class Hobby {
    @Id
    String name;

    String link;
    String category;
    String inOut;


    public Hobby(HobbyRequest body){
        this.name = body.getName();
        this.link = body.getLink();
        this.category = body.getCategory();
        this.inOut = body.getInOut();
    }

    public Hobby(String name, String link, String category, String inOut) {
        this.name = name;
        this.link = link;
        this.category = category;
        this.inOut = inOut;
    }
    @OneToMany(mappedBy = "hobbyObject", fetch = FetchType.EAGER)

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)

    private Set<HobbyInfo> hobbyInfos = new HashSet<>();

    public void addHobbyInfos(HobbyInfo hi){
        hobbyInfos.add(hi);
    }

    /*
       @OneToMany(mappedBy = "reservedCar", fetch = FetchType.EAGER)
    // Removes the Getter & Setter for this
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)

    private Set<Reservation> reservations = new HashSet<>();

    public void addResevertaion (Reservation res){
        reservations.add(res);
    }*/
}
