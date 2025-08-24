package com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment

import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.committee.ProgramCommitteeId
import com.github.shinnosuke0522.demo.cqrs.es.domain.proposal.ProposalId
import java.time.Instant

data class AwaitingReviewAssignment(
    override val id: ReviewAssignmentId,
    override val proposalId: ProposalId,
    override val reviewerPcId: ProgramCommitteeId,
    override val dueAt: Instant,
    override val assignedAt: Instant,
) : ReviewAssignment {
    companion object {
        fun of(
            proposalId: ProposalId,
            reviewerPcId: ProgramCommitteeId,
            dueAt: Instant,
        ): AwaitingReviewAssignment = AwaitingReviewAssignment(
            id = ReviewAssignmentId.generate(),
            proposalId = proposalId,
            reviewerPcId = reviewerPcId,
            dueAt = dueAt,
            assignedAt = Instant.now(),
        )
    }
}