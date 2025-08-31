package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.session

import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.model.Repository

interface SessionRepository: Repository<SessionId, Session>