package EmailClient;

import UsersAndContacts.Contact;
import UsersAndContacts.User;
import UsersAndContacts.UserBuilder;
import jsonOperations.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class EmailClientProxy implements IApp{
    final String fileSeparator=System.getProperty("file.separator");
    private EmailClientApp app;
    private Verify verify;
    private UserJson jsonReadWrite;
    private idsJson  idsJsonReadWrite;
    private LinkedList<User> users;
    private String id;
    private String download = System.getProperty("user.home")+ fileSeparator +"Downloads"+fileSeparator+"\\Accounts\\Attachments";
    private File accounts = new File("Accounts");
    private File usersFile = new File("Accounts\\users.json");
    private File usersID = new File("Accounts\\usersID.json");
    private File mailsID = new File("Accounts\\mailsID.json");
    private File contactsID = new File("Accounts\\contactsID.json");
    private File attachments = new File("Accounts\\Attachments");
    private File downloads = new File(download);

    public EmailClientProxy(){
        this.verify = new Verify();
        this.jsonReadWrite = new UserJson();
        this.idsJsonReadWrite = new idsJson();
        if(! this.accounts.exists()){
            if(this.accounts.mkdir()){
                try {
                    this.usersFile.createNewFile();
                    this.usersID.createNewFile();
                    this.mailsID.createNewFile();
                    this.contactsID.createNewFile();
                    this.attachments.mkdir();
                    this.downloads.mkdir();
                } catch (IOException e) {
                    System.out.println("error in creation of user jason file");
                }
                idsJsonReadWrite.writeToJson(0,this.usersID);
                idsJsonReadWrite.writeToJson(0,this.mailsID);
                idsJsonReadWrite.writeToJson(0,this.contactsID);
            }
        }
    }



    public String signIn(String email, String password){
        this.users = jsonReadWrite.readFromJson(this.usersFile);
        String check = verify.correctUser(email,  password, this.users);
        // check =0 then email not found ,=1 valid user,=2 wrong passtr
        try{
            Integer.parseInt(check);
            this.app = new EmailClientApp();
            this.id = check;
        }
        catch (NumberFormatException nfe){

        }
        return check;
    }

    public int signUp(User user){


        //users json file
        this.users = jsonReadWrite.readFromJson(this.usersFile);
        boolean search = verify.checkEmail(user.getEmail(), this.users);
        if (!search){
            //email already used
            return -1;
        }
        //read user id
        int id = (int)idsJsonReadWrite.readFromJson(this.usersID);
        user.setID(id);
        idsJsonReadWrite.writeToJson(++id,this.usersID);

        // add user to index file of users
        jsonReadWrite.writeToJson(user, usersFile);

        // load users arrayList

        this.users = jsonReadWrite.readFromJson(usersFile);
        UserBuilder builder = new UserBuilder(Integer.toString(user.getId()));
        //account crated
        this.app = new EmailClientApp();

        return user.getId();
    }
    public  LinkedList<String> loadFolderName(int id){
        return app.loadFolderName(id);
    }

    public User returnUser(int id){
        return app.returnUser(id);
    }

    @Override
    public void addContact(int id, Contact contact) {
        app.addContact(id, contact);
    }

    @Override
    public void deleteContact(int id, int contact) {
        app.deleteContact(id, contact);
    }

    @Override
    public void changeContactName(int id, int contactID, String name) {
        app.changeContactName(id, contactID, name);
    }

    @Override
    public void addContactEmail(int id, int contactID, LinkedList<String> email) {
        app.addContactEmail(id, contactID, email);
    }

    @Override
    public LinkedList<Contact> searchForContacts(int id, String contactName) {
        return app.searchForContacts(id, contactName);
    }

    @Override
    public LinkedList<Contact> sortContacts(int id) {
        return app.sortContacts(id);
    }

    @Override
    public void copyEmails(int id, String fromFolder, String toFolder, LinkedList<Integer> emailsID) {
        app.copyEmails(id,fromFolder,toFolder,emailsID);

    }

    @Override
    public LinkedList<Email> listEmails(int id, String folderName, int page) {
        return app.listEmails(id,folderName,page);
    }

    @Override
    public LinkedList<Email> searchedEmails(int id, String folderName, int page, String search, String sort, String[] filter) {
        return app.searchedEmails(id,folderName,page,search,sort,filter);
    }

    @Override
    public void deleteEmails(int id,String folderName,LinkedList<Integer> list) {
        app.deleteEmails(id, folderName, list);
    }

    @Override
    public void moveEmails(int id,String fromFolder,String toFolder,LinkedList<Integer> emailsID) {
        app.moveEmails(id, fromFolder, toFolder, emailsID);
    }

    @Override
    public LinkedList<String> compose(int id, String name,Email email) {
        return app.compose(id, name, email);
    }

    @Override
    public String addFolder(int id, String folderName){
        return app.addFolder(id,folderName);
    }

    @Override
    public String renameFolder(int id, String folderName, String newName) {
        return app.renameFolder(id,folderName,newName);
    }

    @Override
    public void deleteFolder(int id, String folderName) {
     app.deleteFolder(id,folderName);
    }

    @Override
    public LinkedList<Contact> loadContacts(int id) {
        return app.loadContacts(id);
    }



}
