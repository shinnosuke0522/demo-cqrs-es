package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event

import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.error.DomainError

sealed interface ConferenceEventError: DomainError

data class InvalidConferenceEventHostingTypeError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None
): ConferenceEventError

data class InvalidConferenceEventTitleError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None,
): ConferenceEventError

data class InvalidConferenceEventVenueError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None,
): ConferenceEventError

data class InvalidConferenceEventMeetingUrlError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None
): ConferenceEventError

data class InvalidConferenceEvetStaffsError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None,
): ConferenceEventError

data class InvalidConferenceEventScheduleError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None
): ConferenceEventError