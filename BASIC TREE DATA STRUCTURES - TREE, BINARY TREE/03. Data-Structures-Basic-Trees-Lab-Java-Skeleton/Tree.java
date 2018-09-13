import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {

    private T value;
    private List<Tree<T>> children;

    public Tree(T value, Tree<T>... children) {
        this.value = value;
        this.children = new ArrayList<>();
        this.children.addAll(Arrays.asList(children));
    }

    public String print(int indent, StringBuilder builder) {
        System.out.print(repeatString(2 * indent, " "));
        System.out.println(this.value);
        builder.append(repeatString(2 * indent, " "));
        builder.append(this.value).append("\n");

        for (Tree<T> child : this.children) {
            child.print(indent + 1, builder);
        }

        return builder.toString();
    }

    private String repeatString(int count, String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(text);
        }

        return sb.toString();
    }

    public void each(Consumer<T> consumer) {
        consumer.accept(this.value);
        for (Tree<T> child : this.children) {
            child.each(consumer);
        }
    }

    public Iterable<T> orderDFS() {
        List<T> tree = new ArrayList<>();
        dfs(tree, this);

        return tree;
    }

    private void dfs(List<T> tree, Tree<T> root) {
        for (Tree<T> child : root.children) {
            dfs(tree, child);
        }
        tree.add(root.value);
    }

    private void bfs(List<T> tree) {
        Queue<Tree<T>> queue = new LinkedList<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            Tree<T> node = queue.poll();
            tree.add(node.value);
            for (Tree<T> child : node.children) {
                queue.offer(child);
            }
        }

    }

    public Iterable<T> orderBFS() {
        List<T> tree = new ArrayList<>();
        bfs(tree);

        return tree;
    }

}