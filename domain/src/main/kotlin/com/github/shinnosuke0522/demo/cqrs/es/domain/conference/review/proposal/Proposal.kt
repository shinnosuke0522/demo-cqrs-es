package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.proposal

import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event.ConferenceEventId

sealed interface Proposal {
    val id: ProposalId
    val conferenceEventId: ConferenceEventId
    val author: Author
    val type: Type
    val contents: ProposalContent
    val keywords: Collection<ProposalTopic>
    val isDoubleBlinded: Boolean
}

enum class Type(val lengthMinutes: Int) {
    LT(15),
    SESSION_SHORT(30),
    SESSION_LONG(60),
    WORKSHOP_SHORT(60),
    WORKSHOP_LONG(120),
}

