package com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.AggregateRoot
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.committee.ProgramCommitteeId
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.proposal.ProposalId
import java.time.Instant

sealed class ReviewAssignment(
    open val id: ReviewAssignmentId,
    open val proposalId: ProposalId,
    open val reviewerPcId: ProgramCommitteeId,
    open val dueAt: Instant,
    open val assignedAt: Instant,
) : AggregateRoot<ReviewAssignmentId>()
