package com.krotos.guestservices

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class GuestNotFoundException(s: String) : RuntimeException(s)