package com.adam.set;

import java.util.Comparator;

import com.adam.tree.BinaryTree;
import com.adam.tree.RBTree;

public class TreeSet<E> implements Set<E> {
	private RBTree<E> tree;

	public TreeSet() {
		this(null);
	}

	public TreeSet(Comparator<E> comparator) {
		tree = new RBTree<>(comparator);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return tree.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return tree.isEmpty();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		tree.clear();
	}

	@Override
	public boolean contains(E element) {
		// TODO Auto-generated method stub
		return tree.contains(element);
	}

	@Override
	public void add(E element) {
		// TODO Auto-generated method stub
		tree.add(element);
	}

	@Override
	public void remove(E element) {
		// TODO Auto-generated method stub
		tree.remove(element);
	}

	@Override
	public void traversal(Visitor<E> visitor) {
		// TODO Auto-generated method stub
		tree.inorder(new BinaryTree.Visitor<E>() {
			@Override
			public boolean visit(E element) {
				// TODO Auto-generated method stub
				return visitor.visit(element);
			}
		});

	}

}
