import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.*;
import static org.mockito.Matchers.*;
import org.junit.*;
import org.mockito.*;

public class CatTest {

    //this tests that a cat who isn't currently rented is successfully rented
    //by some guy named Steve
    @Test
    public void rentCatTest1(){
        Cat cat = new Cat(1, "Cat", 20, false);
        assertFalse(cat.getRented());
        cat.rentCat("Steve");

        assertTrue(cat.getRented());
        assertEquals(cat.getRenter(), "Steve");
    }

    //this tests that the method rentCat overrides any previous rental
    //information even though the program doesn't allow this
    @Test
    public void rentCatTest2(){
        Cat cat = new Cat(1, "Cat", 20, false);
        cat.rentCat("Steve");
        assertEquals(cat.getRenter(), "Steve");
        assertTrue(cat.getRented());

        cat.rentCat("Terry");
        assertEquals(cat.getRenter(), "Terry");
        assertTrue(cat.getRented());
    }
}
