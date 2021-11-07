package base;

import org.junit.jupiter.api.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TodoListApplicationTest {

    @Test
    void testFileParsing_numItems() throws FileNotFoundException {
        String filePath = "./testing/Test_in.txt";
        Scanner fileIn = new Scanner(new FileInputStream(filePath));
        int numItems = fileIn.nextInt();
        assertEquals(3, numItems);
    }

    @Test
    void testFileParsing_itemID() throws FileNotFoundException {
        String filePath = "./testing/Test_in.txt";
        Scanner fileIn = new Scanner(new FileInputStream(filePath));

        fileIn.nextInt();
        int id = fileIn.nextInt();
        assertEquals(-1952933945, id);
    }

    @Test
    void testFileParsing_itemName() throws FileNotFoundException {
        String filePath = "./testing/Test_in.txt";
        Scanner fileIn = new Scanner(new FileInputStream(filePath));

        fileIn.nextInt();
        fileIn.nextLine();
        fileIn.nextInt();
        fileIn.nextLine();
        String itemName = fileIn.nextLine();
        assertEquals("Test 1", itemName);
    }

    @Test
    void testFileParsing_itemDate() throws FileNotFoundException {
        String filePath = "./testing/Test_in.txt";
        Scanner fileIn = new Scanner(new FileInputStream(filePath));

        fileIn.nextInt();
        fileIn.nextLine();
        fileIn.nextInt();
        fileIn.nextLine();
        fileIn.nextLine();
        String itemDate = fileIn.nextLine();
        assertEquals("2000-01-01", itemDate);
    }

    @Test
    void testFileParsing_itemDetails() throws FileNotFoundException {
        String filePath = "./testing/Test_in.txt";
        Scanner fileIn = new Scanner(new FileInputStream(filePath));

        fileIn.nextInt();
        fileIn.nextLine();
        fileIn.nextInt();
        fileIn.nextLine();
        fileIn.nextLine();
        fileIn.nextLine();
        String itemDetails = fileIn.nextLine();
        assertEquals("This is an example item", itemDetails);
    }

    @Test
    void testFileParsing_itemStatus() throws FileNotFoundException {
        String filePath = "./testing/Test_in.txt";
        Scanner fileIn = new Scanner(new FileInputStream(filePath));

        fileIn.nextInt();
        fileIn.nextLine();
        fileIn.nextInt();
        fileIn.nextLine();
        fileIn.nextLine();
        fileIn.nextLine();
        fileIn.nextLine();
        boolean itemStatus = fileIn.nextBoolean();
        assertFalse(itemStatus);
    }

    ToDoItemTest testItem = new ToDoItemTest("Test item", LocalDate.of(2002, Month.JUNE, 16), "Test Details", true);

    @Test
    void testItemNameRetrieval() {
        assertEquals("Test item", testItem.getItemName());
    }

    @Test
    void testItemDateRetrieval() {
        assertEquals(LocalDate.of(2002, Month.JUNE, 16), testItem.getItemDueDate());
    }

    @Test
    void testItemDateRetrievalAsString() {
        assertEquals("2002-06-16", testItem.getItemDueDateString());
    }

    @Test
    void testItemDetailRetrieval(){
        assertEquals("Test Details", testItem.getItemDetails());
    }

    @Test
    void testItemSelectionRetrieval() {
        assertTrue(testItem.getCompletionStatusBoolean());
    }

    //Test basic functionality of index retrieval methodology
    @Test
    void testIDIndexRetrieval() {
        ArrayList<ToDoItemTest> testArrayList = new ArrayList<>();
        ToDoItemTest testItem1 = new ToDoItemTest("Test item 1", LocalDate.of(2002, Month.JUNE, 16), "Test Details", true);
        ToDoItemTest testItem2 = new ToDoItemTest("Test item 2", LocalDate.of(2002, Month.JUNE, 16), "Test Details", true);
        ToDoItemTest testItem3 = new ToDoItemTest("Test item 3", LocalDate.of(2002, Month.JUNE, 16), "Test Details", true);
        testArrayList.add(testItem1);
        testArrayList.add(testItem2);
        testArrayList.add(testItem3);

        int actualIndex = -1;

        for(int i = 0; i < testArrayList.size(); i++) {
            if(testArrayList.get(i).getItemID() == testItem2.getItemID()){
                actualIndex = i;
            }
        }

        assertEquals(1, actualIndex);
    }

    boolean validateTextLengthTest(int size){
        return size >= 1 && size <= 256;
    }

    @Test
    void testValidateTextLengthFalse(){
        int textSize = 0;
        boolean isProperLength = validateTextLengthTest(textSize);
        assertFalse(isProperLength);
    }

    @Test
    void testValidateTextLengthTrue(){
        int textSize = 10;
        boolean isProperLength = validateTextLengthTest(textSize);
        assertTrue(isProperLength);
    }

}