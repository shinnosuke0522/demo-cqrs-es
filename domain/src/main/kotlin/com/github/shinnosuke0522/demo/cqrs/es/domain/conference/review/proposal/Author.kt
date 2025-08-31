package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.proposal

import com.github.shinnosuke0522.demo.cqrs.es.domain.user.UserId

data class Author (
    val proposalId: ProposalId,
    val userId: UserId
)