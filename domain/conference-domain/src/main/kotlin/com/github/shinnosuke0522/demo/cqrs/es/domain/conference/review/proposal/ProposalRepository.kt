package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.proposal

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.Repository
import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event.ConferenceEventId
import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.shared.UserId

interface ProposalRepository : Repository<Proposal, ProposalId> {
    suspend fun findByAuthorUserId(userId: UserId): List<Proposal>
    suspend fun findByEventId(eventId: ConferenceEventId): List<Proposal>
}
