package EmailClient;

import java.util.Date;
import java.util.LinkedList;

public class EmailBuilder {
    private int id;
    private int importance;
    private String subject;
    private String emailBody;
    private String sender;
    private LinkedList<String> receiver;
    private Date date;
    private LinkedList<String> attachments;

    public EmailBuilder(int id,int importance,String subject, String emailBody,String sender,LinkedList<String> receiver,Date date) {
       this.id=id;
       this.importance=importance;
       this.subject=subject;
       this.emailBody=emailBody;
       this.sender=sender;
       this.receiver=receiver;
       this.date=date;
    }
    public EmailBuilder attachments(LinkedList<String> attachments) {
        this.attachments = attachments;
        return this;
    }
    public EmailBuilder(){

    }

    //Return the finally consrcuted User object
    public Email build() {
        Email email =  new Email(this);
        return email;
    }

    public int getId() {
        return id;
    }

    public int getImportance() {
        return importance;
    }

    public String getSubject() {
        return subject;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public String getSender() {
        return sender;
    }

    public LinkedList<String> getReceiver() {
        return receiver;
    }

    public Date getDate() {
        return date;
    }

    public LinkedList<String> getAttachments() {
        return attachments;
    }
}
