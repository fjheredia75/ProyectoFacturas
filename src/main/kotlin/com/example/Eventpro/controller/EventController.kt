package com.example.Eventpro.controller

import com.example.Eventpro.model.Event
import com.example.Eventpro.service.EventService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
@RequestMapping("/event")
class EventController {
    @Autowired
    lateinit var eventService: EventService
    @PostMapping
    fun save (@RequestBody @Valid event:Event):Event{
        return eventService.save(event)
    }


    @GetMapping
    fun list (event: Event, pageable: Pageable):ResponseEntity<*>{
        val response = eventService.list(pageable, event)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable ("id") id: Long): ResponseEntity<Event>{
        return ResponseEntity(eventService.listById(id), HttpStatus.OK)
    }

    @PutMapping
    fun update (@RequestBody event: Event):ResponseEntity<Event>{
        return ResponseEntity(eventService.update(event), HttpStatus.OK)
    }

    @PatchMapping
    fun updateDescription (@RequestBody event:Event):ResponseEntity<Event>{
        return ResponseEntity(eventService.updateDescription(event), HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return eventService.delete(id)
    }
}