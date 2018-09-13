import jdk.nashorn.api.tree.BinaryTree;
import jdk.nashorn.api.tree.Tree;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(1);

        tree.insert(2);
        tree.insert(0);
        tree.insert(4);

        tree.deleteMin();

        System.out.println();
    }
}
