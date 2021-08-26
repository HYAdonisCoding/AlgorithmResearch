package com.adam.set;

import com.adam.list.LinkedList;
import com.adam.list.List;

public class ListSet<E> implements Set<E> {
	private List<E> list = new LinkedList<>();

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return list.isEmpty();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		list.clear();
	}

	@Override
	public boolean contains(E element) {
		// TODO Auto-generated method stub
		return list.contains(element);
	}

	@Override
	public void add(E element) {
		// TODO Auto-generated method stub
//		if (list.contains(element)) {
//			return;
//		}
//		list.add(element);
		int index = list.indexOf(element);
		if (index != List.ELEMENT_NOT_FOUND) {// 存在就覆盖
			list.set(index, element);
		} else {// 不存在就添加
			list.add(element);
		}
	}

	@Override
	public void remove(E element) {
		// TODO Auto-generated method stub
		int index = list.indexOf(element);
		if (index != List.ELEMENT_NOT_FOUND) {
			list.remove(index);
		}
	}

	@Override
	public void traversal(Visitor<E> visitor) {
		// TODO Auto-generated method stub
		if (visitor == null) {
			return;
		}

		int size = list.size();
		for (int i = 0; i < size; i++) {
			if (visitor.visit(list.get(i)))
				return;
		}
	}
}
