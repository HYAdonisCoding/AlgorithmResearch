package com.adam.list;

// 有缩容的
@SuppressWarnings("unchecked")
public class ArrayList2<E> extends AbstractList<E> {

	private E[] elements;

	private static final int DEFAULT_CAPACITY = 10;

	public ArrayList2(int capaticy) {
		capaticy = capaticy < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capaticy;
		elements = (E[]) new Object[capaticy];
	}

	public ArrayList2() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;

		if (elements != null && elements.length > DEFAULT_CAPACITY) {
			elements = (E[]) new Object[DEFAULT_CAPACITY];
		}

	}

	@Override
	public E get(int idx) {
		rangeCheck(idx);
		return elements[idx];
	}

	@Override
	public E set(int idx, E element) {
		rangeCheck(idx);
		E oldE = elements[idx];
		elements[idx] = element;
		return oldE;
	}

	@Override
	public void add(int idx, E element) {
		rangeCheckForAdd(idx);
		ensureCapacity(idx);
		for (int i = size; i > idx; i--) {
			elements[i] = elements[i - 1];
		}
		elements[idx] = element;
		size++;

	}

	@Override
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
		trim();
		return oldE;
	}

	@Override
	public void remove(E element) {
		remove(indexOf(element));
	}

	@Override
	public int indexOf(E element) {
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (elements[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (element.equals(elements[i])) {
					return i;
				}
			}
		}

		return ELEMENT_NOT_FOUND;
	}

	private void trim() {
		int capacity = elements.length;
		int newCapacity = capacity >> 1;
		if (size >= (newCapacity) || capacity < DEFAULT_CAPACITY) {
			return;
		}
		/// 剩余空间还很多

		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;

		System.out.println("缩容:" + capacity + "->" + newCapacity);

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
