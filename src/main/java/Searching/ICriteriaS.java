package Searching;

import EmailClient.Email;

import java.util.LinkedList;

public interface ICriteriaS {
    public LinkedList<Email> meetCriteria(LinkedList<Email> emails, String criteria);
}
