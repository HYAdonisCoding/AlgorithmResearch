package com.adam;

import java.util.Comparator;

import com.adam.heap.BinaryHeap;
import com.adam.printer.BinaryTrees;

public class Main {

	private static void test1() {
		BinaryHeap<Integer> heap = new BinaryHeap<>();
		heap.add(68);
		heap.add(72);
		heap.add(43);
		heap.add(50);
		heap.add(38);
		heap.add(90);
		BinaryTrees.println(heap);
//		heap.remove();
//		BinaryTrees.println(heap);
		System.out.println(heap.replace(70));
		BinaryTrees.println(heap);
	}

	private static void test2() {
		Integer[] data = { 88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37 };
//		BinaryHeap<Integer> heap = new BinaryHeap<>();
//		for (int i = 0; i < data.length; i++) {
//			heap.add(data[i]);
//		}
		BinaryHeap<Integer> heap = new BinaryHeap<>(data);
		BinaryTrees.println(heap);
		data[0] = 10;
		data[1] = 20;
		BinaryTrees.println(heap);
	}

	public static void test3() {
		Integer[] data = { 88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37 };
//		BinaryHeap<Integer> heap = new BinaryHeap<>();
//		for (int i = 0; i < data.length; i++) {
//			heap.add(data[i]);
//		}
		BinaryHeap<Integer> heap = new BinaryHeap<>(data, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2 - o1;
			}

		});
		BinaryTrees.println(heap);
	}

	static void test4() {
		/// 新建一个小顶堆
		BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2 - o1;
			}

		});
		// 找出最大的前k个数
		int k = 4;
		Integer[] data = { 100, 90, 50, 88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37, 99 };
		for (int i = 0; i < data.length; i++) {
			if (heap.size() < k) {// 前k个数添加到小堆顶
				heap.add(data[i]);
			} else if (data[i] > heap.get()) {// 如果是第K+1个数,并且大于堆顶元素
				heap.replace(data[i]);
			}
		}
		BinaryTrees.println(heap);
	}

	public static void main(String[] args) {
		test4();
	}

}
