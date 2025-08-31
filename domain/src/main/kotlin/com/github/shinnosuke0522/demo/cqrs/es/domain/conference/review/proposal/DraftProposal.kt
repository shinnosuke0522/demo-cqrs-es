package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.proposal

import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event.ConferenceEventId

@ConsistentCopyVisibility
data class DraftProposal private constructor(
    override val id: ProposalId,
    override val conferenceEventId: ConferenceEventId,
    override val type: Type,
    override val isDoubleBlinded: Boolean,
    override val contents: ProposalContent,
    override val keywords: Collection<ProposalTopic>,
    override val author: Author,
): Proposal {
    companion object {
        fun of(
            conferenceEventId: ConferenceEventId,
            type: Type,
            isDoubleBlinded: Boolean,
            contents: ProposalContent,
            keywords: Collection<ProposalTopic>,
            author: Author
        ) = DraftProposal(
            id = ProposalId.generate(),
            conferenceEventId = conferenceEventId,
            type = type,
            isDoubleBlinded = isDoubleBlinded,
            contents = contents,
            keywords = keywords,
            author = author
        )
    }

    fun submit(): SubmittedProposal = SubmittedProposal.of(this)
    fun updateType(newType: Type) = this.copy(type = newType)
    fun updateIsDoubleBlinded(newIsDoubleBlinded: Boolean) = this.copy(isDoubleBlinded = newIsDoubleBlinded)
    fun updateContents(newContents: ProposalContent) = this.copy(contents = newContents)
    fun updateKeywords(newKeywords: Collection<ProposalTopic>) = this.copy(keywords = newKeywords)
}