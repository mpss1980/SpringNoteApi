package br.com.coupledev.spring_boot_crash_course.datasource.repository

import br.com.coupledev.spring_boot_crash_course.datasource.models.Note
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface NoteRepository: MongoRepository<Note, ObjectId> {
    fun findByOwnerId(ownerId: ObjectId): List<Note>
}