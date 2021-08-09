package com.krotos.guestapp.auth

import org.springframework.data.jpa.repository.JpaRepository

interface AuthGroupRepository : JpaRepository<AuthGroup, Long> {
    fun findByUsername(username: String): List<AuthGroup>?
}