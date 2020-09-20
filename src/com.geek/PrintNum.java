package com.geek;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintNum {
    ReentrantLock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    private volatile int count = 1;
    Thread thread1 = new Thread(() -> {
        for (int i = 1; i <= 1000; i++) {
            lock.lock();
            try {
                while (count % 3 != 1) {
                    condition1.await();
                }
                System.out.println("Thread1:" + i);
                count++;
                condition2.signalAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }

    });

    Thread thread2 = new Thread(() -> {
        for (int i = 1; i <= 1000; i++) {
            lock.lock();
            try {
                while (count % 3 != 2) {
                    condition2.await();
                }
                System.out.println("Thread2:" + i);
                count++;
                condition3.signalAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }

    });

    Thread thread3 = new Thread(() -> {
        for (int i = 1; i <= 1000; i++) {
            lock.lock();
            try {
                while (count % 3 != 0) {
                    condition3.await();
                }
                System.out.println("Thread3:" + i);
                count++;
                condition1.signalAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }

    });

    private void start() {
        thread3.start();
        thread1.start();
        thread2.start();

    }

    private volatile int semaphoreCount = 1;
    private volatile boolean stopFlag = false;
    Semaphore semaphore1 = new Semaphore(1);
    Semaphore semaphore2 = new Semaphore(0);
    Semaphore semaphore3 = new Semaphore(0);
    Thread thread4 = new Thread(() -> {
        while (!stopFlag) {
            try {
                boolean acquire = semaphore1.tryAcquire(1, TimeUnit.SECONDS);
                if (!acquire){
                    continue;
                }
                System.out.println("Semaphore Thread4: " + semaphoreCount);
                if (semaphoreCount == 1000) {
                    stopFlag = true;
                    break;
                }
                semaphoreCount++;
                semaphore2.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    });
    Thread thread5 = new Thread(() -> {
        while (!stopFlag) {
            try {
                boolean acquire = semaphore2.tryAcquire(1, TimeUnit.SECONDS);
                if (!acquire){
                    continue;
                }
                System.out.println("Semaphore Thread5: " + semaphoreCount);
                if (semaphoreCount == 1000) {
                    stopFlag = true;
                    break;
                }
                semaphoreCount++;
                semaphore3.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    });
    Thread thread6 = new Thread(() -> {
        while (!stopFlag) {
            try {
                boolean acquire = semaphore3.tryAcquire(1, TimeUnit.SECONDS);
                if (!acquire){
                    continue;
                }
                System.out.println("Semaphore Thread6: " + semaphoreCount);
                if (semaphoreCount == 1000) {
                    stopFlag = true;
                    break;
                }
                semaphoreCount++;
                semaphore1.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    });


    private void startSemaphore() {
        thread6.start();
        thread5.start();
        thread4.start();

    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始");
        PrintNum printNum = new PrintNum();
        printNum.startSemaphore();
        printNum.printStatus();
        Thread.sleep(2000);
        printNum.printStatus();
    }

    private  void printStatus() {
        System.out.println("thread4 status: " + thread4.getState());
        System.out.println("thread5 status: " +thread5.getState());
        System.out.println("thread6 status: " + thread6.getState());
    }
}
