package com.kaminsky;

public class App
{
    public static void main( String[] args )
    {
        BlockingQueue queue = new BlockingQueue(2);

        queue.enqueue("Skdskd");
        queue.enqueue("awdawd");
        queue.enqueue("wdawd");

        System.out.println(queue.dequeue());
    }
}
