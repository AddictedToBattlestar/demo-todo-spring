package com.nenaner.todo.models

import com.nenaner.todo.entities.TodoEntity
import jakarta.validation.constraints.NotBlank
import java.time.Instant
import java.util.*

class Todo (
    var id: Long,
    var complete: Boolean = false,
    @NotBlank(message = "The title must be provided and cannot be blank")
    var title: String,
    @NotBlank(message = "The description must be provided and cannot be blank")
    var description: String,
    var dueDate: Instant?
) {
    companion object {
        fun fromEntity(sourceTodo: TodoEntity): Todo {
            return Todo(
                sourceTodo.id,
                sourceTodo.complete,
                sourceTodo.title,
                sourceTodo.description,
                sourceTodo.dueDate
            )
        }

        fun fromEntities(sourceTodos: Iterable<TodoEntity>): Iterable<Todo> {
            return sourceTodos.map { fromEntity(it) }
        }

        fun fromOptionalEntity(optionalTodo: Optional<TodoEntity>): Optional<Todo> {
            return if (optionalTodo.isPresent) {
                Optional.of(fromEntity(optionalTodo.get()))
            } else {
                Optional.empty()
            }
        }
    }
}