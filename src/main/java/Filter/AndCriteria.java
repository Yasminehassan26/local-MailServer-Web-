package Filter;

import EmailClient.Email;

import java.util.LinkedList;

public class AndCriteria implements ICriteria{

    private ICriteria subjectCriteria = new SubjectCriteria();
    private ICriteria senderCriteria = new SenderCriteria();

    @Override
    public LinkedList<Email> meetCriteria(LinkedList<Email> emails, String[] criteria){
        if(criteria != null) {
            LinkedList<Email> filteredEmail = subjectCriteria.meetCriteria(emails, criteria);
            return senderCriteria.meetCriteria(filteredEmail, criteria);
        }
        return emails;
    }

}
