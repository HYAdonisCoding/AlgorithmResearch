package com.adam.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

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
		root = null;
		size = 0;
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
		remove(node(element));
	}

	public boolean contains(E element) {

		return node(element) != null;
	}

	private void remove(Node<E> node) {
		if (node == null) {
			return;
		}
		size--;

		if (node.hasTwoChildren()) {// 度为2的节点
			// 找到后继节点
			Node<E> sNode = successor(node);
			// 用后继节点的值覆盖度为2的节点的值
			node.element = sNode.element;
			// 删除后继节点
			node = sNode;
		}
		// 删除node节点(node的度必然是1或者0)
		Node<E> replacementNode = node.left != null ? node.left : node.right;

		if (replacementNode != null) {// node是度为1的节点
			// 更改parent
			replacementNode.parent = node.parent;
			// 更改parent的left\right的指向
			if (node.parent == null) {// node是度为1的节点并且是根节点
				root = replacementNode;
			} else if (node == node.parent.left) {
				node.parent.left = replacementNode;
			} else {// node == node.parent.right
				node.parent.right = replacementNode;
			}
		} else if (node.parent == null) {// 叶子节点并且是根节点
			root = null;
		} else {
			// 叶子节点 但不是根节点
			if (node == node.parent.left) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
		}
	}

	private Node<E> node(E element) {
		Node<E> node = root;
		while (node != null) {
			int cmp = compare(element, node.element);
			if (cmp == 0) {
				return node;
			}
			if (cmp > 0) {
				node = node.right;
			} else {
				node = node.left;
			}
		}
		return null;
	}

//	/// 前序遍历
//	public void preorderTraversal() {
//		preorderTraversal(root);
//	}
//
//	// 中序遍历
//	public void inorderTraversal() {
//		inorderTraversal(root);
//
//	}
//
//	// 后序遍历
//	public void postorderTraversal() {
//		postorderTraversal(root);
//
//	}
//
//	// 层序遍历
//	public void levelOrderTraversal() {
//		if (root == null) {
//			return;
//		}
//		Queue<Node<E>> queue = new LinkedList<>();
//		queue.offer(root);
//		while (!queue.isEmpty()) {
//			Node<E> node = queue.poll();
//			System.out.println(node.element);
//			if (node.left != null) {
//				queue.offer(node.left);
//			}
//			if (node.right != null) {
//				queue.offer(node.right);
//			}
//		}
//	}
//
//	private void inorderTraversal(Node<E> node) {
//		if (node == null) {
//			return;
//		}
//		inorderTraversal(node.left);
//		System.out.println(node.element);
//		inorderTraversal(node.right);
//
//	}
//
//	private void postorderTraversal(Node<E> node) {
//		if (node == null) {
//			return;
//		}
//		postorderTraversal(node.left);
//		postorderTraversal(node.right);
//		System.out.println(node.element);
//
//	}
//
//	private void preorderTraversal(Node<E> node) {
//		if (node == null) {
//			return;
//		}
//		System.out.println(node.element);
//
//		preorderTraversal(node.left);
//		preorderTraversal(node.right);
//	}
	/// 前序遍历
	public void preorderTraversal(Visitor<E> visitor) {
		if (visitor == null) {
			return;
		}
		preorderTraversal(root, visitor);
	}

	// 中序遍历
	public void inorderTraversal(Visitor<E> visitor) {
		if (visitor == null) {
			return;
		}
		inorderTraversal(root, visitor);

	}

	// 后序遍历
	public void postorderTraversal(Visitor<E> visitor) {
		if (visitor == null) {
			return;
		}
		postorderTraversal(root, visitor);

	}

	private void inorderTraversal(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.stop) {
			return;
		}
		inorderTraversal(node.left, visitor);
		if (visitor.stop) {
			return;
		}
		visitor.stop = visitor.visit(node.element);
		inorderTraversal(node.right, visitor);

	}

	private void postorderTraversal(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.stop) {
			return;
		}

		postorderTraversal(node.left, visitor);
		postorderTraversal(node.right, visitor);
		if (visitor.stop) {
			return;
		}
		visitor.stop = visitor.visit(node.element);

	}

	private void preorderTraversal(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.stop) {
			return;
		}

		visitor.stop = visitor.visit(node.element);
		preorderTraversal(node.left, visitor);
		preorderTraversal(node.right, visitor);
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

	private Node<E> predecessor(Node<E> node) {
		if (node == null) {
			return null;
		}
		// 前驱节点在左子树当中（left.right.right...）
		Node<E> pNode = node.left;
		if (pNode != null) {

			while (pNode.right != null) {
				pNode = pNode.right;
			}
			return pNode;
		}
		// 从父节点和祖父节点中寻找前驱节点
		while (node.parent != null && node == node.parent.left) {
			node = node.parent;
		}
		// node.parent == null
		// node == node.parent.right
		return node.parent;
	}

	private Node<E> successor(Node<E> node) {
		if (node == null) {
			return null;
		}
		// 前驱节点在左子树当中（right.left.left...）
		Node<E> pNode = node.right;
		if (pNode != null) {

			while (pNode.left != null) {
				pNode = pNode.left;
			}
			return pNode;
		}
		// 从父节点和祖父节点中寻找前驱节点
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		// node.parent == null
		// node == node.parent.left
		return node.parent;
	}

	public void levelOrderTraversal(Visitor<E> visitor) {
		if (root == null || visitor == null) {
			return;
		}
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			if (visitor.visit(node.element)) {
				return;
			}
			;
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
	}

	public static abstract class Visitor<E> {
		boolean stop;

		/// 返回true停止遍历
		public abstract boolean visit(E element);
	}

	public boolean isComplete() {
		if (root == null) {
			return false;
		}
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);

		boolean leaf = false;
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			if (leaf && !node.isLeaf()) {
				return false;
			}
			if (node.left != null) {
				queue.offer(node.left);
			} else if (node.right != null) {
				// node.left == null && node.right != null
				return false;
			}

			if (node.right != null) {
				queue.offer(node.right);
			} else {
				// node.left == null && node.right == null
				// node.left != null && node.right == null
				leaf = true;
			}

		}
		return true;
	}
//	public boolean isComplete() {
//		if (root == null) {
//			return false;
//		}
//		Queue<Node<E>> queue = new LinkedList<>();
//		queue.offer(root);
//
//		boolean leaf = false;
//		while (!queue.isEmpty()) {
//			Node<E> node = queue.poll();
//			if (leaf && !node.isLeaf()) {
//				return false;
//			}
//			///
//			if (node.hasTwoChildren()) {
//				queue.offer(node.left);
//			} else if (node.left == null || node.right != null) {
//				return false;
//			} else {
//				leaf = true;
//				if (node.left != null) {
//					queue.offer(node.left);
//				}
//			}
//
//		}
//		return true;
//	}

	public int height() {
		if (root == null) {
			return 0;
		}
		int height = 0;
		// 每一层的元素数量
		int levelSize = 1;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();

			///
			levelSize--;
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
			if (levelSize == 0) {
				levelSize = queue.size();
				height++;
			}
		}
		return height;
	}

	public int height1() {
		return height1(root);
	}

	private int height1(Node<E> node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(height1(node.left), height1(node.right));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(root, sb, "");
		return sb.toString();
//		return "BinarySearchTree [size=" + size + ", root=" + root + ", comparator=" + comparator + "]";
	}

	private void toString(Node<E> node, StringBuilder sb, String prefixString) {
		if (node == null) {
			return;
		}

		sb.append(prefixString).append(node.element).append("\n");
		toString(node.left, sb, prefixString + "[L]");
		toString(node.right, sb, prefixString + "[R]");
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

		public boolean isLeaf() {
			return left == null && right == null;
		}

		public boolean hasTwoChildren() {

			return left != null && right != null;
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
