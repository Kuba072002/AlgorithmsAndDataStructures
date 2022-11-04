package pl.edu.pw.ee;

import java.util.ArrayList;

import pl.edu.pw.ee.services.HashTable;

public class HashListChaining<T extends Comparable<T>> implements HashTable<T> {

    private final Elem nil = null;
    private ArrayList<Elem> hashElems;
    private int nElem;

    private class Elem {
        private T value;
        private Elem next;

        Elem(T value, Elem nextElem) {
            this.value = value;
            this.next = nextElem;
        }
    }

    public HashListChaining(int size) {
        hashElems = new ArrayList<>(size);
        initializeHash(size);
    }

    @Override
    public void add(T value) {
        if (value == null)
            throw new IllegalArgumentException("Value cannot be null");
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem oldElem = hashElems.get(hashId);
        while (oldElem != nil && !oldElem.value.equals(value)) {
            oldElem = oldElem.next;
        }
        if (oldElem != nil) {
            oldElem.value = value;
        } else {
            hashElems.set(hashId, new Elem(value, hashElems.get(hashId)));
            nElem++;
        }
    }

    @Override
    public T get(T value) {
        if (value == null)
            throw new IllegalArgumentException("Value cannot be null");
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem elem = hashElems.get(hashId);

        while (elem != nil && !elem.value.equals(value)) {
            elem = elem.next;
        }

        return elem != nil ? elem.value : null;
    }

    @Override
    public void delete(T value) {
        if (value == null)
            throw new IllegalArgumentException("Value cannot be null");
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem elem = hashElems.get(hashId);

        if (elem == nil)
            return;
        if (elem.value.equals(value)) {
            hashElems.set(hashId, hashElems.get(hashId).next);
            nElem--;
            return;
        }
        while (elem.next != nil && !elem.next.value.equals(value))
            elem = elem.next;
        if (elem.next != nil) {
            elem.next = elem.next.next;
            nElem--;
        }
    }

    public double countLoadFactor() {
        double size = hashElems.size();
        return nElem / size;
    }

    private void initializeHash(int size) {
        for (int i = 0; i < size; i++) {
            hashElems.add(nil);
        }
    }

    private int countHashId(int hashCode) {
        int n = hashElems.size();
        return Math.abs(hashCode) % n;
    }
}