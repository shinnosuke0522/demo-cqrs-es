package com.github.shinnosuke0522.demo.cqrs.es.domain.user

data class Identity(
    val id: IdentityId,
    val userId: UserId,
    val email: Email,
    val emailVerified: Boolean,
    val idpIssuer: String,
    val idpSubject: String,
)
