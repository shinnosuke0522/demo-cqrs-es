package com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util

import arrow.core.Either
import arrow.core.right
import arrow.core.left

fun <E, T> success(value: T): Either<E, T> = value.right()
fun <E, T> failure(error: E): Either<E, T> = error.left()

inline fun <ERROR, T> Either<ERROR, T>.onSuccess(block: (T) -> Unit): Either<ERROR, T> =
    when (this) {
        is Either.Right -> also { block(value) }   // Right は T
        is Either.Left  -> this
    }

inline fun <ERROR, T> Either<ERROR, T>.onFailure(block: (ERROR) -> Unit): Either<ERROR, T> =
    when (this) {
        is Either.Left  -> also { block(value) }
        is Either.Right -> this // Left は ERROR
    }