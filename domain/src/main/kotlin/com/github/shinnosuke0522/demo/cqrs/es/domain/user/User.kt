package com.github.shinnosuke0522.demo.cqrs.es.domain.user

import java.time.LocalDate

data class User(
    val id: String,
    val displayName: String,
    val birthDate: LocalDate,
    val identities: List<Identity>,
)
