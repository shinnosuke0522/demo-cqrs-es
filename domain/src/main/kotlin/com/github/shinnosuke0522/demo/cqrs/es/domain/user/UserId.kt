package com.github.shinnosuke0522.demo.cqrs.es.domain.user

@JvmInline
value class UserId(val value: String) {
    companion object {
        fun of(value: String): UserId = UserId(value)
    }
}