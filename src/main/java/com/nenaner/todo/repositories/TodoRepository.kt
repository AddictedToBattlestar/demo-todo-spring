package com.nenaner.todo.repositories

import com.nenaner.todo.entities.TodoEntity
import org.springframework.data.repository.CrudRepository

interface TodoRepository: CrudRepository<TodoEntity, Long> {
    fun findAllByComplete(isComplete: Boolean): Iterable<TodoEntity>
}