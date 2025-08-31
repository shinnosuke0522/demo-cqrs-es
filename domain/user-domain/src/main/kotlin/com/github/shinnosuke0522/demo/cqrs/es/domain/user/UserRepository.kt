package com.github.shinnosuke0522.demo.cqrs.es.domain.user

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.Repository

interface UserRepository: Repository<User, UserId>