package com.example.hobbiezz.dto;


import com.example.hobbiezz.entity.Hobby;
import com.example.hobbiezz.entity.HobbyInfo;
import com.example.hobbiezz.entity.Person;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class HobbyInfoResponse {

    int id;

    LocalDateTime hobbyAdded;

    Hobby hobbyObject;

    Person hasHobbies;

    public HobbyInfoResponse(HobbyInfo hobbyInfo){
        this.id= hobbyInfo.getId();
        this.hobbyAdded=hobbyInfo.getHobbyAdded();
        this.hobbyObject=hobbyInfo.getHobbyObject();
        this.hasHobbies=hobbyInfo.getHasHobbies();
    }

    public List< HobbyInfoResponse> getHobbyInfoFromEntities(List<HobbyInfo> hobbyInfos){
        return hobbyInfos.stream().map(hobbyInfo -> new HobbyInfoResponse(hobbyInfo)).collect(Collectors.toList());
    }
}
