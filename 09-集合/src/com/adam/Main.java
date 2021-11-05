package com.adam;

import com.adam.Times.Task;
import com.adam.file.FileInfo;
import com.adam.file.Files;
import com.adam.set.ListSet;
import com.adam.set.Set;
import com.adam.set.Set.Visitor;
import com.adam.set.TreeSet;

public class Main {
	static void test1() {
//		Set<Integer> listSet = new ListSet<Integer>();
//		listSet.add(10);
//		listSet.add(11);
//		listSet.add(12);
//		listSet.add(13);
//		listSet.add(14);
//		listSet.add(10);
		Set<Integer> treeSet = new TreeSet<Integer>();
		treeSet.add(14);
		treeSet.add(10);
		treeSet.add(11);
		treeSet.add(12);
		treeSet.add(13);

		treeSet.add(10);
		treeSet.traversal(new Visitor<Integer>() {

			@Override
			public boolean visit(Integer element) {
				// TODO Auto-generated method stub
				System.out.println(element);
				return false;
			}
		});
	}

	static void testSet(Set<String> set, String[] words) {
		for (int i = 0; i < words.length; i++) {
			set.add(words[i]);
		}
		for (int i = 0; i < words.length; i++) {
			set.contains(words[i]);
		}
		for (int i = 0; i < words.length; i++) {
			set.remove(words[i]);
		}
	}

	static void test2() {
		FileInfo fielInfo = Files.read("/Users/adam/Documents/Work/git_Deveolper", new String[] { "java", "m" });
		System.out.println("文件数量" + fielInfo.getFiles());
		System.out.println("代码数量" + fielInfo.getLines());

		String[] words = fielInfo.words();
		System.out.println("单词数量" + words.length);

		Times.test("ListSet", new Task() {
			@Override
			public void execute() {
				testSet(new ListSet<>(), words);
			}
		});
		Times.test("TreeSet", new Task() {
			@Override
			public void execute() {
				testSet(new TreeSet<>(), words);
			}
		});

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test2();
	}

}
