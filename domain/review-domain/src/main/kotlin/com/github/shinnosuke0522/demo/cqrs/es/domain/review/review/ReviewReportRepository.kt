package com.github.shinnosuke0522.demo.cqrs.es.domain.review.review

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.Repository

interface ReviewReportRepository : Repository<ReviewReport, ReviewReportId>
