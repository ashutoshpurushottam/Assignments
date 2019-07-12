
import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class Permutation {

    public static void main(String[] args) {
        int j = Integer.parseInt(args[0]);

        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            queue.enqueue(item);
        }

        Iterator<String> iterator = queue.iterator();

        for (int i = 0; i < j; ++i) {
            System.out.println(iterator.next());
        }

    }
}
