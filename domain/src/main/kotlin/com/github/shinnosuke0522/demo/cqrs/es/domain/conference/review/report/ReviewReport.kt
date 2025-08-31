package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.report

import com.github.shinnosuke0522.demo.cqrs.es.domain.review.report.ReviewReportId
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.ReviewAssignmentId

sealed interface ReviewReport {
    val id: ReviewReportId
    val assignmentId: ReviewAssignmentId
    val recommendation: Recommendation
}