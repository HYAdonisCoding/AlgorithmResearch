package com.adam;

import com.adam.list.LinkedList;
import com.adam.list.List;

public class Deque<E> {
	private List<E> list = new LinkedList<>();

	public void clear() {
		list.clear();
	}

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void enQueueRear(E element) {
		list.add(element);
	}

	public void enQueueFront(E element) {
		list.add(0, element);
	}

	public E deQueueFront() {

		return list.remove(0);
	}

	public E deQueueRear() {

		return list.remove(size() - 1);
	}

	public E front() {

		return list.get(0);
	}

	public E rear() {

		return list.get(size() - 1);
	}
}
