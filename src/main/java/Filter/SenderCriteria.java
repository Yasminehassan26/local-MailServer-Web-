package Filter;

import EmailClient.Email;

import java.util.LinkedList;

public class SenderCriteria implements ICriteria{


    @Override
    public LinkedList<Email> meetCriteria(LinkedList<Email> emails, String[] criteria) {
        LinkedList<Email> filteredEmail = new LinkedList<Email>();
        if(criteria[1] != "") {
            for (Email email : emails) {
                if (email.getSender().contains(criteria[1])) {
                    filteredEmail.add(email);
                }
            }
            return filteredEmail;
        }
        return emails;
    }
}
