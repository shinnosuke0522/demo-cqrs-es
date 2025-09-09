package com.github.shinnosuke0522.demo.cqrs.es.domain.organization.session

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.AggregateRoot
import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.MarkdownText
import com.github.shinnosuke0522.demo.cqrs.es.domain.organization.event.ConferenceEventId

data class Session(
    val id: SessionId,
    val conferenceId: ConferenceEventId,
    val title: SessionTitle,
    val abstractMarkdown: MarkdownText,
    val outlineMarkdown: MarkdownText,
    val speakers: Speakers,
    val proposal: Proposal,
) : AggregateRoot<SessionId>()
