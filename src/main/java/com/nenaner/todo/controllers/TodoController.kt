package com.nenaner.todo.controllers

import com.nenaner.todo.models.Todo
import com.nenaner.todo.repositories.TodoRepository
import com.nenaner.todo.services.TodoCreationService
import com.nenaner.todo.services.TodoUpdateService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/todo")
class TodoController(
    private val repository: TodoRepository,
    private val creationService: TodoCreationService,
    private val updateService: TodoUpdateService
) {

    @GetMapping
    fun findAll(@RequestParam(name = "isComplete", required = false) isComplete: Boolean?): Iterable<Todo> {
        return if (isComplete == null) {
            val todos = repository.findAll()
            Todo.fromEntities(todos)
        } else {
            val todos = repository.findAllByComplete(isComplete)
            Todo.fromEntities(todos)
        }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Optional<Todo> = Todo.fromOptionalEntity(repository.findById(id))

    @PostMapping
    fun create(@Valid @RequestBody todoBeingCreated: Todo): Todo = creationService.create(todoBeingCreated)

    @PutMapping("{id}")
    fun update(@Valid @RequestBody todoBeingUpdated: Todo): Todo = updateService.update(todoBeingUpdated)

    @DeleteMapping("{id}")
    fun update(@PathVariable id: Long) = repository.deleteById(id)
}