package com.training.datastructure.list;

import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class PriorityQueueStack {

  public static void main(String[] args) {
    PriorityQueue<String> queue = new PriorityQueue<>();
    queue.offer("88");
    queue.offer("41");
    String peek = queue.peek();
    String poll = queue.poll();
    System.out.println(peek);
    System.out.println(poll);
    AtomicStampedReference<String> atomicReference = new AtomicStampedReference<>(null, 1);
    atomicReference.compareAndSet(atomicReference.getReference(), "new String", atomicReference.getStamp(), atomicReference.getStamp() + 1);
    BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();

  }

}
