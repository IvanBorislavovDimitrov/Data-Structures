import java.util.function.Consumer;

public class BinaryTree<T extends Comparable<T>> {

    private T value;
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;

    public BinaryTree(T value) {
        this.value = value;
    }

    public BinaryTree(T value, BinaryTree<T> child) {
        this.value = value;
        if (this.value.compareTo(child.value) > 0) {
            this.leftChild = child;
        } else {
            this.rightChild = child;
        }
    }

    public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    // append output to builder
    public String printIndentedPreOrder(int indent, StringBuilder builder) {
        preOrder(this, indent, builder);

        return builder.toString();
    }

    private static String repeatString(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }

    private void preOrder(BinaryTree<T> root, int indent, StringBuilder builder) {
        if (root != null) {
            builder.append(repeatString(2 * indent)).append(root.value).append("\n");
            preOrder(root.leftChild, indent + 1, builder);
            preOrder(root.rightChild, indent + 1, builder);
        }
    }

    public void eachInOrder(Consumer<T> consumer) {
        inOrder(this, consumer);
    }

    private void inOrder(BinaryTree<T> root, Consumer<T> consumer) {
        if (root != null) {
            inOrder(root.leftChild, consumer);
            consumer.accept(root.value);
            inOrder(root.rightChild, consumer);
        }
    }

    public void eachPostOrder(Consumer<T> consumer) {
        postOrder(this, consumer);
    }

    private void postOrder(BinaryTree<T> root, Consumer<T> consumer) {
        if (root != null) {
            postOrder(root.leftChild, consumer);
            postOrder(root.rightChild, consumer);
            consumer.accept(root.value);
        }
    }

}
