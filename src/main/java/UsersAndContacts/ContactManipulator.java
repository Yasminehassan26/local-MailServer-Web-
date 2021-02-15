package UsersAndContacts;

import Filter.*;
import Sorting.SortContact;
import jsonOperations.ContactJson;
import jsonOperations.idsJson;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class ContactManipulator {

    private File contactsID = new File("Accounts\\contactsID.json");
    private idsJson idsJsonReadWrite = new idsJson();
    private ContactJson contactReadWrite = new ContactJson();
    private OrContactCriteria searchContact = new OrContactCriteria();
    private SortContact sortUserContacts = new SortContact();

    public void addContact(int id,Contact contact){
        int idContact = (int)idsJsonReadWrite.readFromJson(this.contactsID);
        contact.setID(idContact);
        File newContact = new File("Accounts\\"+id+"\\Contacts\\"+idContact+".json");
        idsJsonReadWrite.writeToJson(++idContact, contactsID);
        try {
            newContact.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // write contact in json file
        // change the MailJson to objectJson to use it for contacts and mails
        contactReadWrite.writeToJson(contact, newContact);
    }

    public void deleteContact(int id, int contact){
        File contactToBeDeleted = new File("Accounts\\"+id+"\\Contacts\\"+contact+".json");
        contactToBeDeleted.delete();
    }

    public void changeContactName(int id, int contactID, String name){
        File contact = new File("Accounts\\"+id+"\\Contacts\\"+contactID+".json");
        Contact contactToEdit = contactReadWrite.readFromJson(contact);
        contactToEdit.setName(name);
        contactReadWrite.writeToJson(contactToEdit, contact);
    }

    public void addContactEmail(int id, int contactID, LinkedList<String> emails){
        File contact = new File("Accounts\\"+id+"\\Contacts\\"+contactID+".json");
        Contact contactToEdit = contactReadWrite.readFromJson(contact);
        contactToEdit.setEmails(emails);
        contactReadWrite.writeToJson(contactToEdit, contact);
    }

    // search and sort contacts

    public LinkedList<Contact> searchForContacts(int id, String contactName){
        LinkedList<Contact> userContacts = new LinkedList<Contact>();
        File user = new File("Accounts\\"+id+"\\Contacts");
        File[] contacts = user.listFiles();
        if(contacts != null){
            for(File temp : contacts){
                userContacts.add(contactReadWrite.readFromJson(temp));
            }
        }
        return searchContact.meetCriteria(userContacts, contactName);
    }

    public LinkedList<Contact> sortContacts(int id){
        LinkedList<Contact> userContacts = new LinkedList<Contact>();
        File user = new File("Accounts\\"+id+"\\Contacts");
        File[] contacts = user.listFiles();
        if(contacts != null){
            for(File temp : contacts){
                userContacts.add(contactReadWrite.readFromJson(temp));
            }
        }
        sortUserContacts.quickSort(userContacts);
        return userContacts;
    }

    public LinkedList<Contact> loadContacts(int id){
        LinkedList<Contact> loadedContact = new LinkedList<Contact>();
        File contactsFile = new File("Accounts\\"+id+"\\Contacts");
        File[] contacts = contactsFile.listFiles();
        if(contacts != null){
            for(File temp : contacts){
                loadedContact.add(contactReadWrite.readFromJson(temp));
            }
        }
        return loadedContact;
    }

}
