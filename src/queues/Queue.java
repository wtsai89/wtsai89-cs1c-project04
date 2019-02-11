package queues;

import java.util.*;

/**
 * Manage items in a singly linked list where we can enqueue() items to the end and dequeue() items from the front of the queue.
 * @param <E>
 */
public class Queue<E> implements Iterable<E> {
    private Node head, tail; //head points to the front of the queue. tail points to the end of the queue.
    private String name;
    private int size;

    /**
     * Constructor
     * @param str
     */
    public Queue(String str)
    {
        head = null;
        tail = null;
        name = str;
        size = 0;
    }

    /**
     * Takes a generic item as the argument and adds the item to the end of the queue.
     * @param x
     */
    public void enqueue(E x)
    {
        if(size == 0)
        {
            tail = new Node(x, null);
            head = tail;
        }
        else
        {
            Node newTail = new Node(x, null);
            tail.next = newTail;
            tail = newTail;
        }
        size++;
    }

    /**
     * Removes the item from the front of the queue
     * @return
     * @throws NoSuchElementException
     */
    public E dequeue()
    {
        if(size == 0)
            throw new NoSuchElementException();
        E x = head.data;
        head = head.next;
        size--;
        return x;
    }

    /**
     *  Looks at the least recently added item of the queue and returns an object of the generic type for the data seen
     *  at the front of the queue. The item should not be removed from the front of the queue.
     * @return
     */
    public E peek()
    {
        if(size == 0)
            return null;

        return head.data;
    }

    /**
     * Checks if the queue is pointing to anything
     * @return
     */
    public boolean isEmpty()
    {
        return head == null;
    }

    /**
     * returns the number of elements in the queue
     * @return
     */
    public int size()
    {
        return size;
    }

    /**
     * returns the name of the queue
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns its name and all of its elements
     * @return
     */
    public String toString()
    {
        String s = name + ": \n[";
        Iterator it = iterator();
        if(!it.hasNext())
            s += " ]\n";
        while(it.hasNext())
        {
            s += it.next();
            if(it.hasNext())
                s += ";\n";
            else
                s += "]\n";
        }
        return s;
    }

    /**
     * Definition of inner Node class
     */
    private class Node
    {
        Node next;
        E data;

        /**
         * Generic Node constructor
         * @param x
         * @param nxt
         */
        Node(E x, Node nxt)
        {
            data = x;
            next = nxt;
        }
    }

    /**
     * Iterator method
     * @return
     */
    public java.util.Iterator<E> iterator() { return new QueueIterator(); }

    /**
     * Internal Iterator class
     */
    private class QueueIterator implements java.util.Iterator<E>
    {
        protected Node currentNode;
        protected int currentIndex;


        /**
         * Default constructor
         */
        public QueueIterator()
        {
            currentIndex = 0;
            currentNode = head;
        }

        /**
         * Checks if the queue has any more items at the current position
         * @return
         */
        public boolean hasNext(){return currentIndex < size;}

        /**
         * Iterates once
         * @return
         */
        public E next()
        {
            if( !hasNext() )
                throw new java.util.NoSuchElementException();

            Node lastNode = currentNode;
            currentNode = currentNode.next;
            currentIndex++;
            return lastNode.data;
        }
    }
}
