package com.example.Eventpro.service

import com.example.Eventpro.model.Conference
import com.example.Eventpro.repository.ConferenceRepository
import com.example.Eventpro.repository.EventRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class ConferenceService {
    @Autowired
    lateinit var conferenceRepository: ConferenceRepository
    @Autowired
    lateinit var eventRepository: EventRepository

    fun save (conference:Conference):Conference{
        try{
            eventRepository.findById(conference.eventId)
                ?: throw Exception("Event no existe")
            return conferenceRepository.save(conference)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }

    }



    fun list (pageable: Pageable, conference: Conference):Page<Conference>{
        val matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withMatcher(("title"), ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        return conferenceRepository.findAll(Example.of(conference, matcher), pageable)
    }

    fun listById (id: Long?): Conference?{
        return  conferenceRepository.findById(id)
    }

    fun update(conference:Conference):Conference{
        try {
            conferenceRepository.findById(conference.id)
                ?: throw Exception("ID no existe")
            return  conferenceRepository.save(conference)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }

    }

    fun updateTitle(conference:Conference): Conference {
        try{
            val response = conferenceRepository.findById(conference.id)
                ?: throw Exception("ID no existe")
            response.apply {
                title=conference.title
            }
            return conferenceRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun delete (id: Long?):Boolean?{
        conferenceRepository.findById(id) ?:
        throw  Exception()
        conferenceRepository.deleteById(id!!)
        return true
    }
}