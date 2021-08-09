package com.krotos.guestapp.auth

import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

private val log = LoggerFactory.getLogger(LandonUserDetailsService::class.java)

@Service
class LandonUserDetailsService(
    private val userRepository: UserRepository,
    private val authGroupRepository: AuthGroupRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        log.debug("Username: $username")
        require(username != null) { "Username can't be null here" }
        val user = userRepository.findByUsername(username)
        if (user == null) {
            log.info("User with username: $username not found")
            throw UsernameNotFoundException("User with username: $username not found")
        }
        val authGroups = authGroupRepository.findByUsername(username)
        log.info("Authentication groups: $authGroups")
        return LandonUserPrinciple(user, authGroups)
    }
}