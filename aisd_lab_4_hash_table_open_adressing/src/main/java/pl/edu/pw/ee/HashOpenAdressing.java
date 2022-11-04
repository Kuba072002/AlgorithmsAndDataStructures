package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private int size;
    private int nElems;
    private T[] hashElems;
    private final double correctLoadFactor;
    private T deleted = (T) (new Comparable<T>() {
        public int compareTo(T arg0) {
            return -1;
        };
    });

    HashOpenAdressing() {
        this(2039);
    }

    HashOpenAdressing(int size) {
        validateHashInitSize(size);

        this.size = size;
        this.hashElems = (T[]) new Comparable[this.size];
        this.correctLoadFactor = 0.75;
    }

    @Override
    public void put(T newElem) {
        validateInputElem(newElem);
        resizeIfNeeded();

        int key = newElem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil && hashElems[hashId] != deleted) {
            i += 1;
            hashId = hashFunc(key, i);
        }
        if (newElem.equals(hashElems[hashId])) {
            hashElems[hashId] = newElem;
            return;
        }
        hashElems[hashId] = newElem;
        nElems++;
    }

    @Override
    public T get(T elem) {
        if (elem == null)
            throw new IllegalArgumentException("Argument cannot be null!");
        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil && hashElems[hashId].compareTo(elem) != 0) {
            i += 1;
            hashId = hashFunc(key, i);
        }

        return hashElems[hashId] == nil ? null : hashElems[hashId];
    }

    @Override
    public void delete(T elem) {
        if (elem == null)
            throw new IllegalArgumentException("Argument cannot be null!");
        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil && hashElems[hashId].compareTo(elem) != 0) {
            i += 1;
            hashId = hashFunc(key, i);
        }

        hashElems[hashId] = (T) deleted;
        nElems--;
    }

    private void validateHashInitSize(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException("Initial size of hash table cannot be lower than 1!");
        }
    }

    private void validateInputElem(T newElem) {
        if (newElem == null) {
            throw new IllegalArgumentException("Input elem cannot be null!");
        }
    }

    abstract int hashFunc(int key, int i);

    int getSize() {
        return size;
    }

    private void resizeIfNeeded() {
        double loadFactor = countLoadFactor();

        if (loadFactor >= correctLoadFactor) {
            doubleResize();
        }
    }

    private double countLoadFactor() {
        return (double) nElems / size;
    }

    private void doubleResize() {
        this.size *= 2;
        nElems = 0;

        T[] arr = hashElems;
        hashElems = (T[]) new Comparable[size];

        for (int i = 0; i < size / 2; i++) {
            if (arr[i] != nil && arr[i] != deleted)
                put(arr[i]);
        }
    }
}