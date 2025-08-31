package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.assignment

import arrow.core.Either
import arrow.core.Option
import arrow.core.none
import arrow.core.some
import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.committee.ProgramCommitteeId
import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.proposal.ProposalId
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.failure
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.success
import java.time.Instant

data class OverdueReviewAssignment(
    override val id: ReviewAssignmentId,
    override val proposalId: ProposalId,
    override val reviewerPcId: ProgramCommitteeId,
    override val dueAt: Instant,
    override val assignedAt: Instant,
    val approvedAt: Option<Instant>,
) : ReviewAssignment(id, proposalId, reviewerPcId, dueAt, assignedAt) {
    companion object {
        fun of(assignment: ReviewAssignment) : Either<ReviewAssignmentError, OverdueReviewAssignment> {
            return when (assignment) {
                is AwaitingReviewAssignment -> success(
                    OverdueReviewAssignment(
                        id = assignment.id,
                        proposalId = assignment.proposalId,
                        reviewerPcId = assignment.reviewerPcId,
                        dueAt = assignment.dueAt,
                        assignedAt = assignment.assignedAt,
                        approvedAt = none()
                    )
                )
                is ApprovedReviewAssignment -> Either.Right(
                    OverdueReviewAssignment(
                        id = assignment.id,
                        proposalId = assignment.proposalId,
                        reviewerPcId = assignment.reviewerPcId,
                        dueAt = assignment.dueAt,
                        assignedAt = assignment.assignedAt,
                        approvedAt = assignment.approvedAt.some()
                    )
                )
                is CompletedReviewAssignment,
                is RejectedReviewAssignment,
                is OverdueReviewAssignment -> failure(ReviewAssignmentAlreadyFixedError(
                    "Cannot be overdue because the review assignment is already completed. (id: ${assignment.id})"
                ))
            }
        }
    }
}