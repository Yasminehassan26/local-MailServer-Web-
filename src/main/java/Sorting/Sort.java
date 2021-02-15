package Sorting;

import EmailClient.Email;

import java.util.LinkedList;
import java.util.Stack;

public abstract class Sort {
    private String typeOfSort;
    public void setTypeOfSort(String typeOfSort) {
        this.typeOfSort = typeOfSort;
    }

    public String getTypeOfSort() {
        return typeOfSort;
    }

    public void quickSort(LinkedList<Email> emails) {
        Stack stack = new Stack();
        stack.push(0);
        stack.push(emails.size());

        while (!stack.isEmpty()) {
            int end = (int) stack.pop();
            int start = (int) stack.pop();
            if (end - start < 2) {
                continue;
            }
            int p = start + ((end - start) / 2);
            p = partition(emails, p, start, end);
            stack.push((p + 1));
            stack.push(end);

            stack.push(start);
            stack.push(p);

        }
    }

    public abstract int partition(LinkedList<Email> emails, int position, int start, int end);

    public void swap(LinkedList<Email> emails, int i, int j) {
        Email temp = (Email) emails.get(i);
        emails.set(i, emails.get(j));
        emails.set(j, temp);
    }

}
