package Filter;

import EmailClient.Email;

import java.util.LinkedList;

public class SubjectCriteria implements ICriteria {
    @Override
    public LinkedList<Email> meetCriteria(LinkedList<Email> emails, String[] criteria) {
        LinkedList<Email> filteredEmail = new LinkedList<Email>();
        if(criteria[0] != "") {
            for (Email email : emails) {
                if (email.getSubject().contains(criteria[0])) {
                    filteredEmail.add(email);
                }
            }
            return filteredEmail;
        }
        return emails;
    }
}
