package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.session

import arrow.core.Either

@JvmInline
value class SessionTitle private constructor(val value: String) {
     companion object {
         const val MAX_LENGTH = 100
         fun of(value: String): Either<SessionError, SessionTitle> =
             when {
                 value.isEmpty() -> Either.Left(
                     InvalidSessionTitleError("SessionTitle cannot be empty")
                 )
                 value.length > 100 -> Either.Left(
                     InvalidSessionTitleError("SessionTitle cannot be longer than $MAX_LENGTH characters")
                 )
                 else -> Either.Right(SessionTitle(value))
             }
     }
}