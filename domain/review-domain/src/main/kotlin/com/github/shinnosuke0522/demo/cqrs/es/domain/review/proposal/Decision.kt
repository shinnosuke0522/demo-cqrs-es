package com.github.shinnosuke0522.demo.cqrs.es.domain.review.proposal

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.MarkdownText
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.shared.UserId
import java.time.Instant

data class Decision(
    val type: Type,
    val reasonText: MarkdownText,
    val decidedBy: UserId,
    val decidedAt: Instant = Instant.now(),
) {
    enum class Type {
        APPROVE,
        REJECT,
        ON_WAITING_LIST
    }
}
