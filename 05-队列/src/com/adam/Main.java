package com.adam;

import com.adam.circle.CircleDeque;
import com.adam.circle.CircleQueue;

public class Main {
	public static void test1() {
		Queue<Integer> queue = new Queue<>();
		queue.enQueue(11);
		queue.enQueue(22);
		queue.enQueue(33);
		queue.enQueue(44);

		while (!queue.isEmpty()) {
			System.out.println(queue.deQueue());
		}
	}

	public static void test2() {
		Deque<Integer> queue = new Deque<>();
		queue.enQueueFront(11);
		queue.enQueueFront(22);
		queue.enQueueRear(33);
		queue.enQueueRear(44);
		/* rear 44 33 11 22 front */
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueueRear());
		}
	}

	public static void test3() {
		CircleQueue<Integer> circleQueue = new CircleQueue<>();
		// 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
		for (int i = 0; i < 10; i++) {
			circleQueue.enQueue(i);
		}

		/// null, null, null, null, null, 5, 6, 7, 8, 9
		for (int i = 0; i < 5; i++) {
			circleQueue.deQueue();
		}
		// 15, 16, 17, 18, 19, 5, 6, 7, 8, 9
		for (int i = 15; i < 23; i++) {
			circleQueue.enQueue(i);
		}

		System.out.println(circleQueue);
		while (!circleQueue.isEmpty()) {
			System.out.println(circleQueue.deQueue());
		}
	}

	public static void test4() {
		CircleDeque<Integer> queue = new CircleDeque<>();
		for (int i = 0; i < 10; i++) {
			queue.enQueueFront(i + 1);
			queue.enQueueRear(i + 100);
		}

		/* front 5 4 3 2 1 100 101 102 103 104 rear */
		/* front 5 4 3 2 1 100 101 102 103 104 105 106 8 7 6 rear */
		/*
		 * front 8 7 6 5 4 3 2 1 100 101 102 103 104 105 106 107 108 109 null null 10 9
		 * rear
		 */

		for (int i = 0; i < 3; i++) {
			queue.deQueueFront();
			queue.deQueueRear();
		}
		/*
		 * front null 7 6 5 4 3 2 1 100 101 102 103 104 105 106 null null null null null
		 * null null rear
		 */
		queue.enQueueFront(11);
		queue.enQueueFront(12);
		queue.enQueueRear(22);
		/*
		 * front 11 7 6 5 4 3 2 1 100 101 102 103 104 105 106 22 null null null null
		 * null null rear
		 */
		System.out.println(queue);

		while (!queue.isEmpty()) {
			System.out.println(queue.deQueueFront());
		}
	}

	// 优化 Optimization stack
	static void test5() {
		int n = 2;
		int m = 10;
//		if (n >= m) {
//			System.out.println(n - m);
//		} else {
//			System.out.println(n);
//		}
		// m > 0, n >=0 , n < 2m
		System.out.println(n - (n > m ? m : 0));

		System.out.println(n % m);
	}

	public static void main(String[] args) {
		test3();
		test4();
	}

}
