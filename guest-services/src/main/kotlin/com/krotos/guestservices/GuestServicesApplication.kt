package com.krotos.guestservices

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GuestServicesApplication

fun main(args: Array<String>) {
	runApplication<GuestServicesApplication>(*args)
}
