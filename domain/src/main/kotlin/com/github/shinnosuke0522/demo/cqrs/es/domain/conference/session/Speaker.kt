package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.session

import com.github.shinnosuke0522.demo.cqrs.es.domain.user.UserId

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
