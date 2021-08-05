package com.krotos.guestservices

import javax.persistence.*

@Entity
@Table(name = "GUEST")
class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GUEST_ID")
    var id: Long = 0

    @Column(name = "FIRST_NAME")
    var firstName: String? = null

    @Column(name = "LAST_NAME")
    var lastName: String? = null

    @Column(name = "EMAIL_ADDRESS")
    var emailAddress: String? = null

    @Column(name = "ADDRESS")
    var address: String? = null

    @Column(name = "COUNTRY")
    var country: String? = null

    @Column(name = "STATE")
    var state: String? = null

    @Column(name = "PHONE_NUMBER")
    var phoneNumber: String? = null
}