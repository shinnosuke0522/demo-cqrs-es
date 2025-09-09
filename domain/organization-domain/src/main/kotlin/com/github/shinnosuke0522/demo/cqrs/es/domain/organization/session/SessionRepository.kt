package com.github.shinnosuke0522.demo.cqrs.es.domain.organization.session

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.Repository

interface SessionRepository : Repository<Session, SessionId>
