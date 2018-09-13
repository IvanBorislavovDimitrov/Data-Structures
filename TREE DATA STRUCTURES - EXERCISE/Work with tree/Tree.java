import java.util.*;

public class Tree<T extends Comparable<T>> {

    private static final String PARENT_NOT_FOUND_MESSAGE = "Parent not found!";

    private T value;
    private Tree<T> parent;
    private List<Tree<T>> children;

    public Tree(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public Tree(T value, Tree<T>... children) {
        this.value = value;
        this.children = new ArrayList<>();
        this.children.addAll(Arrays.asList(children));
    }

    public List<T> longestPath() {
        Tree<T> deepestNode = deepestNode(this);
        List<T> path = new ArrayList<>();
        while (deepestNode != null) {
            path.add(deepestNode.value);
            deepestNode = deepestNode.parent;
        }

        Collections.reverse(path);

        return path;
    }

    public T deepestNode() {
        return deepestNode(this).value;
    }

    private Tree<T> deepestNode(Tree<T> root) { // reversed bfs
        Queue<Tree<T>> elements = new ArrayDeque<>();
        elements.offer(root);
        Tree<T> lastElement = root;
        while (!elements.isEmpty()) {
            Tree<T> currentElement = elements.poll();
            lastElement = currentElement;
            for (int i = currentElement.children.size() - 1; i >= 0; i--) {
                elements.offer(currentElement.children.get(i));
            }
        }

        return lastElement;
    }

    public List<T> middleNodes() {
        List<T> middleNodes = new ArrayList<>();
        middleNodes(this, middleNodes);

        middleNodes.sort(Comparable::compareTo);

        return middleNodes;
    }

    private void middleNodes(Tree<T> root, List<T> middleNodes) {
        if (root.parent != null && root.children.size() != 0) {
            middleNodes.add(root.value);
        }

        for (Tree<T> child : root.children) {
            middleNodes(child, middleNodes);
        }
    }

    public List<T> leafNodes() {
        List<T> leafNodes = new ArrayList<>();
        leafNodes(this, leafNodes);

        leafNodes.sort(Comparable::compareTo);

        return leafNodes;
    }

    private void leafNodes(Tree<T> root, List<T> leafNodes) {
        if (root.children.size() == 0) {
            leafNodes.add(root.value);
        }
        for (Tree<T> child : root.children) {
            leafNodes(child, leafNodes);
        }
    }

    public String printTree(StringBuilder sb) {
        printTree(this, sb, 0);

        return sb.toString();
    }

    private void printTree(Tree<T> root, StringBuilder sb, int offset) {
        sb.append(repeatString(2 * offset)).append(root.value).append("\n");
        for (Tree<T> child : root.children) {
            printTree(child, sb, offset + 1);
        }
    }

    private String repeatString(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }

    public T getRootNodeValue() {
        return this.value;
    }

    public void addChild(T parentValue, T childValue) {
        Tree<T> parent = search(parentValue);
        if (parent == null) {
            throw new IllegalArgumentException(PARENT_NOT_FOUND_MESSAGE);
        } else {
            Tree<T> child = new Tree<>(childValue);
            child.parent = parent;
            parent.children.add(child);
        }
    }

    public Tree<T> search(T value) {
        return search(this, value);
    }

    private Tree<T> search(Tree<T> root, T value) {
        Queue<Tree<T>> elements = new ArrayDeque<>();
        elements.offer(root);
        while (!elements.isEmpty()) {
            Tree<T> currentNode = elements.poll();
            if (currentNode.value.equals(value)) {
                return currentNode;
            }

            elements.addAll(currentNode.children);
        }

        return null;
    }

    public static String getParentNotFoundMessage() {
        return PARENT_NOT_FOUND_MESSAGE;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Tree<T> getParent() {
        return this.parent;
    }

    public void setParent(Tree<T> parent) {
        this.parent = parent;
    }

    public List<Tree<T>> getChildren() {
        return this.children;
    }

    public void setChildren(List<Tree<T>> children) {
        this.children = children;
    }
}
