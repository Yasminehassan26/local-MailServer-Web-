package Sorting;
import EmailClient.Email;
import UsersAndContacts.*;
import java.util.LinkedList;
import java.util.Stack;


public class SortContact {

    public void quickSort(LinkedList<Contact> contacts) {
        Stack stack = new Stack();
        stack.push(0);
        stack.push(contacts.size());

        while (!stack.isEmpty()) {
            int end = (int) stack.pop();
            int start = (int) stack.pop();
            if (end - start < 2) {
                continue;
            }
            int p = start + ((end - start) / 2);
            p = partition(contacts, p, start, end);
            stack.push((p + 1));
            stack.push(end);

            stack.push(start);
            stack.push(p);

        }
    }

    public int partition(LinkedList<Contact> contacts, int position, int start, int end){
        int l = start;
        int h = end - 2;
        Contact contact = (Contact) contacts.get(position);
        String piv = contact.getName();
        swap(contacts, position, end - 1);
        Contact contact1;
        Contact contact2;

        while (l < h) {
            contact1 = (Contact) contacts.get(l);
            contact2 = (Contact) contacts.get(h);
            if (contact1.getName().compareToIgnoreCase(piv) < 0) {
                l++;
            } else if (contact2.getName().compareToIgnoreCase(piv) >= 0) {
                h--;
            } else {
                swap(contacts, l, h);
            }
        }
        int idx = h;
        contact2 = (Contact) contacts.get(h);
        if (contact2.getName().compareToIgnoreCase(piv) < 0) {
            idx++;
        }
        swap(contacts, end - 1, idx);
        return idx;
    }

    public void swap(LinkedList<Contact> contacts, int i, int j) {
        Contact temp = (Contact) contacts.get(i);
        contacts.set(i, contacts.get(j));
        contacts.set(j, temp);
    }


}
