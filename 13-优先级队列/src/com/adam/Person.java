package com.adam;

public class Person implements Comparable<Person> {
	private String name;
	private int boneBreak;

	public Person(String name, int boneBreak) {
		super();
		this.name = name;
		this.boneBreak = boneBreak;
	}

	@Override
	public int compareTo(Person p) {
		// TODO Auto-generated method stub
		return this.boneBreak - p.boneBreak;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", boneBreak=" + boneBreak + "]";
	}

}
