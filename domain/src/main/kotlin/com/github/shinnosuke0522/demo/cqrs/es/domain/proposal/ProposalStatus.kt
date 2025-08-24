package com.github.shinnosuke0522.demo.cqrs.es.domain.proposal

// TODO: should be subclass of Proposal
enum class ProposalStatus {
    DRAFT,
    SUBMITTED,
    REVIEWING,
    DISCUSSING,
    DECIDED,
}