package com.adam;

import com.adam.Times.Task;

public class Main {
	/*
	 * 0 1 1 2 3 5 8 13....
	 */
	public static int fib(int n) {
		if (n <= 1) {
			return n;
		}
		return fib(n - 1) + fib(n - 2);
	}

	public static int fib2(int n) {
		if (n <= 1) {
			return n;
		}
		int first = 0;
		int second = 1;
		for (int i = 0; i < n - 1; i++) {
			int sum = first + second;

			first = second;
			second = sum;
		}
		return second;
	}

	public static int fib3(int n) {
		if (n <= 1) {
			return n;
		}
		int first = 0;
		int second = 1;
		for (int i = 0; i < n - 1; i++) {
			second = first + second;

			first = second - first;

		}
		return second;
	}

	public static int fib4(int n) {
		double c = Math.sqrt(5);

		return (int) ((Math.pow((1 + c) / 2, n) - Math.pow((1 - c) / 2, n)) / c);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(123);
		System.out.println(fib2(64));
		Times.test("fib1", new Task() {
			@Override
			public void execute() {
				// TODO Auto-generated method stub
				System.out.println(fib2(20));
			}
		});
		Times.test("fib2", new Task() {
			@Override
			public void execute() {
				// TODO Auto-generated method stub
				System.out.println(fib2(20));
			}
		});
	}

}
