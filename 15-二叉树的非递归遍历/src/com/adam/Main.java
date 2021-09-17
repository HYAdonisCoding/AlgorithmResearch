package com.adam;

import com.adam.printer.BinaryTrees;
import com.adam.tree.BST;
import com.adam.tree.BinaryTree.Visitor;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Integer[] dataIntegers = { 7, 4, 9, 2, 5, 8, 11 };
		BST<Integer> bst = new BST<>();
		for (int i = 0; i < dataIntegers.length; i++) {
			bst.add(dataIntegers[i]);
		}
		// 树状打印
		BinaryTrees.println(bst);
		// 遍历器
		StringBuilder sBuilder = new StringBuilder();
		Visitor<Integer> visitor = new Visitor() {

			@Override
			public boolean visit(Object element) {
				sBuilder.append(element).append(" ");
				return false;
			}

		};

		/// 遍历
		sBuilder.delete(0, sBuilder.length());
		bst.preorder(visitor);
		Asserts.test(sBuilder.toString().equals("7 4 2 5 9 8 11 "));
		System.out.println(sBuilder.toString());

		sBuilder.delete(0, sBuilder.length());
		bst.inorder(visitor);
		Asserts.test(sBuilder.toString().equals("2 4 5 7 8 9 11 "));
		System.out.println(sBuilder.toString());

		sBuilder.delete(0, sBuilder.length());
		bst.postorder(visitor);
		Asserts.test(sBuilder.toString().equals("2 5 4 8 11 9 7 "));
		System.out.println(sBuilder.toString());
	}

}
