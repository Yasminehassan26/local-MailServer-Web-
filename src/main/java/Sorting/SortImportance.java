package Sorting;

import AdditionalClasses.PriorityQueue;
import EmailClient.Email;
import java.util.LinkedList;

public class SortImportance {
    public void sortPriority(LinkedList<Email> mails) {
        PriorityQueue q = new PriorityQueue();
        for (int i = 0; i < mails.size(); i++) {
            Email m = (Email) mails.get(i);
            q.insert(m, m.getImportance());
        }
        for (int i = 0; i < q.size(); i++) {
            mails.set(i, (Email) q.removeMin());
        }
    }
}
