package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.Repository

interface ConferenceRepository : Repository<ConferenceEvent, ConferenceEventId>
