package jsonOperations;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class idsJson implements JsonManipulator{

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Object readFromJson(File users) {
        int id = 0;
        try {
            id  = objectMapper.readValue(users, Integer.class);
        } catch (IOException e) {
            System.out.println("wrong in reading from json");
        }
        return id;
    }

    @Override
    public void writeToJson(Object objectToWrite, File file) {
        int id = (int) objectToWrite;
        try {
            objectMapper.writeValue(file,id);
        } catch (IOException e) {
            System.out.println("wrong in writing to json");
        }
    }

}
