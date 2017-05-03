package com.todo.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todo.repository.entity.ToDo;
import com.todo.service.ToDoService;

@RestController
public class ToDoController {

	private static final Logger logger = LoggerFactory.getLogger(ToDoController.class);

	@Autowired
	private ToDoService toDoService;

	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ToDo> getAllTodos() {
		logger.info("Getting all todo lists");
		return toDoService.getAllTodo();
	}

	@RequestMapping(value = "/todo/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ToDo getToDoById(@PathVariable Long id) throws ToDoIdNotFoundException {
		logger.info("Finding todo list with id " + id);
		ToDo toDo = toDoService.getToDoById(id);
		if (toDo == null || toDo.getId() <= 0) {
			throw new ToDoIdNotFoundException("The given todo list doesn\'t exist");
		}
		return toDo;
	}

	@RequestMapping(value = "/todo/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApplicationMessage deleteToDo(@PathVariable Long id) throws ToDoIdNotFoundException {
		logger.info("Finding todo list with id " + id);
		ToDo toDo = toDoService.getToDoById(id);
		if (toDo == null || toDo.getId() <= 0) {
			throw new ToDoIdNotFoundException("The given todo list doesn\'t exist");
		}
		toDoService.removeToDo(toDo);
		return new ApplicationMessage(HttpStatus.OK, "ToDo list has been deleted");
	}

	@RequestMapping(value = "/todo", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApplicationMessage saveToDo(@RequestBody ToDo toDo) {
		logger.info("Saving todo with details: " + toDo);
		toDoService.saveToDo(toDo);
		return new ApplicationMessage(HttpStatus.OK, "ToDo list has been added with id " + toDo.getId());
	}

	@RequestMapping(value = "/todo", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApplicationMessage updateToDo(@RequestBody ToDo toDo) throws ToDoIdNotFoundException {
		logger.info("Todo list to update with id " + toDo.getId());
		ToDo toDo2 = toDoService.getToDoById(toDo.getId());
		if (toDo2 == null || toDo2.getId() <= 0) {
			throw new ToDoIdNotFoundException("Todo list not found");
		}
		toDoService.saveToDo(toDo);
		return new ApplicationMessage(HttpStatus.OK, "Todo list has been updated");
	}
}
