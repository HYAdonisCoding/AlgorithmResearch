package com.adam.queue;

import java.util.Comparator;

import com.adam.heap.BinaryHeap;

public class PriorityQueue<E> {
	private BinaryHeap<E> heap; // 元素的数量

	public PriorityQueue(Comparator<E> comparator) {
		heap = new BinaryHeap<>(comparator);
	}

	public PriorityQueue() {
		this(null);
	}

	public int size() {
		return heap.size();
	}

	// 是否为空
	public boolean isEmpty() {
		return heap.isEmpty();
	}

	// 清空
	public void clear() {
		heap.clear();
	}

	// 添加元素
	public void enQueue(E element) {
		heap.add(element);
	}

	public E deQueue() { // 获取堆顶元素
		return heap.remove();
	}

	public E front() {
		// 删除堆顶元素
		return heap.get();
	}
}
