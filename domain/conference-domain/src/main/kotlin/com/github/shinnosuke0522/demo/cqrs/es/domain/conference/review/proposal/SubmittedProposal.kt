package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.proposal

import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event.ConferenceEventId

@ConsistentCopyVisibility
data class SubmittedProposal private constructor(
    override val id: ProposalId,
    override val conferenceEventId: ConferenceEventId,
    override val author: Author,
    override val type: ProposalType,
    override val contents: ProposalContent,
    override val keywords: Collection<ProposalTopic>,
    override val isDoubleBlinded: Boolean = true,
) : Proposal(id, conferenceEventId, author, type, contents, keywords, isDoubleBlinded) {
    companion object {
        fun of(
            draft: DraftProposal
        ) = SubmittedProposal(
            id = draft.id,
            conferenceEventId = draft.conferenceEventId,
            type = draft.type,
            isDoubleBlinded = draft.isDoubleBlinded,
            contents = draft.contents,
            keywords = draft.keywords,
            author = draft.author
        )
    }
}
