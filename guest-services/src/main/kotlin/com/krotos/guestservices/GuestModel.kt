package com.krotos.guestservices


class GuestModel(
    var firstName: String?,
    var lastName: String?,
    var emailAddress: String?,
    var address: String?,
    var country: String?,
    var state: String?,
    var phoneNumber: String?,
) {

    constructor() : this(null, null, null, null, null, null, null)

    fun translateModelToGuest(): Guest {
        val guest = Guest()
        guest.firstName = firstName
        guest.lastName = lastName
        guest.emailAddress = emailAddress
        guest.address = address
        guest.country = country
        guest.state = state
        guest.phoneNumber = phoneNumber
        return guest
    }
}