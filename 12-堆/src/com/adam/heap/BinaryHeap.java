package com.adam.heap;

import java.util.Comparator;

import com.adam.printer.BinaryTreeInfo;

/*
 * 二叉堆(最大堆)
 */
@SuppressWarnings({ "unchecked", "unused" })
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
	private E[] elements;
	private static final int DEFAULT_CAPICITY = 10;

	public BinaryHeap(Comparator<E> comparator) {
		super(comparator);
		this.elements = (E[]) new Object[DEFAULT_CAPICITY];
	}

	public BinaryHeap() {
		this(null);
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	@Override
	public void add(E element) {
		elementNotNullCheck(element);
		ensureCapacity(size + 1);

		elements[size++] = element;
		siftUp(size - 1);
	}

	@Override
	public E get() {
		emptyCheck();
		return elements[0];
	}

	@Override
	public E remove() {
		emptyCheck();
		int lastIndex = --size;
		E root = elements[0];
		elements[0] = elements[lastIndex];
		elements[lastIndex] = null;

		siftDown(0);
		return root;
	}

	@Override
	public E replace(E element) {

		return null;
	}

	/*
	 * 让index位置的元素下滤
	 */
	private void siftDown(int index) {
		E element = elements[index];
		int half = size >> 1;
		// 第一个叶子结点的索引 非叶子结点的数量
		while (index < half) { // index是非叶子结点 小于第一个叶子结点的索引
			// index有两种情况
			// 1.只有左子节点
			// 2.同时有右子结点

			// 默认为左子节点的索引
			int childIndex = (index << 1) + 1;
			E child = elements[childIndex];
			// 右子结点
			int rightIndex = childIndex + 1;
			// 找出左右子节点最大的那个
			if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
				child = elements[childIndex = rightIndex];
			}
			if (compare(element, child) >= 0) {
				break;
			}
			// 将子节点存放到index 位置
			elements[index] = child;
			// 重新计算索引
			index = childIndex;
		}
		elements[index] = element;
	}

	/*
	 * 让index位置的元素上滤
	 */
	private void siftUp(int index) {
		E e = elements[index];
//		while (index > 0) {
//			int pIndex = (index - 1) >> 1;
//			E p = elements[pIndex];
//			if (compare(e, p) < 0) {
//				return;
//			}
//			// 交换 位置
//			E tmpE = elements[index];
//			elements[index] = p;
//			elements[pIndex] = tmpE;
//			// 重新计算索引
//			index = pIndex;
//		}
		while (index > 0) {
			int parentIndex = (index - 1) >> 1;
			E parent = elements[parentIndex];
			if (compare(e, parent) < 0) {
				break;
			}
			// 将父元素存储在index位置
			elements[index] = parent;
			// 重新计算索引
			index = parentIndex;
		}
		elements[index] = e;
	}

	/**
	 * 保证要有capacity的容量
	 * 
	 * @param capacity
	 */
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity >= capacity)
			return;

		// 新容量为旧容量的1.5倍
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
	}

	private void emptyCheck() {
		if (size == 0) {
			throw new IndexOutOfBoundsException("BinaryHeap is Empty!");
		}
	}

	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must not be null!");
		}
	}

	@Override
	public Object root() {

		return 0;
	}

	@Override
	public Object left(Object node) {
		int index = (int) node;
		index = (index << 1) + 1;
		return index >= size ? null : index;
	}

	@Override
	public Object right(Object node) {
		int index = (int) node;
		index = (index << 1) + 2;
		return index >= size ? null : index;
	}

	@Override
	public Object string(Object node) {
		return elements[(int) node];
	}
}
