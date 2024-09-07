package com.nenaner.todo.services

import com.nenaner.todo.entities.TodoEntity
import com.nenaner.todo.models.Todo
import com.nenaner.todo.repositories.TodoRepository
import org.springframework.stereotype.Service

@Service
class TodoCreationService(
    private val updateService: TodoUpdateService,
    private val repository: TodoRepository
) {
    fun create(todoBeingCreated: Todo): Todo {
        if (repository.existsById(todoBeingCreated.id)) {
            return updateService.update(todoBeingCreated)
        }
        val entityToCreate = TodoEntity.fromModel(todoBeingCreated)
        val createdTodo = repository.save(entityToCreate)
        return Todo.fromEntity(createdTodo)
    }
}