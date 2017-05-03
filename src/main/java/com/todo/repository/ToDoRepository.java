package com.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.repository.entity.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {

}
