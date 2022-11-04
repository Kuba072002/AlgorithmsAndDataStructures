package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    private double a = 7;
    private double b = 5;

    public HashQuadraticProbing() {
        super();
    }

    public HashQuadraticProbing(int size, double a, double b) {
        super(size);
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (int) (key % m + a * i + b * i * i);
        hash %= m;

        hash = hash < 0 ? -hash : hash;

        return hash;
    }
}
