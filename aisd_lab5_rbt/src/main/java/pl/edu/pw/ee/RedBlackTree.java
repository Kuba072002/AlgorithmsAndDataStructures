package pl.edu.pw.ee;

import static pl.edu.pw.ee.Color.BLACK;
import static pl.edu.pw.ee.Color.RED;

public class RedBlackTree<K extends Comparable<K>, V> {

    private Node<K, V> root;
    private String resultString;

    public V get(K key) {
        validateKey(key);
        Node<K, V> node = root;

        V result = null;

        while (node != null) {

            if (shouldCheckOnTheLeft(key, node)) {
                node = node.getLeft();

            } else if (shouldCheckOnTheRight(key, node)) {
                node = node.getRight();

            } else {
                result = node.getValue();
                break;
            }
        }
        return result;
    }

    public void put(K key, V value) {
        validateParams(key, value);
        root = put(root, key, value);
        root.setColor(BLACK);
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
    }

    private boolean shouldCheckOnTheLeft(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private boolean shouldCheckOnTheRight(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void validateParams(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Input params (key, value) cannot be null.");
        }
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        }

        if (isKeyBiggerThanNode(key, node)) {
            putOnTheRight(node, key, value);

        } else if (isKeySmallerThanNode(key, node)) {
            putOnTheLeft(node, key, value);

        } else {
            node.setValue(value);
        }

        node = reorganizeTree(node);

        return node;
    }

    private boolean isKeyBiggerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void putOnTheRight(Node<K, V> node, K key, V value) {
        Node<K, V> rightChild = put(node.getRight(), key, value);
        node.setRight(rightChild);
    }

    private boolean isKeySmallerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private void putOnTheLeft(Node<K, V> node, K key, V value) {
        Node<K, V> leftChild = put(node.getLeft(), key, value);
        node.setLeft(leftChild);
    }

    private Node<K, V> reorganizeTree(Node<K, V> node) {
        node = rotateLeftIfNeeded(node);
        node = rotateRightIfNeeded(node);
        changeColorsIfNeeded(node);

        return node;
    }

    private Node<K, V> rotateLeftIfNeeded(Node<K, V> node) {
        if (isBlack(node.getLeft()) && isRed(node.getRight())) {
            node = rotateLeft(node);
        }
        return node;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> head = node.getRight();
        node.setRight(head.getLeft());
        head.setLeft(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private Node<K, V> rotateRightIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }
        return node;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> head = node.getLeft();
        node.setLeft(head.getRight());
        head.setRight(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private void changeColorsIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            changeColors(node);
        }
    }

    private void changeColors(Node<K, V> node) {
        node.setColor(RED);
        node.getLeft().setColor(BLACK);
        node.getRight().setColor(BLACK);
    }

    private boolean isBlack(Node<K, V> node) {
        return !isRed(node);
    }

    private boolean isRed(Node<K, V> node) {
        return node == null ? false : node.isRed();
    }

    public void deleteMax() {
        if (root == null)
            throw new ArrayIndexOutOfBoundsException("Rbt is empty");
        root = deleteMax(root);
        if (root != null)
            root.setColor(BLACK);
    }

    private Node<K, V> deleteMax(Node<K, V> node) {
        if (isRed(node.getLeft()))
            node = rotateRight(node);
        if (node.getRight() == null)
            return null;
        if (!isRed(node.getRight()) && !isRed(node.getRight().getLeft())) {
            reverseColors(node);
            if (isRed(node.getLeft().getLeft())) {
                node = rotateRight(node);
                reverseColors(node);
            }
        }
        node.setRight(deleteMax(node.getRight()));
        return reorganizeTree(node);
    }

    private void reverseColors(Node<K, V> node) {
        if (node.getColor() == BLACK)
            node.setColor(RED);
        else {
            node.setColor(BLACK);
        }
        if (node.getLeft().getColor() == BLACK)
            node.getLeft().setColor(RED);
        else {
            node.getLeft().setColor(BLACK);
        }
        if (node.getRight().getColor() == BLACK)
            node.getRight().setColor(RED);
        else {
            node.getRight().setColor(BLACK);
        }
    }

    public String getPreOrder() {
        if (root == null)
            return "Rbt is empty";
        resultString = "";
        getPreOrderMethod(root);
        return resultString;
    }

    private void getPreOrderMethod(Node<K, V> node) {
        resultString += " " + node.toString();
        if (node.getLeft() == null && node.getRight() == null) {
            resultString += " " + node.toString();
            return;
        }
        if (node.getLeft() != null)
            getPostOrderMethod(node.getLeft());
        if (node.getRight() != null)
            getPostOrderMethod(node.getRight());
    }

    public String getInOrder() {
        if (root == null)
            return "Rbt is empty";
        resultString = "";
        getInOrderMethod(root);
        return resultString;
    }

    private void getInOrderMethod(Node<K, V> node) {
        if (node.getLeft() == null && node.getRight() == null) {
            resultString += " " + node.toString();
            return;
        }
        if (node.getLeft() != null)
            getInOrderMethod(node.getLeft());
        resultString += " " + node.toString();
        if (node.getRight() != null)
            getInOrderMethod(node.getRight());
    }

    public String getPostOrder() {
        if (root == null)
            return "Rbt is empty";
        resultString = "";
        getPostOrderMethod(root);
        return resultString;
    }

    private void getPostOrderMethod(Node<K, V> node) {
        if (node.getLeft() == null && node.getRight() == null) {
            resultString += " " + node.toString();
            return;
        }
        if (node.getLeft() != null)
            getPostOrderMethod(node.getLeft());
        if (node.getRight() != null)
            getPostOrderMethod(node.getRight());
        resultString += " " + node.toString();
    }
}
