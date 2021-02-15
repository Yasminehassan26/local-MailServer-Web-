package EmailClient;

import java.util.LinkedList;
import UsersAndContacts.*;
public interface IApp {

    public LinkedList<Email> listEmails(int id , String folderName, int page);
    public LinkedList<Email> searchedEmails(int id , String folderName, int page,String search,String sort,String[] filter);

    public void deleteEmails(int id, String folderName,LinkedList<Integer> ids);
    public void moveEmails(int id,String fromFolder,String toFolder,LinkedList<Integer> emailsID);
    public LinkedList<String> compose(int id,String folderName,Email email);
    public String addFolder(int id , String folderName);
    public String renameFolder(int id, String folderName,String newName);
    public void deleteFolder(int id ,String folderName);
    public LinkedList<String> loadFolderName(int id);
    public void copyEmails(int id,String fromFolder , String toFolder , LinkedList<Integer> emailsID);
    public User returnUser(int id);
    public void addContact(int id,Contact contact);
    public void deleteContact(int id, int contact);
    public void changeContactName(int id, int contactID, String name);
    public void addContactEmail(int id, int contactID, LinkedList<String> email);
    public LinkedList<Contact> searchForContacts(int id, String contactName);
    public LinkedList<Contact> sortContacts(int id);
    public LinkedList<Contact> loadContacts(int id);

}
