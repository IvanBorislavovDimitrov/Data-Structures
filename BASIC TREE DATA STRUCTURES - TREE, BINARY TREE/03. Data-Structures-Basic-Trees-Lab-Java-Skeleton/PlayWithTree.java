public class PlayWithTree {

    public static void main(String[] args) {
//        Tree<Integer> tree =
//                new Tree<>(7,
//                        new Tree<>(19,
//                                new Tree<>(1),
//                                new Tree<>(12),
//                                new Tree<>(31)),
//                        new Tree<>(21),
//                        new Tree<>(14,
//                                new Tree<>(23),
//                                new Tree<>(6)));
//
//        System.out.println("*********************");
//        System.out.println("BFS");
//        Iterable<Integer> integers = tree.orderBFS();
//        for (Integer integer : integers) {
//            System.out.printf("%d ", integer);
//        }
//        System.out.println();
//        System.out.println("*********************\nDFS");
//
//        integers = tree.orderDFS();
//        for (Integer integer : integers) {
//            System.out.printf("%d ", integer);
//        }
//
//        System.out.println();

//        System.out.println("Tree nodes:");
//        tree.each(e -> System.out.print(" " + e));
//        System.out.println();
//
//        System.out.println();
//        BinaryTree<String> binaryTree =
//                new BinaryTree<>("*",
//                        new BinaryTree<>("+",
//                                new BinaryTree<>("3"),
//                                new BinaryTree<>("2")),
//                        new BinaryTree<>("-",
//                                new BinaryTree<>("9"),
//                                new BinaryTree<>("6")));
//
//        System.out.println("Binary tree (indented, pre-order):");
//        output = binaryTree.printIndentedPreOrder(0, new StringBuilder());
//        System.out.println(output);
//
//        System.out.println("Binary tree nodes (in-order):");
//        binaryTree.eachInOrder(e -> System.out.print(" " + e));
//        System.out.println();
//
//        System.out.println("Binary tree nodes (post-order):");
//        binaryTree.eachPostOrder(e -> System.out.print(" " + e));
//        System.out.println();



        BinaryTree<String> binaryTree =
                new BinaryTree<>("*",
                        new BinaryTree<>("+",
                                new BinaryTree<>("3"),
                                new BinaryTree<>("2")),
                        new BinaryTree<>("-",
                                new BinaryTree<>("9"),
                                new BinaryTree<>("6")));
        StringBuilder sb = new StringBuilder();
        binaryTree.printIndentedPreOrder(0, sb);
        System.out.println(sb);
    }
}
