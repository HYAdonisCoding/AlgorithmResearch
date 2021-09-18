package com.adam;

import com.adam.list.LinkedList;
import com.adam.list.List;

public class Main {

	static void test1() {
		List<Integer> list = new LinkedList<>();
		list.add(20);
		list.add(0, 10);
		list.add(30);
		list.add(list.size(), 40);
		System.out.println(list);
		list.remove(1);

		System.out.println(list);
	}

	public static void main(String[] args) {

		System.out.println("LinkedList");
		test1();
	}

}
