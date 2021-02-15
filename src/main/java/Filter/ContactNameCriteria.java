package Filter;

import EmailClient.Email;
import UsersAndContacts.Contact;

import java.util.LinkedList;

public class ContactNameCriteria implements IContactCriteria{


    @Override
    public LinkedList<Contact> meetCriteria(LinkedList<Contact> contacts, String name) {
        LinkedList<Contact> filteredContacts = new LinkedList<Contact>();
        for(Contact temp : contacts){
            if(temp.getName().equalsIgnoreCase(name)){
                filteredContacts.add(temp);
            }
        }
        return filteredContacts;
    }
}
