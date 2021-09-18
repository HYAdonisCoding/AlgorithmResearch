package com.adam;

@SuppressWarnings("unchecked")
public class ArrayList<E> {
	private int size;

	private E[] elements;

	private static final int DEFAULT_CAPACITY = 10;
	private static final int ELEMENT_NOT_FOUND = -1;

	public ArrayList(int capaticy) {
		capaticy = capaticy < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capaticy;
		elements = (E[]) new Object[capaticy];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(E element) {
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}

	public void add(E element) {
		add(size, element);
	}

	public E get(int idx) {
		rangeCheck(idx);
		return elements[idx];
	}

	public E set(int idx, E element) {
		rangeCheck(idx);
		E oldE = elements[idx];
		elements[idx] = element;
		return oldE;
	}

	public void add(int idx, E element) {
		rangeCheckForAdd(idx);
		ensureCapacity(idx);
		for (int i = size; i > idx; i--) {
			elements[i] = elements[i - 1];
		}
		elements[idx] = element;
		size++;

	}

	public E remove(int idx) {
		rangeCheck(idx);
		E oldE = elements[idx];
		for (int i = idx + 1; i < size; i++) {
			elements[i - 1] = elements[i];
		}
//		size--;
//		elements[size] = null;
//		
		// 等价
		elements[--size] = null;
		return oldE;
	}

	public void remove(E element) {
		remove(indexOf(element));
	}

	public int indexOf(E element) {
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (elements[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (elements[i].equals(element)) {
					return i;
				}
			}
		}

		return ELEMENT_NOT_FOUND;
	}

	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity > capacity) {
			return;
		}
		// 新容量为旧容量的1.5倍
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
		System.out.println("扩容:" + oldCapacity + "->" + newCapacity);
	}

	private void outOfBounds(int idx) {
		throw new IndexOutOfBoundsException("idx:" + idx + ", Size:" + size);
	}

	private void rangeCheck(int idx) {
		if (idx < 0 || idx >= size) {
			outOfBounds(idx);
		}
	}

	private void rangeCheckForAdd(int idx) {
		if (idx < 0 || idx > size) {
			outOfBounds(idx);
		}
	}

	@Override
	public String toString() {
		//
//		return "ArrayList [size=" + size + ", elements=" + Arrays.toString(elements) + "]";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("ArrayList [size=").append(size).append(", elements=(");
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				stringBuilder.append(", ");
			}

			stringBuilder.append(elements[i]);
		}
		stringBuilder.append(")]");
		return stringBuilder.toString();
	}
}
