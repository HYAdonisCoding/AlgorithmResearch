package com.adam.list;

public abstract class AbstractList<E> implements List<E> {
	protected int size;

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(E element) {
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}

	@Override
	public void add(E element) {
		add(size, element);
	}

	protected void outOfBounds(int idx) {
		throw new IndexOutOfBoundsException("idx:" + idx + ", Size:" + size);
	}

	protected void rangeCheck(int idx) {
		if (idx < 0 || idx >= size) {
			outOfBounds(idx);
		}
	}

	protected void rangeCheckForAdd(int idx) {
		if (idx < 0 || idx > size) {
			outOfBounds(idx);
		}
	}

}
