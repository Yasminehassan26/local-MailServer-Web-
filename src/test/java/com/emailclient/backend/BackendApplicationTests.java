package com.emailclient.backend;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import EmailClient.Email;
import EmailClient.EmailBuilder;
import EmailClient.EmailClientProxy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import EmailClient.EmailClientProxy;
import UsersAndContacts.*;
import java.util.Date;
import java.util.LinkedList;

@SpringBootTest
class BackendApplicationTests {
    /**
     * test th sign up function
     */
    @Test
    public void testSignUp()  {
        //  create mock
        EmailClientProxy app = mock(EmailClientProxy.class);
        User yasmine = new User(0,"yasmine","hassan","noha@mail.com","123456","female","1/2/2000");
        // test when i enter an email already eneterd
        when(app.signUp(yasmine)).thenReturn(-1);
        // will return 0
        assertEquals(app.signUp(yasmine), -1);
        // test when i enter an email that doesn't exist
        User unknown = new User(0,"yasmine","hassan","unknown@mail.com","123456","female","1/2/2000");
        when(app.signUp(unknown)).thenReturn(3);
        // use mock in test....
        //will return the id of the user
        assertEquals(app.signUp(unknown), 3);
    }

    /**
     * test sign in function
     */
    @Test
    public void testSignIn()  {
        //  create mock
        EmailClientProxy app = mock(EmailClientProxy.class);

        // define return value for method
        when(app.signIn("noha@mail.com","111111")).thenReturn(String.valueOf(0));

        // use mock in test....
        assertEquals(app.signIn("noha@mail.com","111111"), String.valueOf(0));
    }


    @Test
    public void testCompose()  {
        //  create mock
        EmailClientProxy app = mock(EmailClientProxy.class);
        LinkedList<String> rec = new LinkedList<String>();
        rec.add("mariem@mail.com");
        Date date = new Date();
        Email mail = new EmailBuilder(0,1,"test", "hi","noha@mail.com",rec, date).build();

        // define return value for method
        when(app.compose(0,"Sent",mail)).thenReturn(new LinkedList<String>());

        // use mock in test....
        assertEquals(app.compose(0,"Sent",mail), new LinkedList<String>());

        //when it is not found
        LinkedList<String> rec1 = new LinkedList<String>();
        rec1.add("mariam@mail.com");
        Date date1 = new Date();
        Email mail1 = new EmailBuilder(0,1,"test", "hi","noha@mail.com",rec1, date1).build();
        // define return value for method
        LinkedList<String> error = new LinkedList<String>();
        error.add("mariam@mail.com"+" not found !");

        when(app.compose(0,"Sent",mail1)).thenReturn(error);

        // use mock in test....
        assertEquals(app.compose(0,"Sent",mail1), error);

    }

    @Test
    public void testAddFolder()  {
        //  create mock
        EmailClientProxy app = mock(EmailClientProxy.class);

        // define return value for method
        when(app.addFolder(0,"newFolder")).thenReturn("done");
        // use mock in test....
        assertEquals(app.addFolder(0,"newFolder"), "done");

        when(app.addFolder(0,"hello")).thenReturn("Folder Already exists");
        // use mock in test....
        assertEquals(app.addFolder(0,"hello"), "Folder Already exists");
    }

    @Test
    public void testRenameFolder()  {
        //  create mock
        EmailClientProxy app = mock(EmailClientProxy.class);
        when(app.renameFolder(0,"hello","rename")).thenReturn("done");
        // use mock in test....
        assertEquals(app.renameFolder(0,"hello","rename"), "done");

        when(app.renameFolder(0,"hello","new")).thenReturn("A folder with this name already exists");
        // use mock in test....
        assertEquals(app.renameFolder(0,"hello","new"), "A folder with this name already exists");
    }

    @Test
    public void testListEmails()  {
        //  create mock
        EmailClientProxy app = mock(EmailClientProxy.class);
        // load emails of empty folder
        when(app.listEmails(0,"Sent",1)).thenReturn(new LinkedList<Email>());
        assertEquals(app.listEmails(0,"Sent",1), new LinkedList<Email>());
        LinkedList<Email> test2 = new LinkedList<Email>();
        LinkedList<String> rec = new LinkedList<String>();
        rec.add("noha@mail.com");
        Date date=new Date();
        Email mail = new EmailBuilder(1,2,"red flower", "body of red  flower","mariem@mail.com",rec,date ).build();
        test2.add(mail);
        // load emails of empty folder
        when(app.listEmails(0,"Inbox",1)).thenReturn(test2);
        assertEquals(app.listEmails(0,"Inbox",1), test2);
    }



    @Test
    public void testLoadFolderName()  {
        //  create mock
        EmailClientProxy app = mock(EmailClientProxy.class);
        LinkedList<String> names = new LinkedList<String>();
        names.add("hello");
        names.add("new");
        // define return value for method
        when(app.loadFolderName(0)).thenReturn(names);
        // use mock in test....
        assertEquals(app.loadFolderName(0), names);
    }

    @Test
    public void testReturnUser()  {
        //  create mock
        EmailClientProxy app = mock(EmailClientProxy.class);
        User mariam = new User(0,"mariem","yousry","mariem@mail.com","111111","female","2020-12-08");
        // define return value for method
        when(app.returnUser(2)).thenReturn(mariam);
        // use mock in test....
        assertEquals(app.returnUser(2), mariam);
    }





}
