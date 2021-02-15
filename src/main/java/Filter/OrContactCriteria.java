package Filter;

import UsersAndContacts.*;

import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

public class OrContactCriteria implements IContactCriteria{
    IContactCriteria nameCriteria = new ContactNameCriteria();
    IContactCriteria emailCriteria = new ContactEmailCriteria();

    @Override
    public LinkedList<Contact> meetCriteria(LinkedList<Contact> contacts, String name) {
        LinkedList<Contact> filteredContacts = nameCriteria.meetCriteria(contacts, name);
        LinkedList<Contact> filterTwo = emailCriteria.meetCriteria(contacts, name);
        for(Contact temp : filterTwo){
            if(! filteredContacts.contains(temp)){
                filteredContacts.add(temp);
            }
        }
        return filteredContacts;
    }
}
