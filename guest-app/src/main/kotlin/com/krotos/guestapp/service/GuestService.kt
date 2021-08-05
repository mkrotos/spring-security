package com.krotos.guestapp.service

import com.krotos.guestapp.domain.Guest
import com.krotos.guestapp.domain.GuestModel
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

private const val GUESTS = "/guests"
private const val SLASH = "/"

@Service
class GuestService(
    @Value("\${landon.guest.service.url}")
    private val guestServiceUrl: String,
    private val restTemplate: RestTemplate = RestTemplate()
) {

    fun getAllGuests(): List<Guest>? {
        val url = guestServiceUrl + GUESTS
        val request: HttpEntity<String> = HttpEntity<String>(null, null)
        return restTemplate.exchange<List<Guest>>(url, HttpMethod.GET, request,
            object : ParameterizedTypeReference<List<Guest>?>() {}).body
    }

    fun addGuest(guestModel: GuestModel?): Guest? {
        val url = guestServiceUrl + GUESTS
        val request: HttpEntity<GuestModel> = HttpEntity<GuestModel>(guestModel, null)
        return restTemplate.exchange(url, HttpMethod.POST, request, Guest::class.java).body
    }

    fun getGuest(id: Long): Guest? {
        val url = guestServiceUrl + GUESTS + SLASH + id
        val request: HttpEntity<String> = HttpEntity<String>(null, null)
        return restTemplate.exchange(url, HttpMethod.GET, request, Guest::class.java).body
    }

    fun updateGuest(id: Long, guestModel: GuestModel?): Guest? {
        println(guestModel)
        val url = guestServiceUrl + GUESTS + SLASH + id
        val request: HttpEntity<GuestModel> = HttpEntity<GuestModel>(guestModel, null)
        return restTemplate.exchange(url, HttpMethod.PUT, request, Guest::class.java).body
    }
}