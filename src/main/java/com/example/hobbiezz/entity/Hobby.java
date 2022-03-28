package com.example.hobbiezz.entity;

import com.example.hobbiezz.dto.HobbyRequest;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor

public class Hobby {
    @Id
    String name;
    String category;

    String link;
    String inOut;

    /*@OneToMany(mappedBy = "hobbyWithInfo")
    private Set<HobbyInfo> hobbies.txt = new HashSet<>();*/

    public Hobby(HobbyRequest body){
        this.name = body.getName();
        this.category= body.getCategory();
        this.link = body.getLink();
        this.inOut = body.getInOut();
    }

    public Hobby(String name, String category, String link, String inOut) {
        this.name = name;
        this.category= category;
        this.link = link;
        this.inOut = inOut;
    }

    @OneToMany(mappedBy = "hobbyObject", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)

    private Set<HobbyInfo> hobbyInfos = new HashSet<>();

    public void addHobbyObject(HobbyInfo hi){
        hobbyInfos.add(hi);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hobby)) return false;
        Hobby hobby = (Hobby) o;
        return Objects.equals(getName(), hobby.getName()) && Objects.equals(getCategory(), hobby.getCategory()) && Objects.equals(getLink(), hobby.getLink()) && Objects.equals(getInOut(), hobby.getInOut());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCategory(), getLink(), getInOut());
    }
}
