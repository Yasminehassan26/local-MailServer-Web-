package Searching;


import java.util.LinkedList;
import EmailClient.Email;

public class SearchReceivers implements ICriteriaS{

    public LinkedList<Email> meetCriteria(LinkedList<Email> emails, String name) {
        LinkedList<Email> filteredContacts = new LinkedList<Email>();
        for(Email temp : emails){
            if(temp.getReceiver() != null) {
                for (int i = 0; i < temp.getReceiver().size(); i++) {
                    if(temp.getReceiver().get(i).contains(name) ){
                        filteredContacts.add(temp);
                        break;
                    }                }
            }
        }
        return filteredContacts;
    }

}
