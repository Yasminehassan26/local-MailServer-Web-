package jsonOperations;

import UsersAndContacts.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ContactJson implements JsonManipulator {

    ObjectMapper objectMapper;

    public ContactJson() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public Contact readFromJson(File fileToRead) {
        Contact contact = null;
        try {
            contact  = objectMapper.readValue(fileToRead, Contact.class);
        } catch (IOException e) {
            System.out.println("wrong in reading from json");
            e.printStackTrace();
        }
        return contact;    }

    @Override
    public void writeToJson(Object objectToWrite, File file) {
        Contact contact = (Contact) objectToWrite;
        try {
            objectMapper.writeValue(file, contact);
        } catch (IOException e) {
            System.out.println("wrong in writing to json");
        }
    }
}
