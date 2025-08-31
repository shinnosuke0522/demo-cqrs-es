package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.session

import arrow.core.Either
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.failure

@JvmInline
value class Speakers private constructor(val values: List<Speaker>) {
    companion object {
        const val MAX_SPEAKER_COUNT = 3
        const val MAIN_SPEAKER_SIZE = 1

        fun of(speakers: List<Speaker>): Either<SessionError, Speakers> =
            when {
                speakers.isEmpty() -> failure(
                    InvalidSessionSpeakerError("Speakers cannot be empty")
                )
                speakers.size > MAX_SPEAKER_COUNT -> failure(
                    InvalidSessionSpeakerError("Speakers cannot be more than $MAX_SPEAKER_COUNT")
                )
                speakers.count { it.isMainSpeaker() } != MAIN_SPEAKER_SIZE -> failure(
                    InvalidSessionSpeakerError("There can be only one main speaker")
                )
                else -> Either.Right(Speakers(speakers))
            }
    }
}
