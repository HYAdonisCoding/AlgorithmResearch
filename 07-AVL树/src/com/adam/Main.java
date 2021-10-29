package com.adam;

import java.util.Comparator;

import com.adam.printer.BinaryTrees;
import com.adam.tree.AVLTree;

public class Main {

	static void test1() {
		Integer data[] = new Integer[] { 7, 4, 9, 2, 5, 8, 11, 3, 12, 1 };
		AVLTree<Integer> tree = new AVLTree<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}

		});
		for (int i = 0; i < data.length; i++) {
			tree.add(data[i]);
		}

		BinaryTrees.println(tree);
		tree.remove(9);
		BinaryTrees.println(tree);
		System.out.println(tree.isComplete());
	}

	public static void main(String[] args) {
		test1();

	}

}
