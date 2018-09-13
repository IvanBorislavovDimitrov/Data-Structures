import org.junit.Assert;

public class Main {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < 100; i++) {
            list.addFirst(i);
        }

        for (Integer i = 0; i < 100; i++) {
            Assert.assertEquals(i, list.removeLast());
        }

        for (Integer i : list) {
            System.out.println(i);
        }
    }
}
