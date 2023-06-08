
public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;
    String drawOutput;

    public BinaryTree() {
        root = null;
    }

    public void insert(T value) {
        root = ins(value, root);
    }

    private Node<T> ins(T value, Node<T> w) {
        if (w == null)
            return new Node<T>(value);
        if (value.compareTo(w.value) < 0)
            w.left = ins(value, w.left);
        else if (value.compareTo(w.value) > 0)
            w.right = ins(value, w.right);
        return w;
    }

    public boolean isElement(T value) {
        return isElem(value, root);
    }

    private boolean isElem(T value, Node<T> w) {
        if (w == null)
            return false;
        if (value.compareTo(w.value) == 0)
            return true;
        if (value.compareTo(w.value) < 0)
            return isElem(value, w.left);
        else
            return isElem(value, w.right);
    }

    public String toString() {
        return toS(root);
    }

    private String toS(Node<T> w) {
        if (w != null)
            return "(" + w.value + ":" + toS(w.left) + ":" + toS(w.right) + ")";
        return "()";
    }

    public void delete(T value) {
        root=deleteNode(root, value);
    }

    public Node<T> deleteNode(Node<T> w, T key) {

        if (w == null)
            return w;
        if (key.compareTo(w.value) > 0) { // move right
            w.right = deleteNode(w.right, key);
        } else if (key.compareTo(w.value) < 0) { // move left
            w.left = deleteNode(w.left, key);
        } else { // oh yes, we finally found the target
            if (w.left == null && w.right == null) { // hmm, its a leaf node; easy peasy
                w = null;
            } else if (w.right != null) { // oh, it has a right child, don't make it an orphan or is it old enough to
                                          // become a parent ? lets find out
                w.value = successor(w); // my worthy successor
                w.right = deleteNode(w.right, w.value);
            } else { // oh it seems that I do not have a worthy successor, fallback, fallback ...
                w.value = predecessor(w);
                w.left = deleteNode(w.left, w.value);
            }
        }
        return w;
    }

    /**
     * Return node's successor value
     * 
     * @param root
     * @return
     */
    private T successor(Node<T> w) {
        w = w.right;
        while (w.left != null) {
            w = w.left;
        }
        return w.value;
    }

    /**
     * Return node's predecessor value
     * 
     * @param root
     * @return
     */
    private T predecessor(Node<T> w) {
        w = w.left;
        while (w.right != null) {
            w = w.right;
        }
        return w.value;
    }

    private int level(Node<T> w) {
        if (w == null)
            return 0;
        return Math.max(level(w.left), level(w.right)) + 1;
    }

    public String draw() {
        
        if (root == null)
            return "";
        drawOutput = "";
        printLevelOrder();
        System.out.println(drawOutput);
        return drawOutput;

    }

    private void printLevelOrder() {
        int h = level(root);
        int i;
        for (i = 1; i <= h; i++) {
            printCurrentLevel(root, i);
            drawOutput += '*';
        }
    }

    private void printCurrentLevel(Node<T> w, int level) {
        if (w == null)
            return;
        if (level == 1)
            drawOutput += w.value + "       ";
        else if (level > 1) {

            printCurrentLevel(w.left, level - 1);
            printCurrentLevel(w.right, level - 1);
        }
    }
}

class Node<T extends Comparable<T>> {
    T value;
    Node<T> left;
    Node<T> right;

    Node(T value) {
        this.value = value;
        left = null;
        right = null;
    }

    /*
     * public String toString() {
     * return value.toString();
     * }
     */
}
