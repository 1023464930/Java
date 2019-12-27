package Experiment2;

import java.util.NoSuchElementException;

public class Queue<T>
{
    private static final int DEFAULT_CAPACITY=10;
    private int front;
    private int rear;
    private T[] elements;
    public Queue()
    {
        elements=(T[])new Object[DEFAULT_CAPACITY];
        rear=front=0;
    }
    public void clear()
    {
        front=rear=0;
    }
    public Boolean isEmpty()
    {
        return rear==front;
    }
    public Boolean isFull()
    {
        return (rear+1)%elements.length==front;
    }
    public int size()
    {
        return (rear-front+elements.length)%elements.length;
    }
    public void enQueue(T item)
    {
        if(isFull())
        {
            throw new IndexOutOfBoundsException();
        }
        rear=(rear+1)%elements.length;
        elements[rear]=item;
    }
    public T deQueue()
    {
        if(isEmpty())
            return null;
        front=(front+1)%elements.length;
        return elements[front];
    }
    public T getFront()
    {
    if(isEmpty())
        return null;
    return elements[(front+1)%elements.length];
    }
}
