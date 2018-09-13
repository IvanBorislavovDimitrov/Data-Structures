import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int treeSize = Integer.parseInt(input.readLine());
        List<int[]> nodes = new ArrayList<>();
        for (int i = 0; i < treeSize - 1; i++) {
            int[] parentChildInfo = Arrays.stream(input.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            nodes.add(parentChildInfo);
        }

        Tree<Integer> tree = createTree(nodes);

//        printRootNode(tree);
//        printTree(tree);
//        printLeafNodes(tree);
//        printMiddleNodes(tree);
//        printDeepestNode(tree);
//        printLongestNode(tree);
//        printAllPathsWithAGivenSum(tree, Integer.parseInt(input.readLine()));
        printAllSubtreesWithAGivenSum(tree, Integer.parseInt(input.readLine()));
    }

    private static void printAllSubtreesWithAGivenSum(Tree<Integer> root, int value) { // Problem 8. All Subtrees With a Given Sum
        System.out.println(String.format("Subtrees of sum %d:", value));
        findSubtreeDfs(root, value);
    }

    private static void findSubtreeDfs(Tree<Integer> root, int value) {
        List<Integer> subtree = new ArrayList<>();
        sumOfSubtree(root, subtree);
        int sum = subtree.stream().mapToInt(i -> i).sum();
        if (sum == value) {
            System.out.println(String.join(" ", subtree.stream().map(String::valueOf).toArray(String[]::new)));
        }

        for (Tree<Integer> child : root.getChildren()) {
            findSubtreeDfs(child, value);
        }
    }

    private static void sumOfSubtree(Tree<Integer> currentRoot, List<Integer> subtree) {
        subtree.add(currentRoot.getValue());
        for (Tree<Integer> child : currentRoot.getChildren()) {
            sumOfSubtree(child, subtree);
        }
    }

    private static void printAllPathsWithAGivenSum(Tree<Integer> root, int value) { // Problem 7. All Paths With a Given Sum
        List<Integer> nodeValues = new ArrayList<>();
        System.out.println(String.format("Paths of sum %d:", value));
        dfs(nodeValues, root, value);
    }

    private static void dfs(List<Integer> path, Tree<Integer> root, int value) {
        path.add(root.getRootNodeValue());
        if (path.stream().mapToInt(i -> i).sum() == value) {
            System.out.println(String.join(" ", path.stream().map(String::valueOf).toArray(String[]::new)));
        }
        for (Tree<Integer> child : root.getChildren()) {
            dfs(path, child, value);
        }

        path.remove(path.size() - 1);
    }

    private static void printLongestNode(Tree<Integer> tree) { // Problem 6. Longest path
        System.out.print("Longest path: ");
        System.out.println(String.join(" ", tree.longestPath().stream().map(String::valueOf).toArray(String[]::new)));
    }

    private static void printDeepestNode(Tree<Integer> tree) { // Problem 5. Deepest node
        System.out.println(String.format("Deepest node: %d", tree.deepestNode()));
    }

    private static void printMiddleNodes(Tree<Integer> tree) { // Problem 4. Middle nodes
        System.out.print("Middle nodes: ");
        System.out.println(String.join(" ", tree.middleNodes().stream().map(String::valueOf).toArray(String[]::new)));
    }

    private static void printLeafNodes(Tree<Integer> tree) { // Problem 3. Leaf nodes
        System.out.print("Leaf nodes: ");
        System.out.println(String.join(" ", tree.leafNodes().stream().map(String::valueOf).toArray(String[]::new)));
    }

    private static void printTree(Tree<Integer> tree) { // Problem 2. Print Tree
        StringBuilder sb = new StringBuilder();
        tree.printTree(sb);

        System.out.println(sb);
    }

    private static void printRootNode(Tree<Integer> tree) { // Problem 1. Root Node
        System.out.println(String.format("Root node: %d", tree.getRootNodeValue()));
    }

    private static Tree<Integer> createTree(List<int[]> nodes) {
        int root = nodes.get(0)[0];
        Tree<Integer> tree = new Tree<>(root);
        for (int[] node : nodes) {
            int parent = node[0];
            int child = node[1];
            tree.addChild(parent, child);
        }

        return tree;
    }
}
