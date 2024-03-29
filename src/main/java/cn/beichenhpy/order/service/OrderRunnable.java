package cn.beichenhpy.order.service;

import cn.beichenhpy.order.entity.Order;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class OrderRunnable implements Runnable{
    private final BlockingQueue<Order> orderQueue;

    public OrderRunnable(BlockingQueue<Order> orderQueue) {
        this.orderQueue = orderQueue;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public synchronized void run() {
        Order order = null;
        try {
            order = orderQueue.poll(1, TimeUnit.MINUTES);
            //模拟任务
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.warn("线程：{},强制中断",Thread.currentThread().getName());
        }
        if (order != null){
            log.warn("计算order:{},时间:{}",order,new Date());
        }
    }
}
