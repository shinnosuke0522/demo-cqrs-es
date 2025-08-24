package com.github.shinnosuke0522.demo.cqrs.es.domain.proposal

import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.model.MarkdownText
import com.github.shinnosuke0522.demo.cqrs.es.domain.user.UserId
import java.time.Instant

data class Decision(
    val id: DecisionId,
    val proposalId: ProposalId,
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
