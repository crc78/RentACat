import java.util.*;
import java.io.BufferedReader;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.*;
import static org.mockito.Matchers.*;
import org.junit.*;
import org.mockito.*;

@SuppressWarnings("unchecked")
public class RentCatTest {

    //this tests that if no cats are currently rented they all get printed out
    @Test
    public void listTest1(){
        RentACat rac = new RentACat();
        List<Cat> mockCats = mock(ArrayList.class);
        Cat mockCat = mock(Cat.class);

        when(mockCats.get(anyInt())).thenReturn(mockCat);
        when(mockCat.isRented()).thenReturn(false, false, false);

        rac.list(mockCats);
        verify(mockCat, times(3)).tooString();
    }

    //this test verifies that the program prints only the 2 cats in the list
    //that are not currently being rented
    @Test
    public void listTest2(){
        RentACat rac = new RentACat();
        List<Cat> mockCats = mock(ArrayList.class);
        Cat mockCat = mock(Cat.class);

        when(mockCats.get(anyInt())).thenReturn(mockCat);
        when(mockCat.isRented()).thenReturn(false, true, false);

        rac.list(mockCats);
        verify(mockCat, times(2)).tooString();
    }

    //this tests that if all cats are currently rented then the method
    //doens't print any of them out
    @Test
    public void listTest3(){
        RentACat rac = new RentACat();
        List<Cat> mockCats = mock(ArrayList.class);
        Cat mockCat = mock(Cat.class);

        when(mockCats.get(anyInt())).thenReturn(mockCat);
        when(mockCat.isRented()).thenReturn(true, true, true);

        rac.list(mockCats);
        verify(mockCat, times(0)).tooString();
    }

    //this tests that createCats method creates 3 cat objects and pushes them
    //to the array list of cats
    @Test
    public void createCatsTest1(){
        RentACat rac = new RentACat();
        List<Cat> spyCats = spy(new ArrayList());
        spyCats = rac.createCats(spyCats);
        verify(spyCats, times(3)).add(any(Cat.class));
        assertEquals(spyCats.size(), 3);
    }

    //this tests that createCats method creates 3 cat objects and pushes them
    //to the array list of cats which already contains some cat objects
    @Test
    public void createCatsTest2(){
        RentACat rac = new RentACat();
        List<Cat> spyCats = spy(new ArrayList());
        Cat mockCat = mock(Cat.class);
        for(int i=0; i<5; i++) {
            spyCats.add(mockCat);
        }

        spyCats = rac.createCats(spyCats);
        verify(spyCats, times(8)).add(any(Cat.class));
        assertEquals(spyCats.size(), 8);
    }

    //this tests that the createCustomers method creates 3 customer objects and
    //pushes them to the array list of customers
    @Test
    public void createCustomersTest1(){
        RentACat rac = new RentACat();
        List<Customer> spyCustomers = spy(new ArrayList());
        spyCustomers = rac.createCustomers(spyCustomers);
        verify(spyCustomers, times(3)).add(any(Customer.class));
        assertEquals(spyCustomers.size(), 3);
    }

    //this tests that the createCustomers method creates 3 customer objects and
    //pushes them to the array list of customers which already contains
    //some customer objects
    @Test
    public void createCustomersTest2(){
        RentACat rac = new RentACat();
        List<Customer> spyCustomers = spy(new ArrayList());
        Customer mockCustomer = mock(Customer.class);
        for(int i=0; i<5; i++) {
            spyCustomers.add(mockCustomer);
        }

        spyCustomers = rac.createCustomers(spyCustomers);
        verify(spyCustomers, times(8)).add(any(Customer.class));
        assertEquals(spyCustomers.size(), 8);
    }

    //this tests that the ask method returns -1 (invalid option selection) when
    //given 0 and 5 because the valid options are 1 through 4
    @Test
    public void askTest1() throws Exception {
        RentACat rac = new RentACat();
        BufferedReader mockScan = mock(BufferedReader.class);
        when(mockScan.readLine()).thenReturn("0", "5");

        int result1 = rac.ask(mockScan);
        int result2 = rac.ask(mockScan);

        assertEquals(result1, -1);
        assertEquals(result2, -1);
    }

    //this tests that the ask method says a random string is an invalid
    //selection
    @Test
    public void askTest2() throws Exception {
        RentACat rac = new RentACat();
        BufferedReader mockScan = mock(BufferedReader.class);
        when(mockScan.readLine()).thenReturn("asdfasdfasdfasdf asdf asdf asdf asdf");

        int result = rac.ask(mockScan);
        assertEquals(result, -1);
    }

    //this tests that selection 1 through 4 are valid options
    @Test
    public void askTest3() throws Exception {
        RentACat rac = new RentACat();
        BufferedReader mockScan = mock(BufferedReader.class);
        when(mockScan.readLine()).thenReturn("1", "2", "3", "4");

        int result = rac.ask(mockScan);
        assertEquals(result, 1);
        result = rac.ask(mockScan);
        assertEquals(result, 2);
        result = rac.ask(mockScan);
        assertEquals(result, 3);
        result = rac.ask(mockScan);
        assertEquals(result, 4);
    }

    //this tests that a cat is successfully rented after inputting bad ids then
    //good ids for both customer and cat
    @Test
    public void rentCatTest1() throws Exception {
        RentACat rac = new RentACat();
        BufferedReader mockScan = mock(BufferedReader.class);
        List<Customer> mockCustomers = mock(ArrayList.class);
        Customer mockCustomer = mock(Customer.class);
        List<Cat> mockCats = mock(ArrayList.class);
        Cat mockCat = mock(Cat.class);
        when(mockCats.get(0)).thenReturn(mockCat);
        when(mockScan.readLine()).thenReturn("0", "asdf", "2", "0", "asdf", "1");

        when(mockCustomers.get(1)).thenReturn(mockCustomer);

        boolean result = rac.rentCat(mockScan, mockCats, mockCustomers);
        assertTrue(result);
    }

    //this tests that once a cat is successfully rented that if someone tries
    //to rent the same cat then it won't let them
    @Test
    public void rentCatTest2() throws Exception {
        RentACat rac = new RentACat();
        BufferedReader mockScan = mock(BufferedReader.class);
        List<Customer> mockCustomers = mock(ArrayList.class);
        List<Cat> mockCats = mock(ArrayList.class);
        Customer mockCustomer = mock(Customer.class);
        Cat mockCat = mock(Cat.class);

        when(mockScan.readLine()).thenReturn("1", "1", "asdf", "2", "1");
        when(mockCats.get(0)).thenReturn(mockCat);
        when(mockCustomers.get(0)).thenReturn(mockCustomer);
        when(mockCustomers.get(1)).thenReturn(mockCustomer);
        when(mockCat.isRented()).thenReturn(false, true);

        assertTrue(rac.rentCat(mockScan, mockCats, mockCustomers));
        assertFalse(rac.rentCat(mockScan, mockCats, mockCustomers));
    }

    //this tests that someone can succesfully rent all 3 cats if they aren't
    //already rented
    @Test
    public void rentCatTest3() throws Exception {
        RentACat rac = new RentACat();
        BufferedReader mockScan = mock(BufferedReader.class);
        List<Customer> mockCustomers = mock(ArrayList.class);
        List<Cat> mockCats = mock(ArrayList.class);
        Customer mockCustomer = mock(Customer.class);
        Cat mockCat = mock(Cat.class);

        when(mockScan.readLine()).thenReturn("1", "1", "1", "2", "1", "3");
        when(mockCats.get(0)).thenReturn(mockCat);
        when(mockCats.get(1)).thenReturn(mockCat);
        when(mockCats.get(2)).thenReturn(mockCat);
        when(mockCustomers.get(0)).thenReturn(mockCustomer);
        when(mockCat.isRented()).thenReturn(false, false, false);

        assertTrue(rac.rentCat(mockScan, mockCats, mockCustomers));
        assertTrue(rac.rentCat(mockScan, mockCats, mockCustomers));
        assertTrue(rac.rentCat(mockScan, mockCats, mockCustomers));
    }

    //this tests that after inputting a wrong cat id then a correct id for
    //a rented cat that returnCat successfully returned the cat
    @Test
    public void returnCatTest1() throws Exception {
        RentACat rac = new RentACat();
        BufferedReader mockScan = mock(BufferedReader.class);
        List<Cat> mockCats = mock(ArrayList.class);
        Cat mockCat = mock(Cat.class);

        when(mockScan.readLine()).thenReturn("asdf", "1");
        when(mockCats.get(0)).thenReturn(mockCat);
        when(mockCat.isRented()).thenReturn(true);

        assertTrue(rac.returnCat(mockScan, mockCats));
    }

    //this tests that after inputting a wrong cat id then a correct id for a
    //cat that isn't rented then the method returns false to signify that
    //no cat was successfully returned
    @Test
    public void returnCatTest2() throws Exception {
        RentACat rac = new RentACat();
        BufferedReader mockScan = mock(BufferedReader.class);
        List<Cat> mockCats = mock(ArrayList.class);
        Cat mockCat = mock(Cat.class);

        when(mockScan.readLine()).thenReturn("asdf", "1");
        when(mockCats.get(0)).thenReturn(mockCat);
        when(mockCat.isRented()).thenReturn(false);

        assertFalse(rac.returnCat(mockScan, mockCats));
    }

    //this tests that if a cat is successfully returned then it cannot be
    //returned again until it's been rented again
    @Test
    public void returnCatTest3() throws Exception {
        RentACat rac = new RentACat();
        BufferedReader mockScan = mock(BufferedReader.class);
        List<Cat> mockCats = mock(ArrayList.class);
        Cat mockCat = mock(Cat.class);

        when(mockScan.readLine()).thenReturn("1", "1");
        when(mockCats.get(0)).thenReturn(mockCat, mockCat);
        when(mockCat.isRented()).thenReturn(true, false);

        assertTrue(rac.returnCat(mockScan, mockCats));
        assertFalse(rac.returnCat(mockScan, mockCats));
    }

}
