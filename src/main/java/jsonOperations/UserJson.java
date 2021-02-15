package jsonOperations;
import UsersAndContacts.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



public class UserJson implements JsonManipulator {

    ObjectMapper objectMapper;

    public UserJson(){
        objectMapper = new ObjectMapper();
    }


    @Override
    public LinkedList<User> readFromJson(File users) {
        LinkedList<User> usersList = null;
        try {
            usersList  = objectMapper.readValue(users, new TypeReference<LinkedList<User>>() {});
        } catch (IOException e) {
            System.out.println("wrong in reading from json");
        }
        return usersList;
    }

    @Override
    public void writeToJson(Object objectToWrite, File file) {
        User user = (User) objectToWrite;
        LinkedList<User> usersList = readFromJson(file);
        if(usersList == null){
            usersList = new LinkedList<User>();
        }
        usersList.add((User) objectToWrite);
        try {
            objectMapper.writeValue(file,usersList);
        } catch (IOException e) {
          System.out.println("wrong in writing to json");
        }
    }
}
