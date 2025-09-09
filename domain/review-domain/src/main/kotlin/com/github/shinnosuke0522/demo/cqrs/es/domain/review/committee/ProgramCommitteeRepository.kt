package com.github.shinnosuke0522.demo.cqrs.es.domain.review.committee

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.Repository
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.shared.ConferenceEventId

interface ProgramCommitteeRepository : Repository<ProgramCommittee, ProgramCommitteeId> {
    suspend fun findByEventId(eventId: ConferenceEventId): List<ProgramCommittee>
}
