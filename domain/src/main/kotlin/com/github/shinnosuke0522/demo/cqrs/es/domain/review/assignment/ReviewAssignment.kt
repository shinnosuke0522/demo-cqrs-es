package com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment

import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.committee.ProgramCommitteeId
import com.github.shinnosuke0522.demo.cqrs.es.domain.proposal.ProposalId
import java.time.Instant

sealed interface ReviewAssignment {
    val id: ReviewAssignmentId
    val proposalId: ProposalId
    val reviewerPcId: ProgramCommitteeId
    val dueAt: Instant
    val assignedAt: Instant
}

