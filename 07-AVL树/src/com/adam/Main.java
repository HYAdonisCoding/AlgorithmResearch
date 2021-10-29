package com.adam;

import com.adam.printer.BinaryTrees;
import com.adam.tree.AVLTree;

public class Main {

	static void test1() {
		Integer data[] = new Integer[] { 25, 94, 59, 9, 23, 16, 37, 80, 44, 93, 92, 22, 4, 54, 60, 42, 79, 89, 30, 18,
				47, 64 };
		AVLTree<Integer> tree = new AVLTree<>();
		for (int i = 0; i < data.length; i++) {
			tree.add(data[i]);
		}

		BinaryTrees.println(tree);
//		tree.remove(9);
//		BinaryTrees.println(tree);
		System.out.println(tree.isComplete());
	}

	public static void main(String[] args) {
		test1();

	}

}
