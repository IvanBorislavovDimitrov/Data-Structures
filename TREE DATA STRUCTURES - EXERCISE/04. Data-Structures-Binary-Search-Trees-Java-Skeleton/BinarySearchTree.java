import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {

    private static final String TREE_IS_EMPTY_MESSAGE = "Tree is empty";

    private Node root;
    private int count;

    public BinarySearchTree() {
    }

    public BinarySearchTree(T value) {
        this.root = new Node(value);
        this.count++;
    }

    public void delete(T value) {
        deleteRec(this.root, value);
    }

    private Node deleteRec(Node node, T soughtValue) {
        if (node == null) {
            return null;
        }
        int comparison = soughtValue.compareTo(node.value);
        if (comparison < 0) {
            node.left = deleteRec(node.right, soughtValue);
        } else if (comparison > 0) {
            node.right = deleteRec(node.right, soughtValue);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node leftsRightmost = node.left;
                while (leftsRightmost.right != null) {
                    leftsRightmost = leftsRightmost.right;
                }
                leftsRightmost.right = node.right;
                return node.left;
            }
        }
        return node;
    }

    public T ceiling(T value) {
        Node root = this.root;
        if (root.value.compareTo(value) > 0) {
            Node searched = null;
            int closeValue = Integer.MIN_VALUE;
            while (root.left != null) {
                int currentClose = root.value.compareTo(value);
                if (closeValue < currentClose) {
                    closeValue = currentClose;
                    searched = root;
                }
                root = root.left;
            }

            return searched == null ? null : searched.value;
        } else if (root.value.compareTo(value) < 0) {
            Node searched = null;
            int closeValue = Integer.MIN_VALUE;
            while (root.right != null) {
                int compare = root.value.compareTo(value);
                if (closeValue < compare) {
                    closeValue = compare;
                    searched = root;
                }

                root = root.right;
            }

            return searched == null ? null : searched.value;
        }

        return null;
    }

    public T floor(T value) {
        Node root = this.root;
        if (root.value.compareTo(value) > 0) {
            Node searched = null;
            int closeValue = Integer.MAX_VALUE;
            while (root.left != null) {
                int currentClose = root.value.compareTo(value);
                if (closeValue >= currentClose) {
                    closeValue = currentClose;
                    searched = root;
                }
                root = root.left;
            }

            return searched == null ? null : searched.value;
        } else if (root.value.compareTo(value) < 0) {
            Node searched = null;
            int closeValue = Integer.MAX_VALUE;
            while (root.right != null) {
                int compare = root.value.compareTo(value);
                if (closeValue >= compare) {
                    closeValue = compare;
                    searched = root;
                }

                root = root.right;
            }

            return searched == null ? null : searched.value;
        }

        return null;
    }

    public T select(T value) {
        return select(this.root, value);
    }

    private T select(Node root, T value) {
        if (root != null) {
            Integer rank = rank(root.value);
            if (rank.equals(value)) {
                return root.value;
            }

            T element = select(root.left, value);
            if (element == null) {
                element = select(root.right, value);
            }

            return element;
        }

        return null;
    }

    public Node getRoot() {
        return this.root;
    }

    public int rank(T value) {
        return count(this.root, value);
    }

    private int count(Node root, T value) {
        if (root == null) {
            return 0;
        }
        int compare = value.compareTo(root.value);
        if (compare <= 0) {
            return count(root.left, value);
        }

        return 1 + count(root.left, value) + count(root.right, value);
    }

    public void deleteMax() {
        if (this.root == null) {
            throw new IllegalArgumentException(TREE_IS_EMPTY_MESSAGE);
        }

        Node currentNode = this.root;
        Node prevNode = this.root;

        while (currentNode.right != null) {
            prevNode = currentNode;
            currentNode = currentNode.right;
        }

        prevNode.right = null;
        this.count--;
    }

    public void insert(T value) {
        this.root = insert(this.root, value);
        this.count++;
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


        parent.left = null;
        this.count--;
    }

    public int getCount() {
        return this.count;
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

