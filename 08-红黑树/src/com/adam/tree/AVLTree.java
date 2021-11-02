package com.adam.tree;

import java.util.Comparator;

@SuppressWarnings("unused")
public class AVLTree<E> extends BBST<E> {

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
	protected void afterRemove(Node<E> node) {
		// 删除之后
		while ((node = node.parent) != null) {
			if (isBalanced(node)) {// 平衡
				// 更新高度
				updateHeight(node);

			} else {
				// 恢复平衡
				rebalance(node);
			}
		}
	}

	@Override
	protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
		super.afterRotate(grand, parent, child);
		// 更新高度
		updateHeight(grand);
		updateHeight(parent);
	}

	@Override
	protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
		super.rotate(r, a, b, c, d, e, f, g);
		updateHeight(b);
		updateHeight(f);
		updateHeight(d);
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
		if (parent.isLeftChild()) { // L
			if (node.isLeftChild()) { // LL
				rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
			} else { // LR
				rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
			}
		} else { // R
			if (node.isLeftChild()) { // RL
				rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
			} else { // RR
				rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
			}
		}
	}

	/// 恢复平衡
	private void rebalance2(Node<E> grand) {
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
