package UsersAndContacts;

import EmailClient.Email;

import java.util.LinkedList;

public class Contact {

    private int id;
    private String name ;
    private LinkedList<String> emails ;

    public Contact(String name, LinkedList<String> emails) {
        this.name = name;
        this.emails = emails;
    }

    public Contact(){

    }

    public void setID(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<String> getEmails() {
        return emails;
    }

    public void setEmails(LinkedList<String> emails) {
        this.emails = emails;
    }
}
