import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @org.junit.jupiter.api.Test
    void getSender() {
        String sentence1 = "I like dogs.";
        String sentence2 = "you are crazy.";
        String sentence3 = "that is not the answer.";
        User person1 = new Student("jtran", "Student");
        User person2 = new Student("bobby", "Student");
        User person3 = new Tutor("ash", "Tutor");
        try {
            Message test1 = new TextMessage(person1, sentence1);
            Message test2 = new TextMessage(person2, sentence2);
            Message test3 = new TextMessage(person3, sentence3);
            Message test4 = new CodeMessage(person3, sentence3, 7);
            Message test5 = new CodeMessage(person2, sentence2, 8);
            Message test6 = new CodeMessage(person1, sentence1, 16);
            assertEquals(person1, test1.getSender());
            assertEquals(person2, test2.getSender());
            assertEquals(person3, test3.getSender());
            assertEquals(person3, test4.getSender());
            assertEquals(person2, test5.getSender());
            assertEquals(person1, test6.getSender());
            System.out.println(test1.getSender());
        }
        catch (OperationDeniedException ODE) {
        }
        System.out.println(person1.displayBio());
    }


    @org.junit.jupiter.api.Test
    void getContents() {
    }
}