package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.proposal

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.AggregateRoot
import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event.ConferenceEventId

sealed class Proposal (
    open val id: ProposalId,
    open val conferenceEventId: ConferenceEventId,
    open val author: Author,
    open val type: ProposalType,
    open val contents: ProposalContent,
    open val keywords: Collection<ProposalTopic>,
    open val isDoubleBlinded: Boolean,
): AggregateRoot<ProposalId>()

