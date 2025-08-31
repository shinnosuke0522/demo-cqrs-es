package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.proposal

import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event.ConferenceEventId

@ConsistentCopyVisibility
data class DecidedProposal private constructor(
    override val id: ProposalId,
    override val conferenceEventId: ConferenceEventId,
    override val author: Author,
    override val type: ProposalType,
    override val contents: ProposalContent,
    override val keywords: Collection<ProposalTopic>,
    override val isDoubleBlinded: Boolean,
    val decision: Decision
): Proposal(id, conferenceEventId, author, type, contents, keywords, isDoubleBlinded) {
    companion object {
        fun of(
            reviewed: ReviewedProposal,
            decision: Decision
        ) = DecidedProposal(
            id = reviewed.id,
            conferenceEventId = reviewed.conferenceEventId,
            type = reviewed.type,
            isDoubleBlinded = reviewed.isDoubleBlinded,
            contents = reviewed.contents,
            keywords = reviewed.keywords,
            author = reviewed.author,
            decision = decision
        )
    }
}