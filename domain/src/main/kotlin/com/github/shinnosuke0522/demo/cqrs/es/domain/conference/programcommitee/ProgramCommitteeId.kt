package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.programcommitee

import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.ULID

@JvmInline
value class ProgramCommitteeId private constructor(val value: ULID): Comparable<ProgramCommitteeId> {
    override fun compareTo(other: ProgramCommitteeId): Int = this.value.compareTo(other.value)

    companion object {
        fun generate(): ProgramCommitteeId = ProgramCommitteeId(ULID.generate())

        fun of(value: String): ProgramCommitteeId = ProgramCommitteeId(ULID.of(value))
    }
}