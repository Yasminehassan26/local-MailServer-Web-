
package com.emailclient.backend;

import EmailClient.Email;
import EmailClient.EmailClientProxy;
import UsersAndContacts.Contact;
import UsersAndContacts.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/Api")
public class Controller {
    EmailClientProxy app =new EmailClientProxy();
    final String fileSeparator=System.getProperty("file.separator");

    //user validation requests
    @GetMapping("/signInValidation")
    public String signInValidation(@RequestParam(name="email") String email , @RequestParam(name="password") String password){
        return app.signIn(email,password);

    }

    @PostMapping(
            value = "/signUpValidation" , consumes = "application/json" , produces = "text/plain"
    )
    public String signUpValidation(@RequestBody Map<String, Object> data ){
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.convertValue(data, User.class);
        return Integer.toString(app.signUp(user));
    }

    @GetMapping("/getUserInfo")
    public User getUserInfo(@RequestParam(name="id") int id){
        return app.returnUser(id);
    }


    //emails manipulation requests
    @GetMapping("/loadEmails")
    public LinkedList<Email> loadEmails(@RequestParam(name="id") int id ,@RequestParam(name="folderName") String folderName ,@RequestParam(name="page") int page ){

        return app.listEmails(id,folderName,page);
    }

    @GetMapping("/loadSearchedEmails")
    public LinkedList<Email> loadSearchedEmails(@RequestParam(name="id") int id ,@RequestParam(name="folderName") String folderName ,@RequestParam(name="page") int page,@RequestParam(name="search") String search ,@RequestParam(name="sort") String sort,@RequestParam(name="filterSubject") String filterSubject ,@RequestParam(name="filterSender") String filterSender  ){
        System.out.println("hi");
        String[] filter ={filterSubject ,filterSender};
        return app.searchedEmails(id,folderName,page,search,sort,filter);

    }

    @PostMapping(
            value = "/sendEmail" , consumes = "application/json" , produces =  "application/json"
    )
    public LinkedList<String> sendEmail( @RequestBody Map<String, Object> data ){
        ObjectMapper objectMapper = new ObjectMapper();
        int id = (int)(data.get("id"));
        String folderName = (String)(data.get("folderName"));
        Email email = objectMapper.convertValue(data.get("email"), Email.class);
        LinkedList<String> test = app.compose(id,folderName,email);
        System.out.println(test.size());
        return test ;
    }

    @DeleteMapping (
            value = "/deleteEmails" , consumes = "application/json"
    )
    public void deleteEmails (@RequestBody Map<String, Object> data  ){
        System.out.println("hi");
        int id = (int)(data.get("id"));
        String folderName = (String)(data.get("folderName"));
        ArrayList<Integer> emailsIds = (ArrayList<Integer>)(data.get("emailsIds"));
        LinkedList<Integer> ids = new LinkedList<Integer>();
        for(int i = 0 ; i < emailsIds.size() ; i++ ){
            ids.add(emailsIds.get(i));
        }
        app.deleteEmails(id,folderName,ids);
    }

    @PostMapping (
            value = "/moveEmails" , consumes = "application/json"
    )
    public void moveEmails (@RequestBody Map<String, Object> data  ){
        System.out.println("hi");
        int id = (int)(data.get("id"));
        String fromFolder = (String)(data.get("fromFolder"));
        String toFolder = (String)(data.get("toFolder"));
        ArrayList<Integer> emailsIds = (ArrayList<Integer>)(data.get("emailsIds"));
        LinkedList<Integer> ids = new LinkedList<Integer>();
        for(int i = 0 ; i < emailsIds.size() ; i++ ){
            ids.add(emailsIds.get(i));
        }
        app.moveEmails(id,fromFolder,toFolder,ids);
    }

    @PostMapping (
            value = "/copyEmails" , consumes = "application/json"
    )
    public void copyEmails (@RequestBody Map<String, Object> data  ){
        System.out.println("hi");
        int id = (int)(data.get("id"));
        String fromFolder = (String)(data.get("fromFolder"));
        String toFolder = (String)(data.get("toFolder"));
        ArrayList<Integer> emailsIds = (ArrayList<Integer>)(data.get("emailsIds"));
        LinkedList<Integer> ids = new LinkedList<Integer>();
        for(int i = 0 ; i < emailsIds.size() ; i++ ){
            ids.add(emailsIds.get(i));
        }
        app.copyEmails(id,fromFolder,toFolder,ids);
    }

    //folders manipulation requests

    @GetMapping("/loadFolders")
    public LinkedList<String> loadFolders(@RequestParam(name="id") int id){
        LinkedList<String> folders = app.loadFolderName(id);

        return app.loadFolderName(id);
    }


    @PostMapping(
            value = "/addFolder" , consumes = "application/json" , produces = "text/plain"
    )
    public String addFolder (@RequestBody Map<String, Object> data){
        System.out.println(data.get("id"));
        int id = (int)(data.get("id"));
        String folderName = (String)(data.get("folderName"));
        return app.addFolder(id,folderName);
    }

    @PutMapping(
            value = "/renameFolder" , consumes = "application/json" , produces = "text/plain"
    )
    public String renameFolder (@RequestBody Map<String, Object> data){
        int id = (int)(data.get("id"));
        String folderName = (String)(data.get("folderName"));
        String newFolderName = (String)(data.get("newName"));
        return app.renameFolder(id,folderName,newFolderName);
    }

    @DeleteMapping (
            value = "/deleteFolder" , consumes = "application/json"
    )
    public void deleteFolder (@RequestBody Map<String, Object> data  ){
        int id = (int)(data.get("id"));
        String folderName = (String)(data.get("folderName"));
        app.deleteFolder(id,folderName);
    }

    //contacts manipulations requests
    @GetMapping("/loadContacts")
    public LinkedList<Contact> loadContacts(@RequestParam(name="id") int id){
        LinkedList<Contact> contacts = app.loadContacts(id);

        return contacts;
    }

    @PostMapping(
            value = "/addContact" , consumes = "application/json"
    )
    public void addContact (@RequestBody Map<String, Object> data){
        System.out.println("hi");
        ObjectMapper objectMapper = new ObjectMapper();
        int id = (int)(data.get("id"));
        Contact contact = objectMapper.convertValue(data.get("contact"), Contact.class);
        app.addContact(id,contact);
    }

    @DeleteMapping (
            value = "/deleteContact" , consumes = "application/json"
    )
    public void deleteContact (@RequestBody Map<String, Object> data  ){
        int id = (int)(data.get("id"));
        int contactId = (int)(data.get("contactId"));
        app.deleteContact(id,contactId);
    }

    @PutMapping(
            value = "/renameContact" , consumes = "application/json" , produces = "text/plain"
    )
    public void renameContact (@RequestBody Map<String, Object> data){
        int id = (int)(data.get("id"));
        int contactId = (int)(data.get("contactId"));
        String name = (String)(data.get("name"));
        app.changeContactName(id,contactId,name);
    }

    @PutMapping(
            value = "/editContactEmails" , consumes = "application/json" , produces = "text/plain"
    )
    public void editContactEmails (@RequestBody Map<String, Object> data){
        int id = (int)(data.get("id"));
        int contactId = (int)(data.get("contactId"));
        ArrayList<String> loadedEmails = (ArrayList<String>)(data.get("emails"));
        LinkedList<String> emails = new LinkedList<String>();
        for(int i = 0 ; i < loadedEmails.size() ; i++ ){
            emails.add(loadedEmails.get(i));
        }
        app.addContactEmail(id,contactId,emails);
    }


    //Attachements manipulation requests

    @PostMapping(value="/saveAttachment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String sendAttach(@RequestParam("file") MultipartFile file){
        String path="Accounts\\Attachments\\"+file.getOriginalFilename();
        multipartFileToFile(file, "Accounts\\Attachments\\");
        return path;
    }
    public void multipartFileToFile(MultipartFile multipart, String dir) {
        Path filepath = Paths.get(dir, multipart.getOriginalFilename());
        try {
            multipart.transferTo(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value="/Download", consumes = "application/json")
    public void download(@RequestBody String path){
        System.out.println("downloadHere");
        System.out.println(path);
        String sourcePath="";
        String fileName="";
        for(int i=0;i<path.length();i++){
            if(path.charAt(i)!='"'){
                sourcePath=sourcePath+path.charAt(i);
                if(i>=24){
                    fileName=fileName+path.charAt(i);
                }
            }
        }
        String destinationPath=System.getProperty("user.home")+ fileSeparator +"Downloads"+fileSeparator+fileName;
        transfer(sourcePath,destinationPath);
    }

    public void transfer(String source,String destination){
        File sourceFile = new File(source);
        File destinationFile = new File(destination);
        FileOutputStream outputStream= null;
        FileInputStream inputStream=null;
        try {
            outputStream = new FileOutputStream(destinationFile);
            inputStream=new FileInputStream(sourceFile);
            byte []buffer = new byte[4096];
            int bytesRead = -1;
            while((bytesRead=inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
