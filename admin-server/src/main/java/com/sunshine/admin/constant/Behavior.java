package com.sunshine.admin.constant;

public enum Behavior {

	SAVE_OR_UPDATE("saveUpdate"), DEL("del");

	Behavior(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
