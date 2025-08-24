package com.github.shinnosuke0522.demo.cqrs.es.domain.proposal

import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event.ConferenceEventId

data class Proposal(
    val id: ProposalId,
    val conferenceEventId: ConferenceEventId,
    val type: Type,
    val isDoubleBlinded: Boolean,
    val contents: ProposalContent,
    val keywords: Collection<ProposalTopic>,
    val author: Author,
) {
    enum class Type(val lengthMinutes: Int) {
        LT(15),
        SESSION_SHORT(30),
        SESSION_LONG(60),
        WORKSHOP_SHORT(60),
        WORKSHOP_LONG(120),
    }
}
