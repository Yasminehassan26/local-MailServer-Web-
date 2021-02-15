package Filter;

import EmailClient.Email;

import java.util.LinkedList;

public interface ICriteria {

     public LinkedList<Email> meetCriteria(LinkedList<Email> emails, String[] criteria);

}
