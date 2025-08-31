package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.proposal

import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event.ConferenceEventId
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.model.Repository
import com.github.shinnosuke0522.demo.cqrs.es.domain.user.UserId

interface ProposalRepository : Repository<ProposalId, Proposal> {
    suspend fun findByAuthorUserId(userId: UserId): List<Proposal>
    suspend fun findByEventId(eventId: ConferenceEventId): List<Proposal>
}