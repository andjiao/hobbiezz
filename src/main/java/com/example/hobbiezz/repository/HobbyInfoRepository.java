package com.example.hobbiezz.repository;

import com.example.hobbiezz.dto.HobbyInfoResponse;
import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.entity.HobbyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HobbyInfoRepository extends JpaRepository<HobbyInfo, Integer> {
    @Override
    List<HobbyInfo> findAll();

    //Få liste over HobbyInfo knyttet til en person
    List<HobbyInfo> findHobbyInfosByHasHobbies_Id(int id);


    //Få liste over HobbyInfo knyttet til en hobby

    List<HobbyInfo> findHobbyInfosByHobbyObject(Hobby hobby);

    //Nedenstående gør det umuligt for programmet at køre, når den ikke er udkommenteret
    //List<HobbyInfo> findHobbyInfosByHobbyAdded_Id(String name);



}
