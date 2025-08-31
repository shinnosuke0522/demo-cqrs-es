package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.assignment

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.Repository

interface ReviewAssignmentRepository : Repository<ReviewAssignment, ReviewAssignmentId> {
    suspend fun findByProposalId(proposalId: String): List<ReviewAssignment>
    suspend fun findByReviewerPcId(reviewerPcId: String): List<ReviewAssignment>
}