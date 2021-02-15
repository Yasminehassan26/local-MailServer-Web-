package Sorting;

import EmailClient.Email;

import java.util.LinkedList;

public class SortingFactory {

    public void sort(LinkedList<Email> emails, String sortBy){
        LinkedList<Email> sortedEmails ;
        if(sortBy.equalsIgnoreCase("Subject")){
            Sort sort = new SortSubject();
            sort.quickSort(emails);
        }else if(sortBy.equalsIgnoreCase("Sender")){
            Sort sort = new SortSender();
            sort.quickSort(emails);
        }else if(sortBy.equalsIgnoreCase("Date")){
            Sort sort = new SortDate();
            sort.quickSort(emails);
        }else if(sortBy.equalsIgnoreCase("Importance")) {
            SortImportance sort = new SortImportance();
            sort.sortPriority(emails);
        }
    }

}
