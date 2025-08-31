package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.proposal

enum class ProposalType(val lengthMinutes: Int) {
    LT(15),
    SESSION_SHORT(30),
    SESSION_LONG(60),
    WORKSHOP_SHORT(60),
    WORKSHOP_LONG(120),
}