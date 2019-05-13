package com.thoughtworks.collection;

public interface SingleLink<T> {
    public T getHeaderData();
    public T getTailData();
    public int size();
    public boolean isEmpty();
    public boolean deleteFirst();
    public boolean deleteLast();
    public void addHeadPointer(T item);
    default public void addTailPointer(T item){

    }
    public T getNode(int index);
}
