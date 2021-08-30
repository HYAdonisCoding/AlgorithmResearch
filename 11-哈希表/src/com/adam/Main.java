package com.adam;

import com.adam.Times.Task;
import com.adam.file.FileInfo;
import com.adam.file.Files;
import com.adam.map.HashMap;
import com.adam.map.LinkedHashMap;
import com.adam.map.Map;
import com.adam.map.Map.Visitor;
import com.adam.map.TreeMap;
import com.adam.model.Key;
import com.adam.model.SubKey1;
import com.adam.model.SubKey2;

public class Main {
	private static void testMap(Map<String, Integer> map, String[] words) {
		Times.test(map.getClass().getName(), new Task() {
			@Override
			public void execute() {
				for (String word : words) {
					Integer count = map.get(word);
					count = count == null ? 0 : count;
					map.put(word, count + 1);
				}
				System.out.println(map.size()); // 17188

				int count = 0;
				for (String word : words) {
					Integer i = map.get(word);
					count += i == null ? 0 : i;
					map.remove(word);
				}
				Asserts.test(count == words.length);
				Asserts.test(map.size() == 0);
			}
		});
	}

//	private static void test1() {
//		String string = "jack";// 3254239
//		int len = string.length();
//		int hashCode = 0;
//		for (int i = 0; i < len; i++) {
//			char c = string.charAt(i);
////			hashCode = hashCode * 31 + c;
//			hashCode = (hashCode << 5) - hashCode + c;
//		}
//		System.out.println(hashCode);
//		System.out.println(string.hashCode());
//	}
//
//	private static void test2() {
//		Integer a = 110;
//		Float b = 10.6f;
//		Long c = (long) 1561;
//		Double d = 10.9;
//		String eString = "rose";
//
//		System.out.println(a.hashCode());
//		System.out.println(b.hashCode());
//		System.out.println(c.hashCode());
//		System.out.println(d.hashCode());
//		System.out.println(eString.hashCode());
//	}
//
//	private static void test3() {
//		Person p1 = new Person(30, 1.8f, "Adam");
//		Person p2 = new Person(30, 1.8f, "Adam");
//
////		System.out.println(p1.hashCode());
////		System.out.println(p2.hashCode());
//
//		Map<Object, Integer> map = new HashMap<>();
//		map.put(p1, 1);
//		map.put(p2, 2);
//		map.put("test", 3);
//		map.put("jack", 4);
//		map.put("rose", 5);
//		map.put("test", 6);
//		map.put("null", 7);
//
//		map.traversal(new Visitor<Object, Integer>() {
//
//			@Override
//			public boolean visit(Object key, Integer value) {
//				System.out.println(key + ": " + value);
//				return false;
//			}
//
//		});
////		System.out.println(map.size());
////		System.out.println(map.remove("rose"));
////		System.out.println(map.remove("rose"));
////		System.out.println(map.size());
//
//		// System.out.println(map.get(p1));
////		System.out.println(map.get("jack"));
////		System.out.println(map.get("test"));
////		System.out.println(map.get("rose"));
////		System.out.println(map.get("null"));
////		System.out.println(map.get(null));
//	}
//
//	private static void test4() {
//		HashMap<Object, Integer> map = new HashMap<>();
//		for (int i = 1; i < 20; i++) {
//			map.put(new Key(i), i * 10);
//		}
//		System.out.println(map.size());
//
//		map.print();
//		map.traversal(new Visitor<Object, Integer>() {
//
//			@Override
//			public boolean visit(Object key, Integer value) {
//				System.out.println(key + ": " + value);
//				return false;
//			}
//
//		});
//
//		System.out.println(map.get(new Key(3)));
//	}
//
//	private static void test5() {
//		HashMap<Object, Integer> map = new HashMap<>();
//		for (int i = 1; i < 20; i++) {
//			map.put(new Key(i), i);
//		}
//		map.put(new Key(4), 100);
//		System.out.println(map.size());
//		Asserts.test(map.size() == 19);
//		Asserts.test(map.get(new Key(4)) == 100);
//		Asserts.test(map.get(new Key(18)) == 18);
////		map.print();
//		System.out.println(map.get(new Key(1)));
//	}
//
//	private static void test6() {
//		Person p1Person = new Person(10, 1.7f, "Jack");
//		Person p2Person = new Person(10, 1.8f, "Rose");
//		System.out.println(p1Person.equals(p2Person));// false
//		System.out.println(p1Person.compareTo(p2Person));// 0
//	}
//
	private static void test7() {

		SubKey1 key1 = new SubKey1(1);
		SubKey2 key2 = new SubKey2(2);

		HashMap<Object, Integer> map = new HashMap<>();
		map.put(key1, 1);
		map.put(key2, 2);
		System.out.println(map.size());
	}

	static void test2(HashMap<Object, Integer> map) {
		for (int i = 1; i <= 20; i++) {
			map.put(new Key(i), i);
		}
		for (int i = 5; i <= 7; i++) {
			map.put(new Key(i), i + 5);
		}
		Asserts.test(map.size() == 20);
		Asserts.test(map.get(new Key(4)) == 4);
		Asserts.test(map.get(new Key(5)) == 10);
		Asserts.test(map.get(new Key(6)) == 11);
		Asserts.test(map.get(new Key(7)) == 12);
		Asserts.test(map.get(new Key(8)) == 8);
	}

	static void test3(HashMap<Object, Integer> map) {
		map.put(null, 1); // 1
		map.put(new Object(), 2); // 2
		map.put("jack", 3); // 3
		map.put(10, 4); // 4
		map.put(new Object(), 5); // 5
		map.put("jack", 6);
		map.put(10, 7);
		map.put(null, 8);
		map.put(10, null);
		Asserts.test(map.size() == 5);
		Asserts.test(map.get(null) == 8);
		Asserts.test(map.get("jack") == 6);
		Asserts.test(map.get(10) == null);
		Asserts.test(map.get(new Object()) == null);
		Asserts.test(map.containsKey(10));
		Asserts.test(map.containsKey(null));
		Asserts.test(map.containsValue(null));
		Asserts.test(map.containsValue(1) == false);
	}

	static void test4(HashMap<Object, Integer> map) {
		map.put("jack", 1);
		map.put("rose", 2);
		map.put("jim", 3);
		map.put("jake", 4);
		for (int i = 1; i <= 10; i++) {
			map.put("test" + i, i);
			map.put(new Key(i), i);
		}
		for (int i = 5; i <= 7; i++) {
			Asserts.test(map.remove(new Key(i)) == i);
		}
		for (int i = 1; i <= 3; i++) {
			map.put(new Key(i), i + 5);
		}
		Asserts.test(map.size() == 21);
		Asserts.test(map.get(new Key(1)) == 6);
		Asserts.test(map.get(new Key(2)) == 7);
		Asserts.test(map.get(new Key(3)) == 8);
		Asserts.test(map.get(new Key(4)) == 4);
		Asserts.test(map.get(new Key(5)) == null);
		Asserts.test(map.get(new Key(6)) == null);
		Asserts.test(map.get(new Key(7)) == null);
		Asserts.test(map.get(new Key(8)) == 8);
		map.traversal(new Visitor<Object, Integer>() {

			@Override
			public boolean visit(Object key, Integer value) {
				System.out.println(key + " : " + value);
				return false;
			}

		});
	}

	static void test5(HashMap<Object, Integer> map) {
		for (int i = 1; i <= 20; i++) {
			map.put(new SubKey1(i), i);
		}
		map.put(new SubKey2(1), 5);
		Asserts.test(map.get(new SubKey1(1)) == 5);
		Asserts.test(map.get(new SubKey2(1)) == 5);
		Asserts.test(map.size() == 20);
	}

	static void test1() {
		String filepath = "/Users/adam/Documents/Work/git_出包专用";
		FileInfo fileInfo = Files.read(filepath, new String[] { "java", "m" });
		String[] words = fileInfo.words();

		System.out.println("总行数：" + fileInfo.getLines());
		System.out.println("单词总数：" + words.length);
		System.out.println("-------------------------------------");

//		java.util.HashMap<String, Integer> map = new java.util.HashMap<>();
//		
//		for (String word : words) {
//			Integer count = map.get(word);
//			count = count == null ? 0 : count;
//			map.put(word, count + 1);
//		}
//		System.out.println(map.size()); // 17188

		testMap(new TreeMap(), words);
		testMap(new HashMap(), words);
		testMap(new LinkedHashMap<>(), words);
	}

	public static void main(String[] args) {
//		test7();
//		test2(new HashMap<>());
//		test3(new HashMap<>());
//		test4(new HashMap<>());
//		test5(new HashMap<>());
		test1();
		test4(new LinkedHashMap<>());
		test2(new LinkedHashMap<>());
		test3(new LinkedHashMap<>());
		test4(new LinkedHashMap<>());
		test5(new LinkedHashMap<>());
	}

}
