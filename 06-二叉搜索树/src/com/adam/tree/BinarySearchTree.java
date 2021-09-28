package com.adam.tree;

import java.util.Comparator;

import com.adam.printer.BinaryTreeInfo;

@SuppressWarnings({ "unchecked", "unused" })
public class BinarySearchTree<E> implements BinaryTreeInfo {
	private int size;
	Node<E> root;
	private Comparator<E> comparator;

	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	public BinarySearchTree() {
		this(null);
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {

	}

	public void add(E element) {
		elementNotNullCheck(element);
		// 添加的是第一个节点
		if (root == null) {
			root = new Node<>(element, null);
			size++;
			return;
		}
		// 添加的不是第一个节点
		Node<E> node = root;

		// 父节点
		Node<E> parent = root;
		int cmp = 0;
		while (node != null) {
			cmp = compare(element, node.element);
			parent = node;
			if (cmp > 0) {
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} else {
				// 相等
				node.element = element;
				break;
			}

		}
		/// 看看插入到父节点的哪一个位置
		Node<E> newNode = new Node<>(element, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else if (cmp < 0) {
			parent.left = newNode;
		}
		size++;
	}

	public void remove(E element) {

	}

	public boolean contains(E element) {

		return false;
	}

	// 返回值 0 ：e1和e2相等，大于0： e1大于e2， 小于0：e1小于e2
	private int compare(E e1, E e2) {
		if (comparator != null) {
			return comparator.compare(e1, e2);
		}
		return ((Comparable) e1).compareTo(e2);
	}

	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element can not be null");
		}
	}

	private static class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;

		Node<E> parent;

		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}

	}

	@Override
	public Object root() {
		return root;
	}

	@Override
	public Object left(Object node) {
		return ((Node<E>) node).left;
	}

	@Override
	public Object right(Object node) {
		return ((Node<E>) node).right;
	}

	@Override
	public Object string(Object node) {
		Node<E> myNode = ((Node<E>) node);
		String parentString = "null";
		if (myNode.parent != null) {
			parentString = myNode.parent.element.toString();
		}
		return myNode.element + "_p(" + parentString + ")";
	}
}
