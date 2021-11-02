package com.adam.tree;

import java.util.Comparator;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class RBTree<E> extends BBST<E> {
	private static final boolean RED = false;
	private static final boolean BLACK = true;

	public RBTree() {
		this(null);
	}

	public RBTree(Comparator<E> comparator) {
		super(comparator);
	}

	@Override
	protected void afterAdd(Node<E> node) {
		Node<E> parent = node.parent;
		/// 添加的是根节点 或者上溢到达了根节点
		if (parent == null) {
			black(node);
			return;
		}
		// 如果父节点是黑色,直接返回
		if (isBlack(parent)) {
			return;
		}
		// uncle节点
		Node<E> uncle = parent.sibling();
		// 祖父节点
		Node<E> grand = red(parent.parent);
		if (isRed(uncle)) {// 叔父节点是红色[B树节点上溢]
			//
			black(parent);
			black(uncle);
			// 把祖父节点当做新添加的节点
			afterAdd(grand);
			return;
		}
		// 旋转
		// 叔父节点不是红色
		if (parent.isLeftChild()) {// L
			if (node.isLeftChild()) {// LL
				black(parent);
			} else {// LR
				black(node);
				rotateLeft(parent);
			}
			rotateRight(grand);
		} else {// R
			if (node.isLeftChild()) {// RL
				black(node);
				rotateRight(parent);
			} else {// RR
				black(parent);
			}
			rotateLeft(grand);
		}

	}

	@Override
	protected void afterRemove(Node<E> node) {
		// TODO Auto-generated method stub
		super.afterRemove(node);
	}

	private Node<E> red(Node<E> node) {
		return color(node, RED);
	}

	private Node<E> black(Node<E> node) {
		return color(node, BLACK);
	}

	private boolean colorOf(Node<E> node) {
		return node == null ? BLACK : ((RBNode<E>) node).color;
	}

	private boolean isBlack(Node<E> node) {
		return colorOf(node) == BLACK;
	}

	private boolean isRed(Node<E> node) {
		return colorOf(node) == RED;
	}

	private Node<E> color(Node<E> node, boolean color) {
		if (node == null) {
			return node;
		}
		((RBNode<E>) node).color = color;
		return node;
	}

	@Override
	protected Node<E> creatNode(E element, Node<E> parent) {
		return new RBNode<>(element, parent);
	}

	private class RBNode<E> extends Node<E> {
		boolean color = RED;

		public RBNode(E element, Node<E> parent) {
			super(element, parent);
		}

		@Override
		public String toString() {
			String colorString = "";
			if (color == RED) {
				colorString = "R_";
			}
			return colorString + element.toString();
		}
	}
}
