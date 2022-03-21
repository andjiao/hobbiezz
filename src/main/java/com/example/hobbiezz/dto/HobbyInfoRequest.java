package com.example.hobbiezz.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HobbyInfoRequest {

    private String id;
    private LocalDateTime date;

}
