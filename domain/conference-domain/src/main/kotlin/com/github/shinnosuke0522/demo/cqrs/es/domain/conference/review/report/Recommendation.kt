package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.report

enum class Recommendation(val score: Int) {
    STRONG_ACCEPT(3),
    ACCEPT(2),
    WEAK_ACCEPT(1),
    BORDERLINE(0),
    WEAK_REJECT(-1),
    REJECT(-2),
    STRONG_REJECT(-3),
}
