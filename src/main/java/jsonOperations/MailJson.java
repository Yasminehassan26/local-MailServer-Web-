package jsonOperations;
import EmailClient.Email;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class MailJson implements JsonManipulator{

    ObjectMapper objectMapper;

    public MailJson(){
        objectMapper = new ObjectMapper();
    }


    @Override
    public Email readFromJson(File users) {
        Email email = null;
        try {
            email  = objectMapper.readValue(users, Email.class);
        } catch (IOException e) {
            System.out.println("wrong in reading from json");
            e.printStackTrace();
        }
        return email;
    }

    @Override
    public void writeToJson(Object objectToWrite, File file) {
        Email email = (Email) objectToWrite;
        try {
            objectMapper.writeValue(file,email);
        } catch (IOException e) {
            System.out.println("wrong in writing to json");
        }
    }
}
