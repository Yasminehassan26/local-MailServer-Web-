package Sorting;

import EmailClient.Email;

import java.util.LinkedList;

public class SortSender extends Sort {

    public int partition(LinkedList<Email> emails, int position, int start, int end) {
        int l = start;
        int h = end - 2;
        Email mail = (Email) emails.get(position);
        String piv = mail.getSender();
        swap(emails, position, end - 1);
        Email mail1;
        Email mail2;

        while (l < h) {
            mail1 = (Email) emails.get(l);
            mail2 = (Email) emails.get(h);
            if (mail1.getSender().compareToIgnoreCase(piv) < 0) {
                l++;
            } else if (mail2.getSender().compareToIgnoreCase(piv) >= 0) {
                h--;
            } else {
                swap(emails, l, h);
            }
        }
        int idx = h;
        mail2 = (Email) emails.get(h);
        if (mail2.getSender().compareToIgnoreCase(piv) < 0) {
            idx++;
        }
        swap(emails, end - 1, idx);
        return idx;
    }
}
