package com.adam;

public class Person {
	public Person(int age, float height, String name) {
		super();
		this.age = age;
		this.height = height;
		this.name = name;
	}

	private int age;
	private float height;
	private String name;

	@Override
	public boolean equals(Object obj) {
		// 用来比较两个对象是否相等
		if (this == obj) {
			return true;
		}
//		if (obj == null || obj.getClass() != getClass()) {
		if (obj == null || !(obj instanceof Person)) {
			return false;
		}
		// 比较成员变量
		Person person = (Person) obj;
		return person.age == age && person.height == height
				&& (person.name == null ? name == null : person.name.equals(name));
	}

	@Override
	public int hashCode() {
		int hashCode = Integer.hashCode(age);
		hashCode = hashCode * 31 + Float.hashCode(height);
		hashCode = hashCode * 31 + (name != null ? name.hashCode() : 0);
		return hashCode;
	}
}
