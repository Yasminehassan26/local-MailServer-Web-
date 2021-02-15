package EmailClient;
import UsersAndContacts.*;
import java.util.LinkedList;
import java.io.File;

import jsonOperations.UserJson;

public class Verify {

    UserJson jsonReadWrite = new UserJson();

    public  String correctUser(String email, String password, LinkedList<User> users) {
        if(users != null){
        for(int i = 0; i < users.size(); i++) {
            User user =  users.get(i);
            String userEmail = user.getEmail();
            String userPass = user.getPassword();
            if(email.equals(user.getEmail())) {
                if(password.equals(user.getPassword())) {
                    //email exists and correct pass
                    return Integer.toString(user.getId());
                }
                else {
                    // email exist but wrong pass
                    return "Wrong password entered!";
                }
            }
        }}
        else{
            return "not a registered email !!";
        }
        // email doesn't exist
        return "Not a registered email!";
    }
    public  Boolean checkEmail(String email, LinkedList<User> users) {
        if(users!=null){
        for(int i = 0; i < users.size(); i++) {
            User user =  users.get(i);
            if(email.equals(user.getEmail())) {
                return false;
            }
        }}
        return true;
    }
    public String checkReceiver(String receiver) {
        File userIndex = new File("Accounts\\users.json");
        LinkedList<User> users = jsonReadWrite.readFromJson(userIndex);
        if(users!=null){
            for(int i = 0; i < users.size(); i++) {
                User user =  users.get(i);
                if(receiver.equals(user.getEmail())) {
                    return Integer.toString(user.getId());
                }
            }
        }
        String error=receiver+" not found !";
        return error;
    }
}
