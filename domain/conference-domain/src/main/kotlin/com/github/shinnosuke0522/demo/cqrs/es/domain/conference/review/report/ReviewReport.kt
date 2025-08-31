package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.report

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.AggregateRoot
import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.assignment.ReviewAssignmentId

sealed class ReviewReport(
    open val id: ReviewReportId,
    open val assignmentId: ReviewAssignmentId,
    open val recommendation: Recommendation,
    open val commentForAuthor: String,
) : AggregateRoot<ReviewReportId>()
