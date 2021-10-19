/*
  Name: Your Name
  PID:  A12345678
 */

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Messenger Application Test
 * @author Jonathan Tran
 * @since  10/18/21
 */
public class MessengerApplicationTest {

    /*
      Messages defined in starter code. Remember to copy and paste these strings to the
      test file if you cannot directly access them. DO NOT change the original declaration
      to public.
     */
    private static final String INVALID_INPUT =
            "The source path is not valid.";

    // Error message to use in OperationDeniedException for the invalid line number
    private static final String INVALID_CODE =
            "The files are not long enough.";

    // Error message to use in OperationDeniedException
    private static final String EXCEED_MAX_LENGTH =
            "Your input exceeded the maximum length limit.";

    // input validation criteria
    private static final int MAX_TEXT_LENGTH = 500;

    /*
      Global test variables. Initialize them in @Before method.
     */
    Tutor marina;
    MessageExchange room;
    User person1;
    User person2;
    User person3;
    Message test1;
    Message test2;
    Message test3;
    Message test4;
    Message test5;
    Message test6;

    /*
      The date used in Message and its subclasses. You can directly
      call this in your test methods.
     */
    LocalDate date = LocalDate.now();

    /*
     * Setup
     */
    @Before
    public void setup() {
        marina = new Tutor("Marina", "Instructor");
        room = new Autograder(marina);
        person1 = new Student("jtran", "Student");
        person2 = new Student("bobby", "Student");
        person3 = new Tutor("ash", "Tutor");
    }

    /*
      Recap: Assert exception without message
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPremiumUserThrowsIAE() {
        marina = new Tutor("Marina", null);
    }

    /*
      Assert exception with message
     */
    @Test
    public void testPhotoMessageThrowsODE() {
        try {
            CodeMessage pm = new CodeMessage(marina, "PA02.zip", 10);
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            assertEquals(INVALID_INPUT, ode.getMessage());
        }
    }

    /*
     * Assert message content without hardcoding the date
     */
    @Test
    public void testTextMessageGetContents() {
        try {
            TextMessage tm = new TextMessage(marina, "A sample text message.");

            // concatenating the current date when running the test
            String expected = "<Tutor> Marina [" + date + "]: A sample text message.";
            assertEquals(expected, tm.getContents());
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        }
    }

    @org.junit.Test
    public void testCodeMessages3() {
        try {
            CodeMessage tm = new CodeMessage(person1, "PA02.zip", 5);
        } catch (OperationDeniedException ode) {
            assertEquals(INVALID_INPUT, ode.getMessage());
        }
    }

    @org.junit.Test
    public void test2() {
        try {
            CodeMessage pm = new CodeMessage(person2, "PA02.yml", 5);
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            assertEquals(INVALID_CODE, ode.getMessage());
        }
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void test3() {
        try {
            CodeMessage pm = new CodeMessage(null, "PA02.yml", 5);
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            throw new IllegalArgumentException();
        }
    }

    @org.junit.Test
    public void testCodeMessages2() {
        try {
            CodeMessage tm = new CodeMessage(person1, "PA02.yml", 11);
            String expected = "jtran [" + date + "]: Code at PA02.yml";
            assertEquals(expected, tm.getContents());
            assertEquals("yml", tm.getExtension());
            assertEquals(11, tm.getLines());

            CodeMessage tm2 = new CodeMessage(person2, "PA22.yml", 15);
            String expected2 = "bobby [" + date + "]: Code at PA22.yml";
            assertEquals(expected2, tm2.getContents());
            assertEquals("yml", tm2.getExtension());
            assertEquals(15, tm2.getLines());

            CodeMessage tm3 = new CodeMessage(person3, "PA03.html", 21);
            String expected3 = "<Tutor> ash [" + date + "]: Code at PA03.html";
            assertEquals(expected3, tm3.getContents());
            assertEquals("html", tm3.getExtension());
            assertEquals(21, tm3.getLines());

        } catch (OperationDeniedException ode) {
            assertEquals(INVALID_INPUT, ode.getMessage());
        }
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void test5() {
        try {
            TextMessage pm = new TextMessage(null, "bye");
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            throw new IllegalArgumentException();
        }
    }

    @org.junit.Test
    public void test7() {
        String longSentence = "";
        for (int i = 0; i < 550; i++) {
            longSentence += "o";
        }
        try {
            TextMessage pm = new TextMessage(person2, longSentence);
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            assertEquals(EXCEED_MAX_LENGTH, ode.getMessage());
        }
    }

    @Test
    public void testTextMessages2() {
        try {
            TextMessage tm = new TextMessage(person1, "A text message.");
            String expected = "jtran [" + date + "]: A text message.";
            assertEquals(expected, tm.getContents());
            assertEquals("jtran", tm.getSender().displayName());

            TextMessage tm2 = new TextMessage(person2, "A text message1.");
            String expected2 = "bobby [" + date + "]: A text message1.";
            assertEquals(expected2, tm2.getContents());
            assertEquals("bobby", tm2.getSender().displayName());

            TextMessage tm3 = new TextMessage(person3, "A text message2.");
            String expected3 = "<Tutor> ash [" + date + "]: A text message2.";
            assertEquals(expected3, tm3.getContents());
            assertEquals("<Tutor> ash", tm3.getSender().displayName());
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        }
    }

    @Test
    public void testTextMessageGetContents3() {
        String longSentence = "";
        for (int i = 0; i < 550; i++) {
            longSentence += "o";
        }
        try {
            TextMessage tm10 = new TextMessage(marina, longSentence);
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            assertEquals("Your input exceeded the maximum length limit.", ode.getMessage());
        }
    }

    @Test
    public void testingUser1() {
        User person1 = new Student("john1", "Student");
        User person2 = new Student("john2", "Student");
        User person3 = new Student("john3", "Student");
        Student person4 = new Student("john4", "Student");
        Tutor person5 = new Tutor("john5", "Student");
        Tutor person6 = new Tutor("john6", "Student");
        User person7 = new Tutor("john7", "Student");
        User person8 = new Tutor("john8", "Student");

        assertEquals("Student", person1.displayBio());
        assertEquals("Student", person2.displayBio());
        assertEquals("Student", person3.displayBio());
        assertEquals("Student", person4.displayBio());
        assertEquals("Student", person5.displayBio());
        assertEquals("Student", person6.displayBio());
        assertEquals("Student", person7.displayBio());
        assertEquals("Student", person8.displayBio());
        person1.setBio("person1");
        person2.setBio("person2");
        person3.setBio("person3");
        person4.setBio("person4");
        person5.setBio("person5");
        person6.setBio("person6");
        assertEquals("person1", person1.displayBio());
        assertEquals("person2", person2.displayBio());
        assertEquals("person3", person3.displayBio());
        assertEquals("person4", person4.displayBio());
        assertEquals("person5", person5.displayBio());
        assertEquals("person6", person6.displayBio());
        assertEquals("Student", person7.displayBio());
        assertEquals("Student", person8.displayBio());

        assertEquals("john1", person1.displayName());
        assertEquals("john2", person2.displayName());
        assertEquals("john3", person3.displayName());
        assertEquals("john4", person4.displayName());
        assertEquals("<Tutor> john5", person5.displayName());
        assertEquals("<Tutor> john6", person6.displayName());
        assertEquals("<Tutor> john7", person7.displayName());
        assertEquals("<Tutor> john8", person8.displayName());

        person5.setCustomTitle("TA");
        person6.setCustomTitle("Teacher");
        assertEquals("<TA> john5", person5.displayName());
        assertEquals("<Teacher> john6", person6.displayName());
        ArrayList<User> allUsers = new ArrayList<User>();
        allUsers.add(person1);
        allUsers.add(person2);
        allUsers.add(person3);
        allUsers.add(person4);
        person5.createAutograder(allUsers);
        MessageExchange me1 = person5.createAutograder(allUsers);
        person6.createAutograder(allUsers);
        MessageExchange me2 = person5.createAutograder(allUsers);
        allUsers.add(person7);
        MessageExchange me3 = person5.createAutograder(allUsers);
        try {
            person1.joinRoom(me1);
            person2.joinRoom(me1);
            person3.joinRoom(me1);
            person1.joinRoom(me2);
            person2.joinRoom(me2);
            person3.joinRoom(me2);
            person5.joinRoom(me1);
            person5.joinRoom(me2);
            person6.joinRoom(me3);
        } catch (OperationDeniedException ode) {
            ode.getMessage();
        }
//        person1.sendMessage(me1, "hi", 1);
//        person1.sendMessage(me1, "sup", 1);
//        person2.sendMessage(me1, "bye", 1);
//        person1.fetchMessage(me1);
//        person2.fetchMessage(me1);
        person1.quitRoom(me1);
        person1.quitRoom(me2);
        person5.quitRoom(me1);
        person5.quitRoom(me2);

        Autograder tutor1 = new Autograder(person5);
        tutor1.addUser(person1);
        tutor1.addUser(person2);
        tutor1.addUser(person3);
        tutor1.addUser(person6);
        tutor1.getLog(person5);
        tutor1.getLog(person2);
        tutor1.getLog(person3);
        tutor1.getLog(person5);

        ArrayList<User> allUsers2 = new ArrayList<User>();
        allUsers2.add(person5);
        allUsers2.add(person1);
        allUsers2.add(person2);
        allUsers2.add(person3);
        allUsers2.add(person6);
        assertEquals(allUsers2, tutor1.getUsers());
        assertEquals(true, tutor1.removeUser(person5, person1));
        assertEquals(true, tutor1.removeUser(person5, person2));
        assertEquals(false, tutor1.removeUser(person5, person6));

        ArrayList<User> allUsers3 = new ArrayList<User>();
        allUsers3.add(person5);
        allUsers3.add(person3);
        allUsers3.add(person6);
        assertEquals(allUsers3, tutor1.getUsers());
        try {
            assertEquals(" ", tutor1.resolveTicket(person1));
            assertEquals(" ", tutor1.resolveTicket(person2));
            assertEquals(" ", tutor1.resolveTicket(person3));
        } catch (OperationDeniedException ode) {
            ode.getMessage();
        }
        ArrayList<String> empty = new ArrayList<String>();
        assertEquals(empty, tutor1.getResults());

//        ArrayList<User> allUsers2 = new ArrayList<User>();
//        allUsers2.add(person3);
//        assertEquals(allUsers2, tutor1.getUsers());
        Autograder tutor2 = new Autograder(person6);
        assertEquals(empty, tutor2.getResults());

        Autograder tutor3 = new Autograder(person6);
        assertEquals(empty, tutor3.getResults());
        try {
            TextMessage tm = new TextMessage(person1, "A text message.");
            TextMessage tm2 = new TextMessage(person1, "A text message.");
            TextMessage tm3 = new TextMessage(person1, "A text message.");
            assertEquals(true, tutor1.recordMessage(tm));
            assertEquals(true, tutor1.recordMessage(tm2));
            assertEquals(true, tutor1.recordMessage(tm3));
            //person1.sendMessage(tutor1,"sup", 11);
//            person2.sendMessage(tutor1,"sup", 11);
//            person3.sendMessage(tutor1,"sup", 11);
//            tutor1.fetchMessage(tm);

        } catch (OperationDeniedException ode) {
            ode.getMessage();
        }


        assertEquals(true, tutor1.stopSession());
        assertEquals(false, tutor1.stopSession());
        assertEquals(false, tutor1.stopSession());

    }


    @org.junit.Test(expected = IllegalArgumentException.class)
    public void failTest() {
        Student man1 = new Student("man1", "man");
        man1.fetchMessage(null);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void failTest2() {
        Tutor man2 = new Tutor("man1", "man");
        man2.fetchMessage(null);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void failTest3() {
        Tutor man3 = new Tutor("man1", "man");
        man3.fetchMessage(null);
    }

    @org.junit.Test(expected = OperationDeniedException.class)
    public void failTest4() {
        Tutor man3 = new Tutor("man1", "man");
        Student man7 = new Student("j", "ahh");
        Autograder man4 = new Autograder(man3);
        try {
            man4.resolveTicket(man7);
        } catch (OperationDeniedException ode) {
            ode.getMessage();
        }

    }
    @org.junit.Test(expected = OperationDeniedException.class)
    public void failTest5() {
        Tutor man3 = new Tutor("man1", "man");
        Student man7 = new Student("j", "ahh");
        Autograder man4 = new Autograder(man3);
        try {
            man4.resolveTicket(man7);
        } catch (OperationDeniedException ode) {
            ode.getMessage();
        }

    }
}