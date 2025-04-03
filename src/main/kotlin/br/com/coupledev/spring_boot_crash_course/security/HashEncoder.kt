package br.com.coupledev.spring_boot_crash_course.security

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class HashEncoder {

    private val bCrypt = BCryptPasswordEncoder()

    fun encode(raw: String): String = bCrypt.encode(raw)

    fun matches(raw: String, encoded: String): Boolean =
        bCrypt.matches(raw, encoded)
}