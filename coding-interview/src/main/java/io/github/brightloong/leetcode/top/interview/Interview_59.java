package io.github.brightloong.leetcode.top.interview;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author BrightLoong
 * @date 2020/3/7 21:02
 * @description
 */
public class Interview_59 {
}

class MaxQueue {
    Deque<Integer> queue = new ArrayDeque<>();
    Deque<Integer> max = new ArrayDeque<>();

    public MaxQueue() {

    }

    public int max_value() {
        return queue.isEmpty() ? -1 : max.peek();
    }

    public void push_back(int value) {
        //queue如人队列
        queue.offer(value);
        while (!max.isEmpty() && max.getLast() < value) {
            max.pollLast();
        }
        max.offer(value);
    }

    public int pop_front() {
        if(queue.isEmpty()) {
            return -1;
        }
        int val = queue.pop();
        if(max.peek() == val) {
            max.pop();
        }
        return val;
    }

    public static void main(String[] args) {
        MaxQueue queue = new MaxQueue();
        queue.push_back(4);
        queue.push_back(2);
        queue.push_back(1);
        queue.push_back(3);
        queue.push_back(1);
        System.out.println(queue.max_value());
        System.out.println(queue.pop_front());
        System.out.println(queue.max_value());
        System.out.println(queue.pop_front());
        System.out.println(queue.max_value());
        System.out.println(queue.pop_front());
        System.out.println(queue.max_value());
        System.out.println(queue.pop_front());
        System.out.println(queue.max_value());
    }
}
