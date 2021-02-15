package Searching;

import EmailClient.Email;

import java.util.LinkedList;
import java.util.Stack;

public class SearchSubject implements ICriteriaS{

    public LinkedList<Email> meetCriteria(LinkedList<Email> emails, String criteria) {
        LinkedList<Email> filteredEmail = new LinkedList<Email>();
            for (Email email : emails) {
                if (email.getSubject().contains(criteria)){
                    filteredEmail.add(email);
                }
            }
            return filteredEmail;
    }
}
