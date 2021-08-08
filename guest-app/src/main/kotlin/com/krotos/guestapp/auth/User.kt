package com.krotos.guestapp.auth

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "USERS")
class User {

    @Id
    @Column(name = "USER_ID")
    var id: Long? = null

    @Column(name = "USERNAME", nullable = false, unique = true)
    lateinit var username: String

    @Column(name = "PASSWORD", nullable = false)
    lateinit var password: String

    override fun toString(): String {
        return "User(id=$id, username='$username', password='$password')"
    }

}