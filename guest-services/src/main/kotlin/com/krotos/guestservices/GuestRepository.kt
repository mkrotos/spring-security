package com.krotos.guestservices

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GuestRepository : JpaRepository<Guest, Long>