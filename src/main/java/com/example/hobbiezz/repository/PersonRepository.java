package com.example.hobbiezz.repository;

import com.example.hobbiezz.entity.HobbyInfo;
import com.example.hobbiezz.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("select (count(m) > 0) from Person m where m.email = :email")
    boolean emailExist(String email);


    Person findPersonByHobbyInfos(HobbyInfo hobbyinfo);
    Person findPersonById(int id);

    //void deleteById(String email);

}
