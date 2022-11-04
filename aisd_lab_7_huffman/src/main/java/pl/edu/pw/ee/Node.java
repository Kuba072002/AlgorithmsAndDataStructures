package pl.edu.pw.ee;

public class Node implements Comparable<Node> {
    private final int frequency;
    private final char sign;
    private Node leftChild;
    private Node rightChild;

    public Node(int frequency, char sign) {
        this.frequency = frequency;
        this.sign = sign;
    }

    public void setChildren(Node leftchild, Node rightchild) {
        this.leftChild = leftchild;
        this.rightChild = rightchild;
    }

    public void setLeftChild(Node leftchild) {
        this.leftChild = leftchild;
    }

    public void setRightChild(Node rightchild) {
        this.rightChild = rightchild;
    }

    public int getFrequency() {
        return frequency;
    }

    public char getSign() {
        return sign;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public boolean isLeaf() {
        return (leftChild == null) && (rightChild == null);
    }

    @Override
    public int compareTo(Node o) {
        if (frequency > o.getFrequency())
            return 1;
        else if (frequency < o.getFrequency())
            return -1;
        return 0;
    }
}