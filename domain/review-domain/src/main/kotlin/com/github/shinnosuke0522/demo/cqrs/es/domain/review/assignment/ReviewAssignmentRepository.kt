package com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.Repository
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.committee.ProgramCommitteeId
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.proposal.ProposalId

interface ReviewAssignmentRepository : Repository<ReviewAssignment, ReviewAssignmentId> {
    suspend fun findByProposalId(proposalId: ProposalId): List<ReviewAssignment>
    suspend fun findByReviewerPcId(reviewerPcId: ProgramCommitteeId): List<ReviewAssignment>
}
