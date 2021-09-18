package com.adam;

public class Main {

	public static void test2() {
		ArrayList<Person> list = new ArrayList<>();
		list.add(new Person(20, "Jack"));
		list.add(null);
		list.add(new Person(22, "Rose"));
		list.add(new Person(21, "Adam"));
		list.add(null);

		System.out.println(list.indexOf(null));

//		list.clear();

		// 提醒JVM 垃圾回收
//		System.gc();
	}

	public static void test1() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(99);
		list.add(88);
		list.add(77);
		list.add(66);
		list.add(55);
		list.add(0, 100);
		list.add(list.size(), 10);

		for (int i = 0; i < 30; i++) {
			list.add(i);
		}
		System.out.println(list);
	}

	public static void main(String[] args) {

		test2();
	}

}
