package com.example.hobbiezz.repository;

import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.entity.HobbyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, String> {

    Hobby findHobbyByHobbyInfos(HobbyInfo hobbyInfo);

}
