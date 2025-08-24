package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.programcommitee

import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event.ConferenceEventId
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.model.Repository

interface ProgramCommitteeRepository : Repository<ProgramCommitteeId, ProgramCommittee> {
    suspend fun findByEventId(eventId: ConferenceEventId): List<ProgramCommittee>
}