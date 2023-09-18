package com.nenaner.todo.services

import com.nenaner.todo.models.Todo
import com.nenaner.todo.repositories.TodoRepository
import org.springframework.stereotype.Service

@Service
class TodoUpdateService (private val todoRepository: TodoRepository) {
    fun update(todoBeingUpdated: Todo): Todo {
        val existingTodo = todoRepository.findById(todoBeingUpdated.id)
            .orElseThrow { RuntimeException("The id of the Todo provided to be updated does not exist (id: ${todoBeingUpdated.id}") }
        existingTodo.title = todoBeingUpdated.title
        existingTodo.description = todoBeingUpdated.description
        existingTodo.dueDate = todoBeingUpdated.dueDate
        return Todo.fromEntity(existingTodo)
    }
}