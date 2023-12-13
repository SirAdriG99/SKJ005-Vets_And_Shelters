package org.vetsandshelters.shared.DomainUtils;

/**
 * Collection class represents an abstract collection of objects
 * For now, we're only going to use getCollection() and size() methods
 * because with the paginator that we're going to use in the petitions,
 * sizes of the response is going to nbe limited and the order is going to
 * be applied in the petitions to collect the data.
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
