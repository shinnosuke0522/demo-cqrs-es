package com.github.shinnosuke0522.demo.cqrs.es.domain.review.committee

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.AggregateRoot
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.shared.ConferenceEventId
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.shared.UserId

data class ProgramCommittee(
    val id: ProgramCommitteeId,
    val eventId: ConferenceEventId,
    val userId: UserId,
    val isConflictSelf: Boolean = false,
) : AggregateRoot<ProgramCommitteeId>()
