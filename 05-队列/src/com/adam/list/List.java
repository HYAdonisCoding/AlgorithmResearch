package com.adam.list;

public interface List<E> {
	static final int ELEMENT_NOT_FOUND = -1;

	void clear();

	int size();

	boolean isEmpty();

	boolean contains(E element);

	void add(E element);

	E get(int idx);

	E set(int idx, E element);

	void add(int idx, E element);

	E remove(int idx);

	void remove(E element);

	int indexOf(E element);
}
