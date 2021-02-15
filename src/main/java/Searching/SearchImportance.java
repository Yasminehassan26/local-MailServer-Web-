package Searching;

import EmailClient.Email;

import java.util.LinkedList;

public class SearchImportance implements ICriteriaS {

    public LinkedList<Email> meetCriteria(LinkedList<Email> emails, String criteria) {
        LinkedList<Email> filteredEmail = new LinkedList<Email>();
        try {
            int priority = Integer.parseInt(criteria);
            for (Email email : emails) {
                if (email.getImportance() == priority) {
                    filteredEmail.add(email);
                }
            }
        }catch (NumberFormatException nfe) {
            return new LinkedList<Email>();
        }
        return filteredEmail;
    }
}