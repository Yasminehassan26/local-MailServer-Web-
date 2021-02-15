package Searching;

import EmailClient.Email;

import java.util.LinkedList;
import java.util.Stack;

public class SearchSender implements ICriteriaS {

    public LinkedList<Email> meetCriteria(LinkedList<Email> emails, String criteria) {
        LinkedList<Email> filteredEmail = new LinkedList<Email>();
            for (Email email : emails) {
                if (email.getSender().contains(criteria)) {
                    filteredEmail.add(email);
                }
            }
            return filteredEmail;
    }
}

