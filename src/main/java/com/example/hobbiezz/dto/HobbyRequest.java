package com.example.hobbiezz.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HobbyRequest {
    String name;
    String link;
    String category;
    String inOut;
}
