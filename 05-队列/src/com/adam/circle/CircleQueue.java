package com.adam.circle;

@SuppressWarnings("unchecked")
public class CircleQueue<E> {
	private int front;
	private int size;
	private E[] elements;

	private static final int DEFAULT_CAPACITY = 10;

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("CircleQueue [size=").append(size).append(", capacity=").append(elements.length)
				.append(", front=").append(front).append(", elements=(");
		for (int i = 0; i < elements.length; i++) {
			if (i != 0) {
				stringBuilder.append(", ");
			}

			stringBuilder.append(elements[i]);
		}
		stringBuilder.append(")]");
		return stringBuilder.toString();
	}

	public CircleQueue() {
		elements = (E[]) new Object[DEFAULT_CAPACITY];
	}

	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[index(i)] = null;
		}
		size = 0;
		front = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void enQueue(E element) {
		ensureCapacity(size + 1);
		elements[index(size)] = element;
		size++;
	}

	public E deQueue() {
		E e = elements[front];
		elements[front] = null;
		front = index(1);
		size--;
		return e;
	}

	public E front() {

		return elements[front];
	}

	private int index(int index) {
		index += front;
		return index - (index >= elements.length ? elements.length : 0);
	}

	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity >= capacity) {
			return;
		}
		// 新容量为旧容量的1.5倍
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[index(i)];
		}
		elements = newElements;
		// 重置front
		front = 0;
		System.out.println("扩容:" + oldCapacity + "->" + newCapacity);
	}
}
