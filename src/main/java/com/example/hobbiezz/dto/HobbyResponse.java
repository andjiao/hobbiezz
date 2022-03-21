package com.example.hobbiezz.dto;

import com.example.hobbiezz.entity.Hobby;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HobbyResponse {
    String name;

    String link;
    String category;
    String inOut;

    public HobbyResponse(Hobby hobby) {
        this.name = hobby.getName();
        this.link = hobby.getLink();
        this.category = hobby.getCategory();
        this.inOut = hobby.getInOut();
    }

    public static List<HobbyResponse> getHobbiesEntities(List<Hobby> hobbies){
        return hobbies.stream().map(hobby -> new HobbyResponse(hobby)).collect(Collectors.toList());
    }

}
