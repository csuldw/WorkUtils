package com.huawei.csic.entity;

public class UserEntity {
	int id;
	String username;
	String age;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return this.getId() + "-" + this.getAge() + "-" + this.getUsername();
	}

}