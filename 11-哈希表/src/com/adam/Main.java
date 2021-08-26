package com.adam;

import java.util.HashMap;
import java.util.Map;

public class Main {

	private static void test1() {
		String string = "jack";// 3254239
		int len = string.length();
		int hashCode = 0;
		for (int i = 0; i < len; i++) {
			char c = string.charAt(i);
//			hashCode = hashCode * 31 + c;
			hashCode = (hashCode << 5) - hashCode + c;
		}
		System.out.println(hashCode);
		System.out.println(string.hashCode());
	}

	private static void test2() {
		Integer a = 110;
		Float b = 10.6f;
		Long c = (long) 1561;
		Double d = 10.9;
		String eString = "rose";

		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		System.out.println(c.hashCode());
		System.out.println(d.hashCode());
		System.out.println(eString.hashCode());
	}

	public static void main(String[] args) {
//		test2();
		Person p1 = new Person(30, 1.8f, "Adam");
		Person p2 = new Person(30, 1.8f, "Adam");

		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());

		Map<Object, Object> map = new HashMap<>();
		map.put(p1, "abc");
		map.put("test", "ccc");
		map.put(p2, "bcd");
		System.out.println(map.size());

	}

}
