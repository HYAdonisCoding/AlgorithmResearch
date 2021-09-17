package com.adam;

import java.util.HashMap;

public class Trie<E> {
	private int size;

	private Node<E> root;

	public int size() {
		// 元素的数量
		return size;
	}

	public boolean isEmpty() {
		// 是否为空
		return size == 0;
	}

	public void clear() {
		// 清空
		size = 0;
		root = null;
	}

	public E add(String key, E value) {
		// 添加元素
		keyCheck(key);

		/// 创建根节点
		if (root == null) {
			root = new Node<>(null);
		}

		Node<E> node = root;
		int len = key.length();
		for (int i = 0; i < len; i++) {
			char c = key.charAt(i);
			boolean emptyChildren = node.children == null;
			Node<E> childNode = emptyChildren ? null : node.children.get(c);
			if (childNode == null) {
				childNode = new Node<>(node);
				childNode.character = c;
				node.children = emptyChildren ? new HashMap<>() : node.children;
				node.children.put(c, childNode);
			}
			node = childNode;
		}
		if (node.word) {// 已经存在
			// 覆盖
			E oldE = node.value;
			node.value = value;

			return oldE;
		}

		// 新增一个单词
		node.word = true;
		node.value = value;
		size++;
		return null;

	}

	public E get(String key) {
		Node<E> node = node(key);
		return (node != null && node.word == true) ? node.value : null;
	}

	public boolean contains(String key) {
		// 是否包含
		Node<E> node = node(key);
		return node != null && node.word == true;
	}

	public E remove(String key) {
		// 删除元素
		Node<E> node = node(key);
		if (node == null || !node.word) {
			return null;
		}
		size--;
		E oldValue = node.value;

		// 如果还有子节点
		if (node.children != null && !node.children.isEmpty()) {

			node.value = null;
			return oldValue;
		}
		/// 最后一个node
		/// 如果没有子节点
		Node<E> parentNode = null;
		while ((parentNode = node.parent) != null) {
			node.parent.children.remove(node.character);
			if (parentNode.word || !parentNode.children.isEmpty()) {
				break;
			}
			node = parentNode;
		}

		return oldValue;
	}

	public boolean startsWith(String prefix) {
		// 是否为空
		return node(prefix) != null;
	}

	private Node<E> node(String key) {
		keyCheck(key);

		Node<E> node = root;
		int len = key.length();
		for (int i = 0; i < len; i++) {
			if (node == null || node.children == null || node.children.isEmpty()) {
				return null;
			}
			char c = key.charAt(i);
			node = node.children.get(c);

		}
		return node;
	}

	private void keyCheck(String key) {
		if (key == null || key.length() == 0) {
			throw new IllegalArgumentException("key must not be empty");
		}
	}

	private static class Node<E> {
		Node<E> parent;
		HashMap<Character, Node<E>> children;
		Character character;
		E value;
		boolean word;// 是否为单词的结尾（是否为一个完整的单词）

		public Node(Node<E> parent) {
			super();
			this.parent = parent;
		}

	}
}
