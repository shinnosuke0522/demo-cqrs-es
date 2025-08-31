package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.proposal

import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event.ConferenceEventId

@ConsistentCopyVisibility
data class ReviewedProposal private constructor(
    override val id: ProposalId,
    override val conferenceEventId: ConferenceEventId,
    override val author: Author,
    override val type: Type,
    override val contents: ProposalContent,
    override val keywords: Collection<ProposalTopic>,
    override val isDoubleBlinded: Boolean = true,
): Proposal {
    companion object {
        fun of(
            submitted: SubmittedProposal
        ) = ReviewedProposal(
            id = submitted.id,
            conferenceEventId = submitted.conferenceEventId,
            type = submitted.type,
            isDoubleBlinded = submitted.isDoubleBlinded,
            contents = submitted.contents,
            keywords = submitted.keywords,
            author = submitted.author
        )
    }

    fun decide(decision: Decision): DecidedProposal = DecidedProposal.of(this, decision)
}