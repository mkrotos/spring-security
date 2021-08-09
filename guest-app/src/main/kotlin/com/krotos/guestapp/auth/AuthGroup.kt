package com.krotos.guestapp.auth

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "AUTH_USER_GROUP")
class AuthGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AUTH_USER_GROUP_ID")
    var id: Long? = null

    @Column(name = "USERNAME")
    lateinit var username: String

    @Column(name = "AUTH_GROUP")
    lateinit var authGroup: String
}