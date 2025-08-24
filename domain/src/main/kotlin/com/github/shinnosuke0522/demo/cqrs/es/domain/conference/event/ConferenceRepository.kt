package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event

import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.model.Repository

interface ConferenceRepository: Repository<ConferenceEventId, ConferenceEvent>