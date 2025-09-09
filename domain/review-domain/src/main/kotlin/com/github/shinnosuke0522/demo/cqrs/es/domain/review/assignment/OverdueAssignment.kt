package com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment

import arrow.core.Either
import arrow.core.Option
import arrow.core.none
import arrow.core.some
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.event.AssignmentOverdueEvent
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.committee.ProgramCommitteeId
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.proposal.ProposalId
import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.failure
import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.onSuccess
import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.success
import java.time.Instant

data class OverdueAssignment(
    override val id: ReviewAssignmentId,
    override val proposalId: ProposalId,
    override val reviewerPcId: ProgramCommitteeId,
    override val dueAt: Instant,
    override val assignedAt: Instant,
    val approvedAt: Option<Instant>,
) : ReviewAssignment(id, proposalId, reviewerPcId, dueAt, assignedAt) {
    companion object {

        fun of(assignment: ReviewAssignment): Either<ReviewAssignmentError, OverdueAssignment> =
            generate(assignment)
                .onSuccess { assignment ->
                    assignment.addDomainEvent(
                        AssignmentOverdueEvent.of(
                            aggregateId = assignment.id,
                        )
                    )
                }

        private fun generate(assignment: ReviewAssignment): Either<ReviewAssignmentError, OverdueAssignment> {
            return when (assignment) {
                is AwaitingAssignment -> success(
                    OverdueAssignment(
                        id = assignment.id,
                        proposalId = assignment.proposalId,
                        reviewerPcId = assignment.reviewerPcId,
                        dueAt = assignment.dueAt,
                        assignedAt = assignment.assignedAt,
                        approvedAt = none()
                    )
                )

                is ApprovedAssignment -> success(
                    OverdueAssignment(
                        id = assignment.id,
                        proposalId = assignment.proposalId,
                        reviewerPcId = assignment.reviewerPcId,
                        dueAt = assignment.dueAt,
                        assignedAt = assignment.assignedAt,
                        approvedAt = assignment.approvedAt.some()
                    )
                )
                is CompletedAssignment -> failure(
                    ReviewAssignmentAlreadyFixedError(
                        "Cannot be overdue because the review assignment is already completed. (id: ${assignment.id})"
                    )
                )

                is RejectedAssignment -> failure(
                    ReviewAssignmentAlreadyFixedError(
                        "Cannot be overdue because the review assignment " +
                            "is already completed or rejected. (id: ${assignment.id})"
                    )
                )

                is OverdueAssignment -> failure(
                    ReviewAssignmentAlreadyFixedError(
                        "Cannot be overdue because the review assignment is already completed. (id: ${assignment.id})"
                    )
                )

                is UnAssignedReviewAssignment -> failure(
                    ReviewAssignmentAlreadyFixedError(
                        "Cannot be overdue because the review assignment is not active. (id: ${assignment.id})"
                    )
                )
            }
        }
    }
}
