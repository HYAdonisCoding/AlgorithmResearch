package com.adam.circle;

import com.adam.list.AbstractList;

//  双向链表
@SuppressWarnings("unused")
public class CircleLinkedList<E> extends AbstractList<E> {

	private Node<E> first;
	private Node<E> last;
	private Node<E> current;

	public CircleLinkedList() {
		// TODO Auto-generated constructor stub
	}

	private static class Node<E> {
		E element;
		Node<E> prev;
		Node<E> next;

		public Node(Node<E> prev, E element, Node<E> next) {
			super();
			this.prev = prev;
			this.element = element;
			this.next = next;
		}

		@Override
		public String toString() {

			StringBuilder sb = new StringBuilder();
			if (prev != null) {
				sb.append(prev.element);
			}
			sb.append("_").append(element).append("_");
			if (next != null) {
				sb.append(next.element);
			}
			return sb.toString();
		}

		@Override
		protected void finalize() throws Throwable {

			super.finalize();
		}
	}

	public void reset() {
		current = first;
	}

	public E next() {
		if (current == null) {
			return null;
		}
		current = current.next;
		return current.element;
	}

	public E remove() {
		if (current == null) {
			return null;
		}

		Node<E> nextNode = current.next;
		E e = remove(current);
		if (current == nextNode) { // size == 0
			current = null;
		} else {
			current = nextNode;
		}

		return e;
	}

	@Override
	public void clear() {
		size = 0;
		first = null;
		last = null;
	}

	@Override
	public E get(int idx) {

		return node(idx).element;
	}

	@Override
	public E set(int idx, E element) {
		Node<E> node = node(idx);

		E oldE = node.element;
		node.element = element;
		return oldE;
	}

	@Override
	public void add(int idx, E element) {
		rangeCheckForAdd(idx);
		if (idx == size) { // 往最后添加
			Node<E> oldLastNode = last;
			last = new Node<>(oldLastNode, element, first);
			if (oldLastNode == null) {
				first = last;
				first.next = first;
				first.prev = first;
			} else {
				oldLastNode.next = last;
				first.prev = last;
			}

		} else {
			Node<E> next = node(idx);
			Node<E> prev = next.prev;
			Node<E> node = new Node<>(prev, element, next);
			next.prev = node;

			prev.next = node;

			if (next == first) { // idx == 0
				first = node;
			}
		}

		size++;
	}

	@Override
	public E remove(int idx) {
		rangeCheck(idx);

		return remove(node(idx));
	}

	@Override
	public void remove(E element) {

	}

	@Override
	public int indexOf(E element) {
		Node<E> node = first;
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (node.element == null) {
					return i;
				}
				node = node.next;
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (element.equals(node.element)) {
					return i;
				}

				node = node.next;
			}
		}

		return ELEMENT_NOT_FOUND;

	}

	private E remove(Node<E> node) {

		if (size == 1) {
			first = null;
			last = null;
		} else {

			Node<E> prev = node.prev;
			Node<E> next = node.next;
			prev.next = next;
			next.prev = prev;
			if (node == first) { // idx == 0
				first = next;
			}

			if (node == last) { // idx == size - 1
				last = prev;
			}
		}
		size--;
		return node.element;

	}

	/**
	 * 给定idx查找Node
	 */
	private Node<E> node(int idx) {
		rangeCheck(idx);
		Node<E> node = first;
		if (idx < (size >> 1)) {
			for (int i = 0; i < idx; i++) {
				node = node.next;
			}
		} else {
			node = last;
			for (int i = size - 1; i > idx; i--) {
				node = node.prev;
			}
		}

		return node;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("ArrayList [size=").append(size).append(", elements=(");
		Node<E> node = first;
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				stringBuilder.append(", ");
			}

			stringBuilder.append(node);
			node = node.next;
		}
		stringBuilder.append(")]");
		return stringBuilder.toString();
	}

}
