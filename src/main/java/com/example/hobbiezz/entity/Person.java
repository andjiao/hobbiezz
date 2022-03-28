package com.example.hobbiezz.entity;

import com.example.hobbiezz.dto.PersonRequest;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.HashSet;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String email;
    //@Column(length = 320)


    String firstName;

    String lastName;

    @Column(length = 20)
    String phone;

    @ManyToOne
    Address connectedAddress;


    public Person(String email, String firstName, String lastName, String phone) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;

    }




    /*@OneToMany(mappedBy = "personalAddress", fetch = FetchType.EAGER)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)

    private Set<Address> personalAddress = new HashSet<>();

    public void addAddress(Address address){
        personalAddress.add(address);
    }

     */


    public Person(PersonRequest body) {
        this.email = body.getEmail();
        this.firstName = body.getFirstName();
        this.lastName = body.getFirstName();
        this.phone = body.getPhone();
    }


    public Person(String email, String firstName, String lastName, String phone, Address connectedAddress){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.connectedAddress=connectedAddress;
    }


    @OneToMany(mappedBy = "hasHobbies", fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    private Set<HobbyInfo> hobbyInfos = new HashSet<>();

    public void addHobby(HobbyInfo hi){
        hobbyInfos.add(hi);
    }

    //Lavet for at kunne lave tests
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId() == person.getId() && Objects.equals(getEmail(), person.getEmail()) && Objects.equals(getFirstName(), person.getFirstName()) && Objects.equals(getLastName(), person.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getFirstName(), getLastName());
    }
}


