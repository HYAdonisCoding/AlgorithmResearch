package com.adam.tree;

import java.util.Comparator;

@SuppressWarnings("unused")
public class AVLTree<E> extends BST<E> {

	public Node<E> node;

	public AVLTree() {
		this(null);
	}

	public AVLTree(Comparator<E> comparator) {
		super(comparator);
	}

	@Override
	protected void afterAdd(Node<E> node) {

		// 添加之后
		while ((node = node.parent) != null) {
			if (isBalanced(node)) {// 平衡
				// 更新高度
				updateHeight(node);

			} else {
				// 恢复平衡
				rebalance(node);
				break;
			}
		}
	}

	@Override
	protected Node<E> creatNode(E element, Node<E> parent) {
		// TODO Auto-generated method stub
		return new AVLNode<>(element, parent);
	}

	/// 恢复平衡
	private void rebalance(Node<E> grand) {
		Node<E> parent = ((AVLNode<E>) grand).tallerChild();
		Node<E> node = ((AVLNode<E>) parent).tallerChild();
		if (parent.isLeftChild()) {// L
			if (node.isLeftChild()) {// LL
				rotateRight(grand);
			} else { // LR
				rotateLeft(parent);
				rotateRight(grand);
			}

		} else {// R
			if (node.isLeftChild()) {// RL
				rotateRight(parent);
				rotateLeft(grand);
			} else { // RR
				rotateLeft(grand);
			}
		}
	}

	private void updateHeight(Node<E> node) {
		((AVLNode<E>) node).updateHeight();

	}

	private void rotateLeft(Node<E> grand) {
		Node<E> parent = grand.right;
		Node<E> child = parent.left;
		grand.right = parent.left;
		parent.left = grand;
		afterRotate(grand, parent, child);
	}

	private void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> child = parent.right;
		grand.left = child;
		parent.right = grand;
		afterRotate(grand, parent, child);

	}

	private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
		// 让parent成为子树的根节点
		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else {// grand 是根节点
			root = parent;
		}
		// 更新child的parent
		if (child != null) {
			child.parent = grand;
		}
		// 更新grand的parent
		grand.parent = parent;

		// 更新高度
		updateHeight(grand);
		updateHeight(parent);
	}

	private boolean isBalanced(Node<E> node) {

		return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
	}

	private class AVLNode<E> extends Node<E> {

		int height = 1;

		public AVLNode(E element, Node<E> parent) {
			super(element, parent);
		}

		public int balanceFactor() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
			return leftHeight - rightHeight;
		}

		public void updateHeight() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
			height = 1 + Math.max(leftHeight, rightHeight);
		}

		public Node<E> tallerChild() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
			if (leftHeight > rightHeight) {
				return left;
			}
			if (leftHeight < rightHeight) {
				return right;
			}
			return isLeftChild() ? left : right;
		}

		@Override
		public String toString() {
			Node<E> myNode = ((Node<E>) node);
			String parentString = "null";
			if (myNode.parent != null) {
				parentString = myNode.parent.element.toString();
			}
			return myNode.element + "_p(" + parentString + ")_h(" + height + ")";
		}
	}
}
