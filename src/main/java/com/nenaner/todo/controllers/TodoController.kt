package com.nenaner.todo.controllers

import com.nenaner.todo.entities.TodoEntity
import com.nenaner.todo.models.Todo
import com.nenaner.todo.repositories.TodoRepository
import com.nenaner.todo.services.TodoUpdateService
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/todo")
class TodoController(
    private val todoRepository: TodoRepository,
    private val todoUpdateService: TodoUpdateService
) {
    private final val logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping
    fun findAll(@RequestParam(name = "isComplete", required = false) isComplete: Boolean?): Iterable<Todo> {
        logger.info("findAll called")
        return if (isComplete == null) {
            val todos = todoRepository.findAll()
            Todo.fromEntities(todos)
        } else {
            val todos = todoRepository.findAllByComplete(isComplete)
            Todo.fromEntities(todos)
        }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Optional<Todo> = Todo.fromOptionalEntity(todoRepository.findById(id))

    @PostMapping
    fun create(@Valid @RequestBody todoBeingCreated: Todo): Todo {
        if (todoRepository.existsById(todoBeingCreated.id)) {
            return update(todoBeingCreated)
        }
        val entityToCreate = TodoEntity.fromModel(todoBeingCreated)
        val createdTodo = todoRepository.save(entityToCreate)
        return Todo.fromEntity(createdTodo)
    }

    @PutMapping("{id}")
    fun update(@Valid @RequestBody todoBeingUpdated: Todo): Todo  {
        return todoUpdateService.update(todoBeingUpdated)
    }

    @DeleteMapping("{id}")
    fun update(@PathVariable id: Long) = todoRepository.deleteById(id)
}