package com.nenaner.todo.entities

import com.nenaner.todo.models.Todo
import jakarta.persistence.*
import org.hibernate.envers.Audited
import java.time.Instant

@Audited
@Entity
@Table(name = "todo")
class TodoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var complete: Boolean = false,
    var title: String,
    var description: String,
    var dueDate: Instant?
) {
    companion object {
        fun fromModel(sourceTodo: Todo): TodoEntity {
            return TodoEntity(
                sourceTodo.id,
                sourceTodo.complete,
                sourceTodo.title,
                sourceTodo.description,
                sourceTodo.dueDate
            )
        }
    }
}