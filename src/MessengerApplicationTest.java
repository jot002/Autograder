/*
  Name: Your Name
  PID:  A12345678
 */

import java.time.LocalDate;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Messenger Application Test
 * @author TODO
 * @since  TODO
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

    @Test
    public void testCodeMessages3() {
        try {
            CodeMessage tm = new CodeMessage(person1, "PA02.zip", 5);
        } catch (OperationDeniedException ode) {
            assertEquals(INVALID_INPUT, ode.getMessage());
        }
    }

    @Test
    public void test2() {
        try {
            CodeMessage pm = new CodeMessage(person2, "PA02.yml", 5);
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            assertEquals(INVALID_CODE, ode.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test3() {
        try {
            CodeMessage pm = new CodeMessage(null, "PA02.yml", 5);
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            throw new IllegalArgumentException();

        }
    }

    @Test
    public void testCodeMessages2() {
        try {
            CodeMessage tm = new CodeMessage(person1, "PA02.yml", 11);
            String expected = "jtran [" + date + "]: Code at PA02.yml";
            //assertEquals(expected, tm.getContents());
            assertEquals("yml", tm.getExtension());
            assertEquals(11, tm.getLines());

            CodeMessage tm2 = new CodeMessage(person2, "PA22.yml", 15);
            String expected2 = "bobby [" + date + "]: Code at PA22.yml";
            //assertEquals(expected2, tm2.getContents());
            assertEquals("yml", tm2.getExtension());
            assertEquals(15, tm2.getLines());

            CodeMessage tm3 = new CodeMessage(person3, "PA03.html", 21);
            String expected3 = "<Tutor> ash [" + date + "]: Code at PA03.html";
            //assertEquals(expected3, tm3.getContents());
            assertEquals("html", tm3.getExtension());
            assertEquals(21, tm3.getLines());


        } catch (OperationDeniedException ode) {
            assertEquals(INVALID_INPUT, ode.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test5() {
        try {
            TextMessage pm = new TextMessage(null, "bye");
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            throw new IllegalArgumentException();

        }
    }

    @Test
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
            //assertEquals(expected, tm.getContents());
            assertEquals("jtran", tm.getSender().displayName());

            TextMessage tm2 = new TextMessage(person2, "A text message1.");
            String expected2 = "bobby [" + date + "]: A text message1.";
            //assertEquals(expected2, tm2.getContents());
            assertEquals("bobby", tm2.getSender().displayName());

            TextMessage tm3 = new TextMessage(person3, "A text message2.");
            String expected3 = "<Tutor> ash [" + date + "]: A text message2.";
            //assertEquals(expected3, tm3.getContents());
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

//    @Test(expected = OperationDeniedException.class)
//    public void testMessage3() {
//        String sentence1 = "I like dogs.";
//        String sentence2 = "you are crazy.";
//        String sentence3 = "that is not the answer.";
//        test4 = new CodeMessage(person3, sentence3, 7);
//        test5 = new CodeMessage(person2, sentence2, 8);
//
//    }

    @Test(expected = OperationDeniedException.class)
    public void testTextMessage2() {
        User person1 = new Student("jtran", "Student");
        User person2 = new Student("bobby", "Student");
        User person3 = new Tutor("ash", "Tutor");
        String sentence1 = "I like dogs.";
        String sentence2 = "you are crazy.";
        String sentence3 = "that is not the answer.";
        try {
            Message test4 = new CodeMessage(person3, sentence3, 7);
            Message test5 = new CodeMessage(person2, sentence2, 8);
        } catch (OperationDeniedException ODE) {
        }
//    @Test
//    public void testTextMessage() {
//        String sentence1 = "I like dogs.";
//        String sentence2 = "you are crazy.";
//        String sentence3 = "that is not the answer.";
//        User person1 = new Student("jtran", "Student");
//        User person2 = new Student("bobby", "Student");
//        User person3 = new Tutor("ash", "Tutor");
//        try {
//            Message test1 = new TextMessage(person1, sentence1);
//            Message test2 = new TextMessage(person2, sentence2);
//            Message test3 = new TextMessage(person3, sentence3);
//            Message test4 = new CodeMessage(person3, sentence3, 7);
//            Message test5 = new CodeMessage(person2, sentence2, 8);
//            Message test6 = new CodeMessage(person1, sentence1, 16);
//            assertEquals(person1, test1.getSender());
//            assertEquals(person2, test2.getSender());
//            assertEquals(person3, test3.getSender());
//            assertEquals(person3, test4.getSender());
//            assertEquals(person2, test5.getSender());
//            assertEquals(person1, test6.getSender());
//        }
//        catch (OperationDeniedException ODE) {
//        }
//        assertEquals("Student", person1.displayBio());
//    }

    }
}

