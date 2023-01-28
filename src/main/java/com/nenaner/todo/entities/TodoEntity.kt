package com.nenaner.todo.entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.time.Instant

@Entity
@Table(name = "todo")
class TodoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var complete: Boolean = false,
    @NotBlank(message = "The description must be provided and cannot be blank")
    var description: String,
    var dueDate: Instant?
)