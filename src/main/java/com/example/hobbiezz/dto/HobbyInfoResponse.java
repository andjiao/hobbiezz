package com.example.hobbiezz.dto;


import com.example.hobbiezz.entity.HobbyInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HobbyInfoResponse {

    int id;
    private LocalDateTime hobbyAdded;


    public HobbyInfoResponse(HobbyInfo hobbyInfo){
        this.id= hobbyInfo.getId();
        this.hobbyAdded=hobbyInfo.getHobbyAdded();
    }

    public List< HobbyInfoResponse> getHobbyInfoFromEntities(List<HobbyInfo> hobbyInfos){
        return hobbyInfos.stream().map(hobbyInfo -> new HobbyInfoResponse(hobbyInfo)).collect(Collectors.toList());
    }
}
