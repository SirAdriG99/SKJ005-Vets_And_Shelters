package org.vetsandshelters.shared.DomainUtils;

/**
 * Collection class represents an abstract collection of objects
 */
public abstract class Collection<T> {
    private T[] collection;

    public Collection(T[] collection) {
        this.collection = collection;
    }

    public T[] getCollection() {
        return collection;
    }

    public int size() {
        return collection.length;
    }

    // TODO: Implement method to detect the type of the object
}
