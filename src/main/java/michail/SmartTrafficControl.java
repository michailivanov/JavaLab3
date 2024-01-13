package michail;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SmartTrafficControl {
    private Queue<Car> queue;
    private Lock lock;
    private Condition carArrived;

    public SmartTrafficControl(){
        this.queue = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.carArrived = lock.newCondition();
    }

    public void addCar(Car car){
        lock.lock();
        try {
            this.queue.offer(car);
            carArrived.signal();
        }finally {
            lock.unlock();
        }

    }

    public void regulateCars() {
        while (true) {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    carArrived.await();
                }

                Car firstCar = queue.poll();
                System.out.print("\t>" + firstCar);

                Iterator<Car> iterator = queue.iterator();
                while (iterator.hasNext()) {
                    Car car = iterator.next();
                    if (!car.isIntersect(firstCar)) {
                        System.out.print(", " + car);
                        iterator.remove();
                    }

                }

                System.out.print(" passed the crossroad.\n");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}