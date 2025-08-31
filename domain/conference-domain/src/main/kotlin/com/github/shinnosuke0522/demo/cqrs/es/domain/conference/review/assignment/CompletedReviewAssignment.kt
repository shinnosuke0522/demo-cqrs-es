package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.assignment

import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.committee.ProgramCommitteeId
import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.proposal.ProposalId
import java.time.Instant

data class CompletedReviewAssignment(
    override val id: ReviewAssignmentId,
    override val proposalId: ProposalId,
    override val reviewerPcId: ProgramCommitteeId,
    override val dueAt: Instant,
    override val assignedAt: Instant,
    val approvedAt: Instant,
    val completedAt: Instant,
) : ReviewAssignment(id, proposalId, reviewerPcId, dueAt, assignedAt) {
    companion object {
        fun of(
            approved: ApprovedReviewAssignment,
            completedAt: Instant = Instant.now(),
        ): CompletedReviewAssignment = CompletedReviewAssignment(
            id = approved.id,
            proposalId = approved.proposalId,
            reviewerPcId = approved.reviewerPcId,
            dueAt = approved.dueAt,
            assignedAt = approved.assignedAt,
            approvedAt = approved.approvedAt,
            completedAt = completedAt,
        )
    }
}
