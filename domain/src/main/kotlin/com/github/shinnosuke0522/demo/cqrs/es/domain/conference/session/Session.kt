package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.session

import com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event.ConferenceEventId
import com.github.shinnosuke0522.demo.cqrs.es.domain.proposal.ProposalId
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.model.MarkdownText

data class Session(
    val id: SessionId,
    val conferenceId: ConferenceEventId,
    val title: SessionTitle,
    val abstractMarkdown: MarkdownText,
    val outlineMarkdown: MarkdownText,
    val speakers: Speakers,
    val proposal: ProposalId,
)