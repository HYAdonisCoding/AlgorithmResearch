package com.adam;

import java.util.ArrayList;
import java.util.List;

import com.adam.Times.Task;
import com.adam.printer.BinaryTrees;
import com.adam.tree.AVLTree;
import com.adam.tree.BST;
import com.adam.tree.RBTree;

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

	static void test2() {
		Integer data[] = new Integer[] { 25, 94, 59, 9, 23, 16, 37, 80 };
		AVLTree<Integer> tree = new AVLTree<>();
		for (int i = 0; i < data.length; i++) {
			tree.add(data[i]);
		}

		BinaryTrees.println(tree);
		tree.remove(9);
		tree.remove(94);
		tree.remove(16);
		tree.remove(23);
		BinaryTrees.println(tree);
		System.out.println(tree.isComplete());
	}

	static void test3() {
		List<Integer> data = new ArrayList<>();
		for (int i = 0; i < 100_000; i++) {
			data.add((int) Math.random() * 100_000);
		}
		Times.test("BST", new Task() {
			@Override
			public void execute() {
				BST<Integer> bst = new BST<>();
				for (int i = 0; i < data.size(); i++) {
					bst.add(data.get(i));
				}
				System.out.println(bst.height());

				for (int i = 0; i < data.size(); i++) {
					bst.contains(data.get(i));
				}
				for (int i = 0; i < data.size(); i++) {
					bst.remove(data.get(i));

				}
			}
		});
		Times.test("AVLTree", new Task() {

			@Override
			public void execute() {
				AVLTree<Integer> tree = new AVLTree<>();
				for (int i = 0; i < data.size(); i++) {
					tree.add(data.get(i));
				}
				System.out.println(tree.height());
				BinaryTrees.println(tree);

				for (int i = 0; i < data.size(); i++) {
					tree.contains(data.get(i));
				}
				for (int i = 0; i < data.size(); i++) {
					tree.remove(data.get(i));
				}
			}
		});

	}

	static void test4() {
		Integer data[] = new Integer[] { 41, 74, 46, 3, 24, 25, 61, 97, 99, 95 };
		RBTree<Integer> tree = new RBTree<>();
		for (int i = 0; i < data.length; i++) {
			tree.add(data[i]);
			System.out.println("[" + data[i] + "]");
			BinaryTrees.println(tree);
			System.out.println("------------------------------------");
		}

		BinaryTrees.println(tree);
//		tree.remove(9);
//		tree.remove(94);
//		tree.remove(16);
//		tree.remove(23);
//		BinaryTrees.println(tree);
//		System.out.println(tree.isComplete());
	}

	static void test5() {
		Integer data[] = new Integer[] { 41, 74, 46, 3, 24, 25, 61, 97, 99, 95 };
		RBTree<Integer> tree = new RBTree<>();
		for (int i = 0; i < data.length; i++) {
			tree.add(data[i]);
		}

		BinaryTrees.println(tree);
		for (int i = 0; i < data.length; i++) {
			tree.remove(data[i]);
			System.out.println("remove -- [" + data[i] + "]");
			BinaryTrees.println(tree);
			System.out.println("------------------------------------");
		}
	}

	public static void main(String[] args) {
		test5();

	}

}
