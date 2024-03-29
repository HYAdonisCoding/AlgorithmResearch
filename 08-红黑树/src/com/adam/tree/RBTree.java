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
		// 如果删除的节点是红色
//		if (isRed(node)) {
//			return;
//		}
		/// 用于取代node的节点是红色
		if (isRed(node)) {
			black(node);
			return;
		}
		Node<E> parent = node.parent;
		// 删除的是根节点
		if (parent == null) {
			return;
		}
		/// 删除的是黑色的叶子节点
		// 判断被删除的node是左还是右 或者 是下溢
		boolean left = parent.left == null || node.isLeftChild();
		Node<E> sibling = left ? parent.right : parent.left;
		if (left) {// 被删除的节点在左边,兄弟节点在右边
			if (isRed(sibling)) {// 兄弟节点是红色
				black(sibling);
				red(parent);
				rotateLeft(parent);
				// 更换兄弟
				sibling = parent.right;
			}
			// 兄弟节点是黑色
			if (isBlack(sibling.left) && isBlack(sibling.right)) {
				// 兄弟节点没有一个红色子节点,父节点要向下跟兄弟节点合并
				boolean parentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				if (parentBlack) {
					// 下溢
					afterRemove(parent);
				}
			} else {
				// 兄弟节点至少有一个红色子节点

				// 兄弟节点的左边是黑色,兄弟要先旋转
				if (isBlack(sibling.right)) {
					rotateRight(sibling);
					sibling = parent.right;
				}
				color(sibling, colorOf(parent));
				black(sibling.right);
				black(parent);
				rotateLeft(parent);
			}
		} else {// 被删除的节点在右边,兄弟节点在左边
			if (isRed(sibling)) {// 兄弟节点是红色
				black(sibling);
				red(parent);
				rotateRight(parent);
				// 更换兄弟
				sibling = parent.left;
			}
			// 兄弟节点是黑色
			if (isBlack(sibling.left) && isBlack(sibling.right)) {
				// 兄弟节点没有一个红色子节点,父节点要向下跟兄弟节点合并
				boolean parentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				if (parentBlack) {
					// 下溢
					afterRemove(parent);
				}
			} else {
				// 兄弟节点至少有一个红色子节点

				// 兄弟节点的左边是黑色,兄弟要先旋转
				if (isBlack(sibling.left)) {
					rotateLeft(sibling);
					sibling = parent.left;
				}
				color(sibling, colorOf(parent));
				black(sibling.left);
				black(parent);
				rotateRight(parent);
			}
		}
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
