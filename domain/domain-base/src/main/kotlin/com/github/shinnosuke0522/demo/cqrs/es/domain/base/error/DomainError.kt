package com.github.shinnosuke0522.demo.cqrs.es.domain.base.error

interface DomainError {
    val message: String
    val cause: Cause

    sealed interface Cause {
        data object None : Cause
        data class Domain(val error: DomainError) : Cause
        data class External(val throwable: Throwable) : Cause
    }
}
