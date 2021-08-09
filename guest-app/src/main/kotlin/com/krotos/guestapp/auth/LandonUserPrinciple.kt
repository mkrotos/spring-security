package com.krotos.guestapp.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class LandonUserPrinciple(private val user: User, private val authGroups: List<AuthGroup>?) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authGroups?.map { SimpleGrantedAuthority(it.authGroup) }?.toMutableSet() ?: mutableSetOf()
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}