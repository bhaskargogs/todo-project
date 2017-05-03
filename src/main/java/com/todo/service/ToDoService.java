package com.todo.service;

import java.util.List;

import com.todo.repository.entity.ToDo;

public interface ToDoService {
	public List<ToDo> getAllTodo();

	public ToDo getToDoById(long id);

	public ToDo saveToDo(ToDo toDo);

	public void removeToDo(ToDo toDo);
}
