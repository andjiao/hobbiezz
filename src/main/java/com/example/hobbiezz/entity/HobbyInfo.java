package com.example.hobbiezz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    public HobbyInfo(Hobby hobbyObject, Person hasHobbies){
        this.hasHobbies=hasHobbies;
        this.hobbyObject =hobbyObject;
        hobbyObject.addHobbyInfos(this);
        hasHobbies.addHobby(this);

    }
}
