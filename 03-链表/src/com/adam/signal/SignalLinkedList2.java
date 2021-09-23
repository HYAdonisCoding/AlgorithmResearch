package com.adam.signal;

import com.adam.list.AbstractList;

// 增加一个虚拟头结点
@SuppressWarnings("unused")
public class SignalLinkedList2<E> extends AbstractList<E> {

	private Node<E> first;

	public SignalLinkedList2() {
		first = new Node<>(null, null);
	}

	private static class Node<E> {
		E element;
		Node<E> next;

		public Node(E element, Node<E> next) {
			super();
			this.element = element;
			this.next = next;
		}

		@Override
		protected void finalize() throws Throwable {

			super.finalize();
		}
	}

	@Override
	public void clear() {
		size = 0;
		first = null;
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

		Node<E> prev = idx == 0 ? first : node(idx - 1);
		prev.next = new Node<>(element, prev.next);

		size++;
	}

	@Override
	public E remove(int idx) {
		rangeCheck(idx);

		Node<E> prev = idx == 0 ? first : node(idx - 1);

		Node<E> node = prev.next;
		prev.next = node.next;

		size--;
		return node.element;
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

	/**
	 * 给定idx查找Node
	 */
	private Node<E> node(int idx) {
		rangeCheck(idx);

		Node<E> node = first.next;
		for (int i = 0; i < idx; i++) {
			node = node.next;
		}
		return node;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("ArrayList [size=").append(size).append(", elements=(");
		Node<E> node = first.next;
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				stringBuilder.append(", ");
			}

			stringBuilder.append(node.element);
			node = node.next;
		}
		stringBuilder.append(")]");
		return stringBuilder.toString();
	}

}
