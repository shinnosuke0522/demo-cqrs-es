package com.github.shinnosuke0522.demo.cqrs.es.domain.user

import arrow.core.Either
import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.failure
import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.success

@JvmInline
value class Email private constructor(val value: String) {
    companion object {
        fun of(value: String): Either<UserError, Email> {
            return if (value.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))) {
                success(Email(value))
            } else if (value.isEmpty()) {
                failure(InvalidUserEmailError("Email address cannot be empty"))
            } else {
                failure(InvalidUserEmailError("Invalid email format: $value"))
            }
        }
    }
}
