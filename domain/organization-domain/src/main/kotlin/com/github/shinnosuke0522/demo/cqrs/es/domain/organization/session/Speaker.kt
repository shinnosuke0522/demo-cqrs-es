package com.github.shinnosuke0522.demo.cqrs.es.domain.organization.session

import com.github.shinnosuke0522.demo.cqrs.es.domain.organization.membership.UserId

data class Speaker(
    val speakerUserId: UserId,
    val role: Role,
) {
    enum class Role {
        MAIN_SPEAKER,
        CO_SPEAKER,
    }

    fun isMainSpeaker(): Boolean = role == Role.MAIN_SPEAKER
}
