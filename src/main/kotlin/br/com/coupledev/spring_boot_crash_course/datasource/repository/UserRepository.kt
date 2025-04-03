package br.com.coupledev.spring_boot_crash_course.datasource.repository

import br.com.coupledev.spring_boot_crash_course.datasource.models.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, ObjectId> {
    fun findByEmail(email: String): User?
}