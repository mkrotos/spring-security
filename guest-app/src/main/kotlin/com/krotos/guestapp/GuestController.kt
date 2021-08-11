package com.krotos.guestapp

import com.krotos.guestapp.domain.Guest
import com.krotos.guestapp.domain.GuestModel
import com.krotos.guestapp.service.GuestService
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.View
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/")
class GuestController(private val guestService: GuestService) {

    @GetMapping(value = ["/", "/index"])
    fun getHomePage(model: Model?): String {
        return "index"
    }

    @GetMapping(value = ["/login"])
    fun getLoginPage(model: Model): String {
        return "login"
    }

    @GetMapping(value = ["/logout-success"])
    fun getLogoutPage(model: Model):String {
        return "logout"
    }

    @GetMapping(value = ["/guests"])
    @PreAuthorize("hasRole('ROLE_USER')")
    fun getGuests(model: Model): String {
        val guests: List<Guest?>? = guestService.getAllGuests()
        model.addAttribute("guests", guests)
        return "guests-view"
    }

    @GetMapping(value = ["/guests/add"])
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun getAddGuestForm(model: Model?): String {
        return "guest-view"
    }

    @PostMapping(value = ["/guests"])
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun addGuest(request: HttpServletRequest, model: Model, @ModelAttribute guestModel: GuestModel?): ModelAndView {
        val guest: Guest? = guestService.addGuest(guestModel)
        model.addAttribute("guest", guest)
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT)
        return ModelAndView("redirect:/guests/" + guest?.id)
    }

    @GetMapping(value = ["/guests/{id}"])
    @PreAuthorize("hasRole('ROLE_USER')")
    fun getGuest(model: Model, @PathVariable id: Long): String {
        val guest: Guest? = guestService.getGuest(id)
        model.addAttribute("guest", guest)
        return "guest-view"
    }

    @PostMapping(value = ["/guests/{id}"])
    fun updateGuest(model: Model, @PathVariable id: Long, @ModelAttribute guestModel: GuestModel?): String {
        val guest: Guest? = guestService.updateGuest(id, guestModel)
        model.addAttribute("guest", guest)
        model.addAttribute("guestModel", GuestModel())
        return "guest-view"
    }
}