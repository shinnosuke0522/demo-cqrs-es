package com.github.shinnosuke0522.demo.cqrs.es.domain.user

import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.model.Repository

interface UserRepository: Repository<UserId, User>