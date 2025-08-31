package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.report

import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.assignment.ReviewAssignmentId
import java.time.Instant

data class SubmittedReviewReport(
    override val id: ReviewReportId,
    override val assignmentId: ReviewAssignmentId,
    override val recommendation: Recommendation,
    override val commentForAuthor: String,
    val submittedAt: Instant,
) : ReviewReport(id, assignmentId, recommendation, commentForAuthor) {
    companion object {
        fun of(
            assignmentId: ReviewAssignmentId,
            recommendation: Recommendation,
            commentForAuthor: String,
            submittedAt: Instant = Instant.now(),
        ): SubmittedReviewReport = SubmittedReviewReport(
            id = ReviewReportId.Companion.generate(),
            assignmentId = assignmentId,
            recommendation = recommendation,
            commentForAuthor = commentForAuthor,
            submittedAt = submittedAt,
        )

        fun of(
            draft: DraftReviewReport,
            submittedAt: Instant = Instant.now(),
        ): SubmittedReviewReport = SubmittedReviewReport(
            id = draft.id,
            assignmentId = draft.assignmentId,
            recommendation = draft.recommendation,
            commentForAuthor = draft.commentForAuthor,
            submittedAt = submittedAt,
        )
    }
}