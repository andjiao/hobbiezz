package com.example.hobbiezz.service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class HobbySQLStatementService {

    public static void main(String[] args) throws IOException {

            String createTableHobby = "";
            String createWithInsert = null;

            createWithInsert = createTableHobby + Files.lines(Paths.get("src/main/java/com/example/hobbiezz/dto/hobbies.txt"))
                    .map(hobby -> {
                        String[] c = hobby.split(";");
                        return String.format("hobbyRepository.save(new Hobby(\"%s\",\"%s\",\"%s\",\"%s\")),", c[0], c[1], c[2], c[3]);
                    }).collect(Collectors.joining("\n"));
            System.out.println(createWithInsert + "");
        }
    }
