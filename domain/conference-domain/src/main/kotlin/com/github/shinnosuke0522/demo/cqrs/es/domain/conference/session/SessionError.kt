package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.session

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.error.DomainError

sealed interface SessionError : DomainError

data class InvalidSessionTitleError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None,
) : SessionError

data class InvalidSessionScheduleError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None,
) : SessionError

data class InvalidSessionSpeakerError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None,
) : SessionError

data class SessionSpeakerNotFoundError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None,
) : SessionError

data class SessionConferenceEventNotFoundError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None,
) : SessionError
