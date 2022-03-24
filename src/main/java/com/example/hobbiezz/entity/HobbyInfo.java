package com.example.hobbiezz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class HobbyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime hobbyAdded;

    @ManyToOne
    Hobby hobbyObject;

    @ManyToOne
    Person hasHobbies;

    public HobbyInfo(LocalDateTime date, Hobby hobbyObject, Person hasHobbies){
        this.hobbyAdded=date;
        this.hasHobbies=hasHobbies;
        this.hobbyObject =hobbyObject;
        hobbyObject.addHobbyObject(this);
        hasHobbies.addHobby(this);
    }

    public HobbyInfo(Hobby hobbyObject, Person hasHobbies){
        this.hasHobbies=hasHobbies;
        this.hobbyObject =hobbyObject;
        hobbyObject.addHobbyObject(this);
        hasHobbies.addHobby(this);
    }


    /*   @ManyToOne
    Car reservedCar;

    @ManyToOne
    Member reservedBy;

    public Reservation(LocalDate date, Car reservedCar, Member reservedBy) {
        this.reservationDate = date;
        this.reservedCar = reservedCar;
        this.reservedBy = reservedBy;
        reservedCar.addResevertaion(this);
        reservedBy.addResevertaion(this);

    }*/
}
