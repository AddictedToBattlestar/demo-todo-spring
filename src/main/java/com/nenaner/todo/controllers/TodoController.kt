package com.nenaner.todo.controllers

import com.nenaner.todo.entities.TodoEntity
import com.nenaner.todo.repositories.TodoRepository
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/todo")
class TodoController(private val todoRepository: TodoRepository) {
    @GetMapping
    fun findAll(@RequestParam(name = "isComplete", required = false) isComplete: Boolean?): Iterable<TodoEntity> {
        return if (isComplete == null) {
            todoRepository.findAll()
        } else {
            todoRepository.findAllByComplete(isComplete)
        }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Optional<TodoEntity> = todoRepository.findById(id)

    @PostMapping
    fun create(@Valid @RequestBody todoBeingCreated: TodoEntity): TodoEntity = todoRepository.save(todoBeingCreated)

    @PutMapping("{id}")
    fun update(@Valid @RequestBody todoBeingUpdated: TodoEntity): TodoEntity = todoRepository.save(todoBeingUpdated)

    @DeleteMapping("{id}")
    fun update(@PathVariable id: Long) = todoRepository.deleteById(id)
}