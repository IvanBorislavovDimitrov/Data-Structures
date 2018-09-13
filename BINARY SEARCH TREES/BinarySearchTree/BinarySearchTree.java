import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;

    public BinarySearchTree() {
    }

    public BinarySearchTree(T value) {
        this.root = new Node(value);
    }

    public Node getRoot() {
        return this.root;
    }

    public void insert(T value) {
        this.root = insert(this.root, value);
    }

    private Node insert(Node root, T value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }

        if (root.value.compareTo(value) > 0) {
            root.left = insert(root.left, value);
        } else if (root.value.compareTo(value) < 0) {
            root.right = insert(root.right, value);
        }

        return root;
    }

    public boolean contains(T value) {
        return contains(this.root, value);
    }

    private boolean contains(Node root, T value) {
        if (root != null) {
            if (root.value.equals(value)) {
                return true;
            }
            if (root.value.compareTo(value) > 0) {
                return contains(root.left, value);
            }

            return contains(root.right, value);
        }

        return false;
    }

    public Node search(T item) {
        return search(this.root, item);
    }

    private Node search(Node root, T item) {
        if (root == null || root.value.equals(item)) {
            return root;
        }

        if (root.value.compareTo(item) > 0) {
            return search(root.left, item);
        }

        return search(root.right, item);
    }

    public void eachInOrder(Consumer<T> consumer) {
        inOrder(this.root, consumer);
    }

    private void inOrder(Node root, Consumer<T> consumer) {
        if (root != null) {
            inOrder(root.left, consumer);
            consumer.accept(root.value);
            inOrder(root.right, consumer);
        }
    }

    public Iterable<T> range(T from, T to) {
        List<T> list = new ArrayList<>();
        range(list, this.root, from, to);

        return list;
    }

    private void range(List<T> list, Node root, T from, T to) {
        if (root != null) {
            if (root.value.compareTo(from) > 0 && root.value.compareTo(to) < 0) {
                list.add(root.value);
            }
            range(list, root.left, from, to);
            range(list, root.right, from, to);
        }
    }

    public void deleteMin() {
        if (this.root == null) {
            return;
        }

        Node parent = this.root;
        Node currentRoot = this.root;
        while (currentRoot.left != null) {
            parent = currentRoot;
            currentRoot = currentRoot.left;
        }

        if (parent == this.root) {
            this.root.left = null;
        } else {
            parent.left = null;
        }
    }

    class Node {
        private T value;
        private Node left;
        private Node right;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return this.value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return this.left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "Value " + this.value;
        }
    }
}

