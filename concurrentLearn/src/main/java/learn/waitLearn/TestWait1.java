package learn.waitLearn;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author: zhun.huang
 * @create: 2018-03-25 下午3:10
 * @email: nolan.zhun@gmail.com
 * @description: 通过生产者-消费者模型
 */
public class TestWait1 {

    public static void main(String[] args) {
        Queue<Integer> workingQueue = new LinkedList<>();
        Producer producer = new Producer(workingQueue);
        Consumer consumer = new Consumer(workingQueue);
        new Thread(producer, "producer").start();
        new Thread(consumer, "consumer").start();

    }

    public static class Producer implements Runnable {

        private Queue<Integer> workingQueue;

        private Integer maxSize = 100;

        public Producer(Queue<Integer> workingQueue) {
            this.workingQueue = workingQueue;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (workingQueue) {
                    try {
                        while (workingQueue.size() == maxSize) {
                            System.out.println("Queue is full, " + "Producer thread waiting for " + "consumer to take something from queue");
                            workingQueue.wait();
                        }
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    Random random = new Random();
                    int nextInt = random.nextInt();
                    workingQueue.add(nextInt);
                    System.out.println("生产商品:" + nextInt);
                    workingQueue.notifyAll();
                }
            }
        }
    }

    public static class Consumer implements Runnable {

        private Queue<Integer> queue;

        public Consumer(Queue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            System.out.println("Queue is empty," + "Consumer thread is waiting" + " for producer thread to put something in queue");
                            queue.wait();
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                    }
                    Integer peek = queue.remove();
                    System.out.println("消费商品:" + peek);
                    queue.notifyAll();
                }
            }
        }
    }
}
