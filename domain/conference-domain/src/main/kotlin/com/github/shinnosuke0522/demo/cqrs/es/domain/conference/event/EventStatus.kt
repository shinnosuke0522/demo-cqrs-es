package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event

enum class EventStatus {
    // 下書き（まだ公開されていない）
    DRAFT,

    // 登壇公募（Call for Proposals）受付中
    CFP_OPENING,

    // 査読期間中
    REVIEWING,

    // 採択期間中
    DECISIONING,

    // セッションスケジューリング中
    SCHEDULING,

    // スケジュール確定
    SCHEDULED,

    // 開催中
    OPENING,

    // 終了
    CLOSED,

    // 中止
    CANCELED,
}
