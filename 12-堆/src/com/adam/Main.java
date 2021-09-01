package com.adam;

import com.adam.heap.BinaryHeap;
import com.adam.printer.BinaryTrees;

public class Main {

	public static void main(String[] args) {
		BinaryHeap<Integer> heap = new BinaryHeap<>();
		heap.add(68);
		heap.add(72);
		heap.add(43);
		heap.add(50);
		heap.add(38);
		heap.add(90);
		BinaryTrees.println(heap);
		heap.remove();
		BinaryTrees.println(heap);
	}

}
