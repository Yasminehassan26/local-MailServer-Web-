package Filter;

import UsersAndContacts.Contact;

import java.util.LinkedList;

public class ContactEmailCriteria implements  IContactCriteria{
    @Override
    public LinkedList<Contact> meetCriteria(LinkedList<Contact> contacts, String name) {
        LinkedList<Contact> filteredContacts = new LinkedList<Contact>();
        for(Contact temp : contacts){
            if(temp.getEmails() != null) {
                for (int i = 0; i < temp.getEmails().size(); i++) {
                    if(temp.getEmails().get(i).equalsIgnoreCase(name)){
                        filteredContacts.add(temp);
                        break;
                    }
                }
            }
        }
        return filteredContacts;
    }
}
