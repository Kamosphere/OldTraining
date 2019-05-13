package com.thoughtworks.collection;

public class LinkSingle<T> implements SingleLink<T>{
    private class Node
    {
        private T data;
        private Node next;
        public Node()
        {
        }
        public Node(T data,Node next)
        {
            this.data = data;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    int size;

    public LinkSingle()
    {
        head = null;
        tail = null;
    }

    public LinkSingle (T data)
    {
        head = new Node(data,null);
        tail = head;
        size++;
    }

    public int size()
    {
        return size;
    }

    @Override
    public T getHeaderData()
    {
        return getNode(0);
    }

    @Override
    public T getTailData()
    {
        return getNode(this.size-1);
    }

    @Override
    public T getNode(int index){
        return getNodeState(index).data;
    }

    public Node getNodeState(int index)
    {
        if(index < 0 || index > size-1)
        {
            throw new IndexOutOfBoundsException();
        }
        Node current = head;
        for (int i = 0;i < size && current.next!=null;i++,current = current.next)
        {
            if(i == index)
            {
                return current;
            }
        }
        return null;
    }

    @Override
    public void addTailPointer(T item)
    {
        if(head==null)
        {
            head = new Node(item,null);
            tail = head;
        }
        else
        {
            Node newNode = new Node(item,null);
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void addHeadPointer(T item)
    {
        head = new Node(item,head);
        if(tail==null)
        {
            tail=head;
        }
    }

    public T delete (int index)
    {
        Node deleteNode = null;
        if(index<0||index>size-1)
        {
            throw new IndexOutOfBoundsException();
        }
        if(index==0)
        {
            deleteNode = head;
            head = head.next;
        }
        else
        {
            Node prev = getNodeState(index-1);
            deleteNode = prev.next;
            prev.next=deleteNode.next;
            deleteNode.next = null;
        }
        return deleteNode.data;
    }

    @Override
    public boolean deleteFirst()
    {
        if(delete(0)!=null){
            return true;
        }
        else return false;
    }

    @Override
    public boolean deleteLast()
    {
        if(delete(size-1)!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty()
    {
        return size==0;
    }
}