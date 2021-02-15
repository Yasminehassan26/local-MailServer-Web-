package EmailClient;
import Filter.AndCriteria;
import Filter.ICriteria;
import Searching.OrCriteria;
import Sorting.*;
import UsersAndContacts.*;
import jsonOperations.MailJson;
import jsonOperations.UserJson;
import jsonOperations.idsJson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.util.LinkedList;
public class EmailClientApp implements IApp{

    MailJson jsonReadWrite = new MailJson();
    idsJson idsJsonReadWrite = new idsJson();
    UserJson userJsonReadWrite=new UserJson();
    ContactManipulator contactsManipulator = new ContactManipulator();
    /*
    need linked list of emails of the folder
    */

    public LinkedList<Email> listEmails(int id, String folderName, int page){
        if(folderName.equals("Trash")){
            delete(id);
        }
        LinkedList<Email> emailsToShow = new LinkedList<Email>();
        File index = new File("Accounts\\"+id+"\\"+folderName);
        File[] mails = index.listFiles();
        LinkedList<Email> list = new LinkedList<Email>();
        if(mails != null) {
            for (int i = 0; i < mails.length; i++) {
                Email tempMail = jsonReadWrite.readFromJson(mails[i]);
                list.add(tempMail);
            }
        }
        int interval=page*10;
        int first=interval-10;
        int last=interval-1;
        if(last >= list.size()) {
            last = list.size()-1 ;
        }
        if(first >= list.size()) {
            return emailsToShow;
        }
        List<Email> subList =  list.subList(first, last+1);
        for(int i = 0; i < subList.size(); i++) {
            emailsToShow.add(subList.get(i));
        }
        return emailsToShow;
    }

    @Override
    public LinkedList<Email> searchedEmails(int id, String folderName, int page, String search, String sort, String[] filter) {
        if(folderName.equals("Trash")){
            delete(id);
        }
        File index = new File("Accounts\\"+id+"\\"+folderName);
        File[] mails = index.listFiles();
        LinkedList<Email> list = new LinkedList<Email>();
        LinkedList<Email> emailsToShow = new LinkedList<Email>();

        for(int i = 0; i < mails.length; i++){
            Email  tempMail = jsonReadWrite.readFromJson(mails[i]);
            list.add(tempMail);
        }
        ///////////////////////////
        //search
        if(search != "") {
            OrCriteria searchFor = new OrCriteria();
            list = searchFor.meetCriteria(list, search);
        }
        // filter
        ICriteria filtering = new AndCriteria();
        list = filtering.meetCriteria(list,filter);

        //sort
        SortingFactory sortFor= new SortingFactory();
        sortFor.sort(list,sort);
        ///////////////////////////
        int interval=page*10;
        int first=interval-10;
        int last=interval-1;
        if(last >= list.size()) {
            last = list.size()-1 ;
        }
        if(first >= list.size()) {
            return emailsToShow;
        }
        List<Email> subList =  list.subList(first, last+1);
        for(int i = 0; i < subList.size(); i++) {
            emailsToShow.add(subList.get(i));
        }
        return emailsToShow;
    }

    public void delete(int id){
        Date currentDate = new Date();
        File index = new File("Accounts\\"+id+"\\"+"Trash");
        File[] mails = index.listFiles();
        LinkedList<Email> deletedEmail = new LinkedList<Email>();
        if(mails != null) {
            for (int i = 0; i < mails.length; i++) {
                Email tempMail = jsonReadWrite.readFromJson(mails[i]);
                long difference_In_Time = currentDate.getTime() - tempMail.getDate().getTime();
                long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
                if (difference_In_Days > 30) {
                    mails[i].delete();
                }
            }
        }
    }

    public void deleteEmails(int id, String folderName, LinkedList<Integer> emailsID){
        moveEmails(id, folderName, "Trash", emailsID);
    }

    public void moveEmails(int id,String fromFolder,String toFolder, LinkedList<Integer> emailsID){
        for(int i = 0; i < emailsID.size(); i++){
            int tempId = emailsID.get(i);
            try {
                Path temp = Files.move(Paths.get("Accounts\\"+id+"\\"+fromFolder+"\\"+tempId+".json"), Paths.get("Accounts\\"+id+"\\"+toFolder+"\\"+tempId+".json"));
            } catch (IOException e) {
                System.out.println("Can't move file");
            }
        }
    }
    public void copyEmails(int id,String fromFolder , String toFolder , LinkedList<Integer> emailsID) {
        Prototype prototype =new Prototype();

        for(int i = 0; i < emailsID.size(); i++) {
            File emailToCopy = new File("Accounts\\" + id + "\\" + fromFolder + "\\" + emailsID.get(i) + ".json");
            Email tempMail = jsonReadWrite.readFromJson(emailToCopy);
            Email copiedMail = prototype.duplicateEmail(tempMail);
            File index = new File("Accounts\\" + id + "\\" + toFolder + "\\" + copiedMail.getId() + ".json");
            jsonReadWrite.writeToJson(copiedMail, index);
        }
    }

    @Override
    public User returnUser(int id) {
        File usersFile = new File("Accounts\\users.json");
        LinkedList<User> users = userJsonReadWrite.readFromJson(usersFile);
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getId()==id){
                return users.get(i);
            }
        }
        return null;
    }

    @Override
    public void addContact(int id, Contact contact) {
        contactsManipulator.addContact(id, contact);
    }

    @Override
    public void deleteContact(int id, int contact) {
        contactsManipulator.deleteContact(id, contact);
    }

    @Override
    public void changeContactName(int id, int contactID, String name) {
        contactsManipulator.changeContactName(id, contactID, name);
    }

    @Override
    public void addContactEmail(int id, int contactID, LinkedList<String> email) {
        contactsManipulator.addContactEmail(id, contactID, email);
    }

    @Override
    public LinkedList<Contact> searchForContacts(int id, String contactName) {
        return contactsManipulator.searchForContacts(id, contactName);
    }

    @Override
    public LinkedList<Contact> sortContacts(int id) {
        return contactsManipulator.sortContacts(id);
    }


    public LinkedList<String> compose(int id ,String folderName,Email email){
        // check if it's from draft and if true delete
        File draft = new File("Accounts\\"+id+"\\Draft\\"+email.getId()+".json");
        if(draft.exists()){
            draft.delete();
        }
        Date date = new Date();
        // create new email with id from json file
        int newID = (int) idsJsonReadWrite.readFromJson(new File("Accounts\\mailsID.json"));
        Email newEmail = new EmailBuilder(newID, email.getImportance(), email.getSubject(), email.getEmailBody(), email.getSender(), email.getReceiver(), date).attachments(email.getAttachments()).build();
        idsJsonReadWrite.writeToJson(++newID, new File("Accounts\\mailsID.json"));

        File index = new File("Accounts\\" + id + "\\" + folderName + "\\" + newEmail.getId() + ".json");
        File idsEmail = new File("Accounts\\mailsID.json");
        Verify verify = new Verify();
        LinkedList<String> done = new LinkedList<String>();
        if (folderName.equalsIgnoreCase("Sent")) {
            // check if reciever is in the system
            LinkedList<String> receivers = email.getReceiver();
            for (int i = 0; i < receivers.size(); i++) {
                String find = verify.checkReceiver(receivers.get(i));
                try {
                    int receiverID = Integer.parseInt(find);
                    LinkedList<String> insert = new LinkedList<String>();
                    insert.add(receivers.get(i));
                    //// load id from serial number index file
                    int mailID = (int) idsJsonReadWrite.readFromJson(idsEmail);
                    Email receiveEmail = new EmailBuilder(mailID, email.getImportance(), email.getSubject(), email.getEmailBody(), email.getSender(), insert, date).attachments(email.getAttachments()).build();
                    File emailToSend = new File("Accounts\\" + receiverID + "\\" + "Inbox" + "\\" + receiveEmail.getId() + ".json");
                    jsonReadWrite.writeToJson(receiveEmail, emailToSend);
                    idsJsonReadWrite.writeToJson(++mailID, idsEmail);
                } catch (NumberFormatException nfe) {
                    done.add(find);
                }
            }
        }
        jsonReadWrite.writeToJson(newEmail, index);
        return done;

    }

    public LinkedList<String> loadFolderName(int id){
        LinkedList<String> folderNames = new LinkedList<String>();
        File user = new File("Accounts\\"+id);
        File[] names = user.listFiles();


        for(File file : names){
            String temp = file.getName();
            if( !temp.equals("Inbox") && !temp.equals("Draft") && !temp.equals("Sent") && !temp.equals("Trash") && !temp.equals("Contacts") ){
                folderNames.add(file.getName());
            }
        }
        return folderNames;
    }

    @Override
    public String addFolder(int id, String folderName) {
        File user = new File("Accounts\\"+id);
        File[] files = user.listFiles();
        LinkedList<String> filesNames = new LinkedList<String>();
        for(File file : files){
            filesNames.add(file.getName());
        }
        if(! filesNames.contains(folderName)){
            Folder newFolder = new Folder("Accounts\\"+id+"\\"+folderName);
            newFolder.createFolder();
            return "done";
        }
        return "Folder Already exists";
    }

    @Override
    public String renameFolder(int id, String folderName, String newName) {
        File user = new File("Accounts\\"+id);
        File[] files = user.listFiles();
        LinkedList<String> filesNames = new LinkedList<String>();
        for(File file : files){
            filesNames.add(file.getName());
        }
        if(! filesNames.contains(newName)) {
            Folder folderToRename = new Folder("Accounts\\" + id + "\\" + folderName);
            folderToRename.renameFolder(id, newName);
            return "done";
        }
        return "A folder with this name already exists";
    }

    @Override
    public void deleteFolder(int id, String folderName) {
        Folder folderToDelete = new Folder("Accounts\\"+id+"\\"+folderName);
        folderToDelete.deleteFolder();
    }

    @Override
    public LinkedList<Contact> loadContacts(int id) {
        return contactsManipulator.loadContacts(id);
    }
}
