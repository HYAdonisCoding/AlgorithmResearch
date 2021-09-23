package com.adam;

import com.adam.circle.CircleLinkedList;
import com.adam.list.ArrayList2;
import com.adam.list.List;
import com.adam.signal.SignalLinkedList;
import com.adam.signal.SignalLinkedList2;

public class Main {

	static void test1() {
		List<Integer> list = new SignalLinkedList<>();

		list.add(20);
		list.add(0, 10);
		list.add(30);
		list.add(list.size(), 40);
		System.out.println(list);
		list.remove(1);

		System.out.println(list);
	}

	static void test2() {
		List<Integer> list = new SignalLinkedList2<>();
//		list.remove(0);

		list.add(20);
		list.add(0, 10);
		list.add(30);
		list.add(list.size(), 40);
		System.out.println(list);

		System.out.println(list.remove(1));
		System.out.println(list);
	}

	static void test3() {
		List<Integer> list = new ArrayList2<>();
		for (int i = 0; i < 50; i++) {
			list.add(i);
		}
		for (int i = 0; i < 50; i++) {
			list.remove(0);
		}
		System.out.println(list);
	}

	private static void testList(List<Integer> list) {
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(44);

		list.add(0, 55);// [55, 11, 22, 33, 44]
		list.add(2, 66);// [55, 11, 66, 22, 33, 44]
		list.add(list.size(), 77);// [55, 11, 66, 22, 33, 44, 77]

		list.remove(0);// [11, 66, 22, 33, 44, 77]
		list.remove(2);// [11, 66, 33, 44, 77]
		list.remove(list.size() - 1);// [11, 66, 33, 44]

		Asserts.test(list.indexOf(44) == 3);
		Asserts.test(list.indexOf(22) == List.ELEMENT_NOT_FOUND);
		Asserts.test(list.contains(33));
		Asserts.test(list.get(0) == 11);
		Asserts.test(list.get(1) == 66);
		Asserts.test(list.get(list.size() - 1) == 44);

		System.out.println(list);
	}

	// 约瑟夫问题
	private static void josephus() {
		CircleLinkedList<Integer> list = new CircleLinkedList<>();
		for (int i = 1; i < 9; i++) {
			list.add(i);
		}
		/// 指向头结点 指向1
		list.reset();

		//
		while (!list.isEmpty()) {
			list.next();
			list.next();

			System.out.println(list.remove());
		}
	}

	public static void main(String[] args) {

		System.out.println("LinkedList");

//		testList(new ArrayList<>());
//		testList(new LinkedList<>());

//		testList(new SignalCircleLinkedList<>());
//		testList(new CircleLinkedList<>());

		josephus();
	}

}
