package com.github.shinnosuke0522.demo.cqrs.es.domain.review.review

import com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.ReviewAssignmentId

@ConsistentCopyVisibility
data class DraftReviewReport private constructor(
    override val id: ReviewReportId,
    override val assignmentId: ReviewAssignmentId,
    override val recommendation: Recommendation,
    override val commentForAuthor: String,
) : ReviewReport(id, assignmentId, recommendation, commentForAuthor) {

    fun submit(): SubmittedReviewReport = SubmittedReviewReport.of(this)

    fun withRecommendation(newRecommendation: Recommendation): DraftReviewReport =
        this.copy(recommendation = newRecommendation)

    fun withCommentForAuthor(newCommentForAuthor: String): DraftReviewReport =
        this.copy(commentForAuthor = newCommentForAuthor)

    companion object {
        fun of(
            assignmentId: ReviewAssignmentId,
            recommendation: Recommendation,
            commentForAuthor: String
        ): DraftReviewReport = DraftReviewReport(
            id = ReviewReportId.Companion.generate(),
            assignmentId = assignmentId,
            recommendation = recommendation,
            commentForAuthor = commentForAuthor
        )
    }
}
