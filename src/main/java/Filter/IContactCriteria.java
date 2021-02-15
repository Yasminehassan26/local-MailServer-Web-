package Filter;
import  UsersAndContacts.*;
import  java.util.LinkedList;

public interface IContactCriteria {

    public LinkedList<Contact> meetCriteria(LinkedList<Contact> contacts, String name);

}
