package com.krotos.guestservices

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

private val log = LoggerFactory.getLogger(GuestServicesController::class.java)

@RestController
@RequestMapping("/guests")
class GuestServicesController(private val repository: GuestRepository) {

    @GetMapping
    fun allGuests(): List<Guest?> {
        return repository.findAll()
    }

    @PostMapping
    fun addGuest(@RequestBody model: GuestModel): ResponseEntity<Guest> {
        val guest = repository.save(model.translateModelToGuest())
        val location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(guest.id).toUri()
        return ResponseEntity.created(location).body(guest)
    }

    @GetMapping("/{id}")
    fun getGuest(@PathVariable id: Long): Guest {
        val guest = repository.findById(id)
        return guest.orElseThrow { GuestNotFoundException("Guest not found with id: $id") }!!
    }

    @PutMapping("/{id}")
    fun updateGuest(@PathVariable id: Long, @RequestBody model: GuestModel): Guest {
        val existing = repository.findById(id)
        if (!existing.isPresent) {
            throw GuestNotFoundException("Guest not found with id: $id")
        }
        val guest = model.translateModelToGuest()
        guest.id = id
        return repository.save(guest)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    fun deleteGuest(@PathVariable id: Long) {
        val existing = repository.findById(id)
        if (!existing.isPresent) {
            throw GuestNotFoundException("Guest not found with id: $id")
        }
        repository.deleteById(id)
    }

}


