package HobbySQL;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class HobbyJava {
    public static void main(String[] args) throws IOException {

        String createTableHobby="";
        String createWithInsert = null;

        createWithInsert =createTableHobby + Files.lines(Paths.get("src/main/java/HobbySQL/Hobbies.text"))
                .map(hobby -> {
                    String[] c= hobby.split(";");
                    return String.format("hobbyRepository.save(new Hobby(\"%s\",\"%s\",\"%s\",\"%s\")),",c[0],c[1],c[2],c[3]);
                }).collect(Collectors.joining("\n"));
        System.out.println(createWithInsert + "");




            /*

           Larses kode:

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class HobbySQLStatementService {
    public static void main(String[] args) throws IOException {
        System.out.println("Categories (distinct): \n" + Files.lines((Paths.get("src/main/java/com/example/hobbiezz/dto/hobbies")))
                .map(hobby -> hobby.split(";")[2]).distinct()
                .collect(Collectors.joining("\n")));

        System.out.println("\nIn/Outdoor (distinct): \n" + Files.lines((Paths.get("src/main/java/com/example/hobbiezz/dto/hobbies")))
                .map(hobby -> hobby.split(";")[3]).distinct()
                .collect(Collectors.joining("\n")));

        String createTableHobby = "\ncreate table hobby (\n" +
                "  name  varchar(40) primary key,\n" +
                "  link  varchar(100),\n" +
                "  category varchar(40),\n" +
                "  in_out varchar(10)\n" +
                "  );\n\n";

        String createWithInserts = createTableHobby + Files.lines(Paths.get("src/main/java/com/example/hobbiezz/dto/hobbies"))
                .map(hobby -> {
                    String[] c= hobby.split(";");
                    return String.format("INSERT INTO hobby VALUES ('%s','%s','%s','%s');", c[0],c[1],c[2],c[3]);
                }).collect(Collectors.joining("\n"));

        System.out.println(createWithInserts);  //Write it to a file if you prefer

        try {
            FileWriter myWriter = new FileWriter("src/main/java/com/example/hobbiezz/dto/hobbies");
            myWriter.write(createWithInserts);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
            */

    }
}



