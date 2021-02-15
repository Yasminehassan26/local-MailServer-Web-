import EmailClient.Email;
import EmailClient.EmailClientProxy;
import UsersAndContacts.*;

import java.util.LinkedList;


public class test {
    public static void main(String[] args){
        EmailClientProxy app = new EmailClientProxy();

       /* User yasmine = new User(0,"yasmine","hassan","yasmine@mail.com","12345","female","1/2");

        User mariam = new User(0,"mariam","Youssri","mariam@mail.com","12345","female","1/2");

        System.out.println("sign up 0"+app.signUp(yasmine));
        System.out.println("sign up 1"+app.signUp(mariam));
        System.out.println(" sign in 0 "+app.signIn("yasmine@mail.com","12345")); // 0
        System.out.println("sign in 1 "+app.signIn("mariam@mail.com","12345")); //1

        Date now = new Date();
        LinkedList<String> rec = new LinkedList<String>();
        rec.add("mariam@mail.com");

        Email m = new EmailBuilder(0,0,"one","hgfhsdf","yasmine",rec,now).build(); //0

        Email m1= new EmailBuilder(1,0,"two","hg","mariam",rec,now).build(); //1

        Email m3= new EmailBuilder(2,0,"three","hg","noha",rec,now).build();  //2

        Email m4= new EmailBuilder(3,0,"hey","hg","menna",rec,now).build();  //3




        app.compose(0,"Sent",m);//2
          app.compose(0,"Sent",m1);//1

        app.compose(0,"Sent",m3);//2
        app.compose(0 ,"Draft",m4);//3
       /* Date now = new Date();
        LinkedList<String> rec = new LinkedList<String>();
        rec.add("mariam@mail.com");
        System.out.println(" sign in 0 "+app.signIn("yasmine@mail.com","12345")); // 0
        Email m = new EmailBuilder(0,0,"one","hgfhsdf","mariam",rec,now).build(); //0
        app.compose(0,"Sent",m); //0
       Date now1 = new Date();
        LinkedList<String> rec = new LinkedList<String>();
        rec.add("yasmine@mail.com");
       System.out.println(" sign in 0 "+app.signIn("yasmine@mail.com","12345")); // 0
        Email m7 = new EmailBuilder(0,0,"one","hgfhsdf","yasmine@mail.com",rec,now1).build(); //0
        app.compose(0,"Sent",m7); //0

        */
        // testing contacts
     /*   LinkedList<String> rec = new LinkedList<String>();
        rec.add("mariam@mail.com");
        LinkedList<String> rec2 = new LinkedList<String>();
        rec.add("ma2riam@mnoham");
        System.out.println(" sign in 0 "+app.signIn("yasmine@mail.com","12345")); // 0
        Contact one = new Contact("mariam",rec);
        Contact two = new Contact("noha",rec2);
        ContactManipulator man = new ContactManipulator();
        man.addContact(0, two);
        man.addContact(0, one);

        LinkedList<Contact> testone = man.sortContacts(0);
        for(int i = 0; i < testone.size(); i++){
            System.out.println(testone.get(i).getName());
        }


        LinkedList<Contact> testtwo = man.searchForContacts(0, "mariam");
        for(int i = 0; i < testtwo.size(); i++){
            System.out.println(testtwo.get(i).getName());

        }
*/
        // testing sort filter and search
        System.out.println(" sign in 0 "+app.signIn("noha@mail.com","111111")); // 0
        String[] filterBy = new String[2];
        filterBy[0] = "";
        filterBy[1] = "";
        LinkedList<Email> filtered = app.searchedEmails(1,"Inbox",1,"mariem","Date",filterBy);
        for(int i = 0; i < filtered.size(); i++){
            System.out.println(filtered.get(i).getSender()+"-----"+filtered.get(i).getSubject());
        }

       /* app.addFolder(0,"yasmine");
        app.addFolder(0,"Mariam");
        app.addFolder(0,"zft");
        app.renameFolder(0,"Mariam","Marioma");
        app.deleteFolder(0,"zft");

        LinkedList<String> names = app.loadFolderName(0);
        for(int i = 0; i < names.size(); i++){
            System.out.println(names.get(i));
        }
        System.out.println("test load");
        LinkedList<Email> test2 = app.listEmails(0,"Sent",1);
        for(int i = 0; i < test2.size(); i++){
            System.out.println(test2.get(i).getSubject());
        }
        System.out.println("test copy");
        LinkedList<Integer> idss=new LinkedList<>();
        idss.add(0);
        idss.add(2);
        idss.add(4);
        app.copyEmails(0,"Sent","yasmine",idss);*/

      /*  LinkedList<Email> test = app.listEmails(1,"Sent",1);
        for(int i = 0; i < test.size(); i++){
            System.out.println(test.get(i).getSender());
        }
        LinkedList<Integer> ids = new LinkedList<Integer>();
        ids.add(2);
        ids.add(3);
        app.deleteEmails(1,"Sent",ids);
        LinkedList<Email> test2 = app.listEmails(1,"Sent",1);
        for(int i = 0; i < test2.size(); i++){
            System.out.println(test2.get(i).getSender());
        }*/
       /* LinkedList<Email> test3 = app.listEmails(1,"Trash",1);
        for(int i = 0; i < test3.size(); i++){
            System.out.println(test3.get(i).getSender());
        }
*/

    }
}
