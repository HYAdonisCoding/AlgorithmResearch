package com.adam;

import java.util.Comparator;

import com.adam.printer.BinaryTrees;
import com.adam.tree.BST;

public class Main {
	private static class PersonComparator implements Comparator<Person> {
		@Override
		public int compare(Person e1, Person e2) {
			return e1.getAge() - e2.getAge();
		}
	}

	private static class PersonComparator2 implements Comparator<Person> {
		@Override
		public int compare(Person e1, Person e2) {
			return e2.getAge() - e1.getAge();
		}
	}

	static void test1() {
		Integer data[] = new Integer[] { 7, 4, 9, 2, 5, 8, 11, 3, 12, 1 };
		BST<Integer> binarySearchTree = new BST<>();
		for (int i = 0; i < data.length; i++) {
			binarySearchTree.add(data[i]);
		}

		BinaryTrees.println(binarySearchTree);

		BST<Person> binarySearchTree2 = new BST<>(new PersonComparator());
		for (int i = 0; i < data.length; i++) {
			binarySearchTree2.add(new Person(data[i], data[i].toString()));
		}

		BST<Person> binarySearchTree3 = new BST<>(new PersonComparator2());
		for (int i = 0; i < data.length; i++) {
			binarySearchTree3.add(new Person(data[i], data[i].toString()));
		}

		BST<Person> binarySearchTree4 = new BST<>(new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return o1.getAge() - o2.getAge();
			}
		});
		for (int i = 0; i < data.length; i++) {
			binarySearchTree4.add(new Person(data[i], data[i].toString()));
		}

	}

	static void test2() {
		Integer data[] = new Integer[] { 7, 4, 9, 2, 5, 8, 11, 3, 12, 1 };

		BST<Person> binarySearchTree1 = new BST<>();
		for (int i = 0; i < data.length; i++) {
			binarySearchTree1.add(new Person(data[i], data[i].toString()));
		}
		BinaryTrees.println(binarySearchTree1);

		BST<Person> binarySearchTree2 = new BST<>(new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return o2.getAge() - o1.getAge();
			}
		});
		for (int i = 0; i < data.length; i++) {
			binarySearchTree2.add(new Person(data[i], data[i].toString()));
		}
		BinaryTrees.println(binarySearchTree2);

	}

	static void test3() {
		BST<Integer> binarySearchTree = new BST<>();

		for (int i = 0; i < 30; i++) {
			binarySearchTree.add(((int) (Math.random() * 100)));
		}
		BinaryTrees.println(binarySearchTree);

//		String string = BinaryTrees.printString(binarySearchTree);
//		string += "\n";
//		Files.writeToFile("/Users/adam/Documents/Developer/1.txt", string, true);

	}

	static void test4() {
		BST<Person> binarySearchTree1 = new BST<>();
		binarySearchTree1.add(new Person(10, "Jack"));
		binarySearchTree1.add(new Person(12, "Rose"));
		binarySearchTree1.add(new Person(6, "Adam"));

		binarySearchTree1.add(new Person(10, "Michael"));
		BinaryTrees.println(binarySearchTree1);
	}

	static void test5() {
		Integer data[] = new Integer[] { 7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12 };

		BST<Integer> binarySearchTree1 = new BST<>();
		for (int i = 0; i < data.length; i++) {
			binarySearchTree1.add(data[i]);
		}
		BinaryTrees.println(binarySearchTree1);
//		binarySearchTree1.preorderTraversal();
//		binarySearchTree1.inorderTraversal();
//		binarySearchTree1.levelOrderTraversal(new Visitor<Integer>() {
//			@Override
//			public void visit(Integer element) {
//				System.out.print("_" + element + "_");
//			}
//		});

	}

	static void test6() {
		Integer data[] = new Integer[] { 7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12 };

		BST<Integer> binarySearchTree1 = new BST<>();
		for (int i = 0; i < data.length; i++) {
			binarySearchTree1.add(data[i]);
		}
		BinaryTrees.println(binarySearchTree1);
//		binarySearchTree1.preorderTraversal();
//		binarySearchTree1.inorderTraversal();

//		binarySearchTree1.preorderTraversal(new Visitor<Integer>() {
//
//			public boolean visit(Integer element) {
//				System.out.print(element + "_");
//				return element == 1 ? true : false;
//			}
//		});
//		System.out.println();
//		binarySearchTree1.inorderTraversal(new Visitor<Integer>() {
//
//			public boolean visit(Integer element) {
//				System.out.print(element + "_");
//				return element == 4 ? true : false;
//			}
//		});
//		System.out.println();
//		binarySearchTree1.postorderTraversal(new Visitor<Integer>() {
//
//			public boolean visit(Integer element) {
//				System.out.print(element + "_");
//				return element == 5 ? true : false;
//			}
//		});
//		System.out.println();
//		binarySearchTree1.levelOrderTraversal(new Visitor<Integer>() {
//			public boolean visit(Integer element) {
//				System.out.print(element + "_");
//				return element == 2 ? true : false;
//			}
//		});
//		System.out.println(binarySearchTree1);
		System.out.println(binarySearchTree1.height());

	}

	static void test7() {
		BST<Integer> binarySearchTree = new BST<>();

		for (int i = 0; i < 30; i++) {
			binarySearchTree.add(((int) (Math.random() * 100)));
		}
		BinaryTrees.println(binarySearchTree);
		System.out.println(binarySearchTree.height());

	}

	static void test8() {
		Integer data[] = new Integer[] { 7, 4, 9, 2, 1 };
		BST<Integer> binarySearchTree = new BST<>();
		for (int i = 0; i < data.length; i++) {
			binarySearchTree.add(data[i]);
		}

		BinaryTrees.println(binarySearchTree);
		System.out.println(binarySearchTree.isComplete());
	}

	static void test9() {
		Integer data[] = new Integer[] { 7, 4, 9, 2, 5, 8, 11, 3, 12, 1 };
		BST<Integer> binarySearchTree = new BST<>();
		for (int i = 0; i < data.length; i++) {
			binarySearchTree.add(data[i]);
		}

		BinaryTrees.println(binarySearchTree);
		binarySearchTree.remove(9);
		BinaryTrees.println(binarySearchTree);
		System.out.println(binarySearchTree.isComplete());
	}

	public static void main(String[] args) {
		test9();

	}

}
