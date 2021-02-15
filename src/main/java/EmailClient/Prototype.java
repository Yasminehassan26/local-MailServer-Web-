package EmailClient;

import java.io.File;

import jsonOperations.*;

public class Prototype implements Cloneable {

    private idsJson idsJsonReadWrite = new idsJson();

    public Email  duplicateEmail(Email temp){
        File idsEmail = new File("Accounts\\mailsID.json");
        int mailID = (int)idsJsonReadWrite.readFromJson(idsEmail);
        Email copyEmail = new EmailBuilder(mailID,temp.getImportance(),temp.getSubject(),temp.getEmailBody(),temp.getSender(),temp.getReceiver(), temp.getDate()).attachments(temp.getAttachments()).build();
        idsJsonReadWrite.writeToJson(++mailID, idsEmail);
        return  copyEmail;
    }
}
