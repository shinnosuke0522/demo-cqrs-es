package com.github.shinnosuke0522.demo.cqrs.es.domain.organization.event

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.Repository

interface ConferenceRepository : Repository<ConferenceEvent, ConferenceEventId>
