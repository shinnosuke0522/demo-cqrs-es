package com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment

import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.model.Repository

interface ReviewAssignmentRepository : Repository<ReviewAssignmentId, ReviewAssignment> {
    suspend fun findByProposalId(proposalId: String): List<ReviewAssignment>
    suspend fun findByReviewerPcId(reviewerPcId: String): List<ReviewAssignment>
}