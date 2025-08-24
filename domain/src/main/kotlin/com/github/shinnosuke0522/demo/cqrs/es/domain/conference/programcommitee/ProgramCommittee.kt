package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.programcommitee

import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event.ConferenceEventId
import com.github.shinnosuke0522.demo.cqrs.es.domain.user.UserId

data class ProgramCommittee(
    val id: ProgramCommitteeId,
    val eventId: ConferenceEventId,
    val userId: UserId,
    val isConflictSelf: Boolean = false,
)
