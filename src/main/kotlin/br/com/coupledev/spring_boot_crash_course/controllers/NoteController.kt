package br.com.coupledev.spring_boot_crash_course.controllers

import br.com.coupledev.spring_boot_crash_course.datasource.models.Note
import br.com.coupledev.spring_boot_crash_course.datasource.repository.NoteRepository
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
@RequestMapping("/notes")
class NoteController(
    private val repository: NoteRepository
) {

    data class NoteRequest(
        val id: String?,
        val title: String,
        val content: String,
        val color: Long,
    )

    data class NoteResponse(
        val id: String,
        val title: String,
        val content: String,
        val color: Long,
        val createdAt: Instant,
    )

    @PostMapping
    fun save(@RequestBody body: NoteRequest): NoteResponse {
        val note = repository.save(
            Note(
                id = body.id?.let { ObjectId(it) } ?: ObjectId.get(),
                ownerId = ObjectId(),
                title = body.title,
                content = body.content,
                color = body.color,
                createdAt = Instant.now()
            )
        )

        return note.toResponse()
    }

    @GetMapping
    fun findByOwnerId(
        @RequestParam(required = true) ownerId: String,
    ): List<NoteResponse> {
        return repository.findByOwnerId(ObjectId(ownerId)).map { it.toResponse() }
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteById(@PathVariable id: String) {
        repository.deleteById(ObjectId(id))
    }

    private fun Note.toResponse(): NoteResponse {
        return NoteResponse(
            id = this.id.toString(),
            title = this.title,
            content = this.content,
            color = this.color,
            createdAt = this.createdAt
        )
    }

}