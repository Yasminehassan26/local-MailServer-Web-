package EmailClient;

import java.util.LinkedList;
import java.util.Date;

public class Email{
    private int id;
    private int importance;
    private String subject;
    private String emailBody;
    private String sender;
    private LinkedList<String> receiver;
    private Date date;
    private LinkedList<String> attachments;


    public Email(EmailBuilder builder) {
        this.id = builder.getId();
        this.sender = builder.getSender();
        this.subject = builder.getSubject();
        this.receiver = builder.getReceiver();
        this.emailBody = builder.getEmailBody();
        this.importance = builder.getImportance();
        this.date = builder.getDate();
        this.attachments = builder.getAttachments();
    }

    public Email(){

    }

    public String getSender() {
        return this.sender;
    }

    public String getSubject() {
        return this.subject;
    }

    public LinkedList<String> getReceiver() {
        return this.receiver;
    }
    /*
    public void setReceiver(LinkedList<String> receivers){
        this.receivers=receivers;
    }
*/
    public String getEmailBody() {
        return this.emailBody;
    }

    public LinkedList<String> getAttachments() {
        return this.attachments;
    }

    public int getImportance() {
        return this.importance;
    }

    public Date getDate() {
        return date;}

    public int getId(){
        return id;
    }




}
