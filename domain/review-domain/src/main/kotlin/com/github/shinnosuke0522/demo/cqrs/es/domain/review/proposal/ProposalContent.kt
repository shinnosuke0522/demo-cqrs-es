package com.github.shinnosuke0522.demo.cqrs.es.domain.review.proposal

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.MarkdownText

data class ProposalContent(
    val id: ProposalContentId,
    val proposalId: ProposalId,
    val title: ProposalTitle,
    val abstractMarkdown: MarkdownText,
    val outlineMarkdown: MarkdownText,
    val targetAudienceMarkdown: MarkdownText,
)
