package Searching;
import EmailClient.Email;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import Filter.ICriteria;
import Filter.SenderCriteria;
import Sorting.SortingFactory;

public class OrCriteria implements ICriteriaS{

    ICriteriaS searchSender = new SearchSender();
    ICriteriaS searchSubject = new SearchSubject();
    ICriteriaS searchImportance = new SearchImportance();
    ICriteriaS searchReceivers = new SearchReceivers();

    @Override
    public LinkedList<Email> meetCriteria(LinkedList<Email> emails, String criteria) {
        LinkedList<Email> firstsearch = searchSender.meetCriteria(emails, criteria);
        LinkedList<Email> secondSearch = searchSubject.meetCriteria(emails, criteria);
        LinkedList<Email> thirdSearch = searchImportance.meetCriteria(emails, criteria);
        LinkedList<Email> fourthSearch = searchReceivers.meetCriteria(emails, criteria);
        for(Email temp : secondSearch){
            if(! firstsearch.contains(temp)){
                firstsearch.add(temp);
            }
        }

        for(Email temp : thirdSearch){
            if(! firstsearch.contains(temp)){
                firstsearch.add(temp);
            }
        }

        for(Email temp : fourthSearch){
            if(! firstsearch.contains(temp)){
                firstsearch.add(temp);
            }
        }

        return firstsearch;
    }
}
