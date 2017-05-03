package com.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.todo.repository.ToDoRepository;
import com.todo.repository.entity.ToDo;

@Service("ToDoService")
@Scope("singleton")
public class ToDoServiceImpl implements ToDoService {

	@Autowired
	ToDoRepository toDoRepository;

	@Override
	public List<ToDo> getAllTodo() {
		return toDoRepository.findAll();
	}

	@Override
	public ToDo getToDoById(long id) {
		return toDoRepository.findOne(id);
	}

	@Override
	public ToDo saveToDo(ToDo toDo) {
		return toDoRepository.save(toDo);
	}

	@Override
	public void removeToDo(ToDo toDo) {
		toDoRepository.delete(toDo);
	}

}
