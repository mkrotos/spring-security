package com.krotos.guestapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GuestAppApplication

fun main(args: Array<String>) {
	runApplication<GuestAppApplication>(*args)
}
