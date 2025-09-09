package com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment

import arrow.core.Either
import arrow.core.Option
import arrow.core.none
import arrow.core.some
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.event.AssignmentUnassignedEvent
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.committee.ProgramCommitteeId
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.proposal.ProposalId
import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.failure
import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.onSuccess
import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.success
import java.time.Instant

data class UnAssignedReviewAssignment(
    override val id: ReviewAssignmentId,
    override val proposalId: ProposalId,
    override val reviewerPcId: ProgramCommitteeId,
    override val dueAt: Instant,
    override val assignedAt: Instant,
    val approvedAt: Option<Instant>,
    val unAssignedAt: Instant,
) : ReviewAssignment(id, proposalId, reviewerPcId, dueAt, assignedAt) {

    companion object {

        fun of(
            assignment: ReviewAssignment,
            unAssignedAt: Instant = Instant.now(),
        ): Either<ReviewAssignmentError, UnAssignedReviewAssignment> =
            generate(assignment, unAssignedAt).onSuccess {
                it.addDomainEvent(
                    AssignmentUnassignedEvent.of(
                        aggregateId = it.id,
                        unassignedAt = it.unAssignedAt,
                    )
                )
            }

        private fun generate(
            assignment: ReviewAssignment,
            unAssignedAt: Instant = Instant.now(),
        ): Either<ReviewAssignmentError, UnAssignedReviewAssignment> = when (assignment) {
            is AwaitingAssignment, is OverdueAssignment -> success(
                UnAssignedReviewAssignment(
                    id = assignment.id,
                    proposalId = assignment.proposalId,
                    reviewerPcId = assignment.reviewerPcId,
                    dueAt = assignment.dueAt,
                    assignedAt = assignment.assignedAt,
                    approvedAt = none(),
                    unAssignedAt = unAssignedAt,
                )
            )

            is ApprovedAssignment -> success(
                UnAssignedReviewAssignment(
                    id = assignment.id,
                    proposalId = assignment.proposalId,
                    reviewerPcId = assignment.reviewerPcId,
                    dueAt = assignment.dueAt,
                    assignedAt = assignment.assignedAt,
                    approvedAt = assignment.approvedAt.some(),
                    unAssignedAt = unAssignedAt,
                )
            )

            is CompletedAssignment, is RejectedAssignment -> failure(
                ReviewAssignmentAlreadyFixedError(
                    "Cannot unassign because the review assignment is already completed. (id: ${assignment.id})"
                )
            )

            is UnAssignedReviewAssignment -> failure(
                ReviewAssignmentAlreadyFixedError(
                    "Cannot unassign because the review assignment is already unassigned. (id: ${assignment.id})"
                )
            )
        }
    }
}
