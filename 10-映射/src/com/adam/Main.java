package com.adam;

import com.adam.file.FileInfo;
import com.adam.file.Files;
import com.adam.map.Map;
import com.adam.map.Map.Visitor;
import com.adam.map.TreeMap;
import com.adam.set.Set;
import com.adam.set.TreeSet;

public class Main {
	static void test1() {
		Map<String, Integer> map = new TreeMap<>();
		map.put("c", 2);
		map.put("a", 5);
		map.put("b", 62);
		map.put("a", 2);

		map.traversal(new Visitor<String, Integer>() {

			@Override
			public boolean visit(String key, Integer value) {
				System.out.println(key + " : " + value);
				return false;
			}
		});
	}

	static void test2() {
		FileInfo fielInfo = Files.read("/Users/adam/eclipse-workspace/09-集合", new String[] { "java", "m" });
		System.out.println("文件数量" + fielInfo.getFiles());
		System.out.println("代码数量" + fielInfo.getLines());

		String[] words = fielInfo.words();
		System.out.println("单词数量" + words.length);
		Map<String, Integer> map = new TreeMap<>();
		for (int i = 0; i < words.length; i++) {
			Integer count = map.get(words[i]);
			count = (count == null) ? 1 : (count + 1);
			map.put(words[i], count);
		}
		System.out.println(map.size());
		map.traversal(new Visitor<String, Integer>() {

			@Override
			public boolean visit(String key, Integer value) {
				System.out.println(key + " : " + value);
				return false;
			}
		});
	}

	static void test3() {
		Set<String> set = new TreeSet();
		set.add("c");
		set.add("f");
		set.add("a");
		set.add("b");
		set.add("c");
		set.add("a");

		set.traversal(new Set.Visitor<String>() {

			@Override
			public boolean visit(String element) {
				System.out.println(element);
				return false;
			}

		});
	}

	public static void main(String[] args) {
		test3();

	}

}
