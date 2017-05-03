package com.todo.web;

public class ToDoIdNotFoundException extends RuntimeException {

	private String todo;

	public ToDoIdNotFoundException(String todo) {
		this.todo = todo;
	}

	public String getTodo() {
		return todo;
	}

}
