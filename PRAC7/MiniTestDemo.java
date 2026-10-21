
import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}

class MyTests {
    @Run
    public void testAdd() {
        System.out.println("Test addition passed");
    }

    @Run
    public void testSubtract() {
        System.out.println("Test subtraction passed");
    }

    public void normalMethod() {
        System.out.println("This method will not run");
    }
}

public class MiniTestDemo {
    public static void main(String[] args) throws Exception {
        MyTests tests = new MyTests();
        int count = 0;

        for (Method method : tests.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Run.class)) {
                method.invoke(tests);
                count++;
            }
        }

        System.out.println("Tests run: " + count);
    }
}
