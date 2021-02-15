package UsersAndContacts;

import EmailClient.Folder;

import java.io.File;
import java.io.IOException;

public class UserBuilder {

    private String id;

    public UserBuilder(String userId){
        this.id = userId;
        creatUserAccount();
    }

    private void creatUserAccount(){
        Folder user = new Folder("Accounts\\"+this.id);
        user.createFolder();
        Folder inbox = new Folder("Accounts\\"+this.id+"\\Inbox");
        inbox.createFolder();
        Folder sent = new Folder("Accounts\\"+this.id+"\\Sent");
        sent.createFolder();
        Folder trash = new Folder("Accounts\\"+this.id+"\\Trash");
        trash.createFolder();
        Folder draft = new Folder("Accounts\\"+this.id+"\\Draft");
        draft.createFolder();
        Folder contacts = new Folder("Accounts\\"+this.id+"\\Contacts");
        contacts.createFolder();
    }
}
