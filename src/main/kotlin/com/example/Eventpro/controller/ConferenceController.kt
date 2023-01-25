package com.example.Eventpro.controller

import com.example.Eventpro.model.Conference
import com.example.Eventpro.service.ConferenceService
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
@RequestMapping("/conference")
class ConferenceController {
    @Autowired
    lateinit var conferenceService: ConferenceService
    @PostMapping
    fun save (@RequestBody @Valid conference:Conference):Conference{
        return conferenceService.save(conference)
    }


    @GetMapping
    fun list (conference: Conference, pageable: Pageable):ResponseEntity<*>{
        val response = conferenceService.list(pageable, conference)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable ("id") id: Long): ResponseEntity<Conference>{
        return ResponseEntity(conferenceService.listById(id), HttpStatus.OK)
    }

    @PutMapping
    fun update (@RequestBody conference: Conference):ResponseEntity<Conference>{
        return ResponseEntity(conferenceService.update(conference), HttpStatus.OK)
    }

    @PatchMapping
    fun updateTitle (@RequestBody conference:Conference):ResponseEntity<Conference>{
        return ResponseEntity(conferenceService.updateTitle(conference), HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return conferenceService.delete(id)
    }
}