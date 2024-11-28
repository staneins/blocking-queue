package com.kaminsky;

public class BlockingQueue<T> {

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private int size;
    private int maxSize;

    public BlockingQueue(int maxSize) {
        head = null;
        size = 0;
        this.maxSize = maxSize;
    }

    public synchronized void enqueue(T data) {
        if (size < maxSize) {
            Node<T> newNode = new Node<>(data);
            if (head == null) {
                head = newNode;
            } else {
                Node<T> current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
            size++;
            notifyAll();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized T dequeue() {
        while (size == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        T data = head.data;
        head = head.next;
        size--;
        notifyAll();
        return data;
    }

    public synchronized int size() {
        return size;
    }

}
