import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Object[] array = new Object[500];
        for (int i = 0; i < 500; i++) {
            array[i] = i;
        }

        CircularQueue<Object> queue = new CircularQueue<>();

        for (int i = 0; i < array.length; i++) {
            queue.enqueue(array[i]);
        }

        queue.forEach(x -> {
            if ((Integer) x % 2 == 1) {
                System.out.println(x);
            }
        });

    }

}
