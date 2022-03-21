package com.example.hobbiezz.repository;

import com.example.hobbiezz.entity.HobbyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HobbyInfoRepository extends JpaRepository<HobbyInfo, Integer> {
    @Override
    List<HobbyInfo> findAll();

    //Få liste over HobbyInfo knyttet til en person
    List<HobbyInfo> findHobbyInfosByHasHobbies_Id(int id);

    /*
    //Få liste over HobbyInfo knyttet til en hobby
    List<HobbyInfo> findHobbyInfosByHobbyAdded(Hobby hobby);

     */
}
