package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.report

import com.github.shinnosuke0522.demo.cqrs.es.domain.review.report.ReviewReportId
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.ReviewAssignmentId

data class DraftReviewReport(
    override val id: ReviewReportId,
    override val assignmentId: ReviewAssignmentId,
    override val recommendation: Recommendation,
) : ReviewReport {
    companion object {
        fun of(
            assignmentId: ReviewAssignmentId,
            recommendation: Recommendation,
        ): DraftReviewReport = DraftReviewReport(
            id = ReviewReportId.Companion.generate(),
            assignmentId = assignmentId,
            recommendation = recommendation,
        )
    }
}