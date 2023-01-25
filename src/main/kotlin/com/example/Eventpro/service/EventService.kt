package com.example.Eventpro.service

import com.example.Eventpro.model.Event
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
class EventService {
    @Autowired
    lateinit var eventRepository: EventRepository
    fun save (event:Event):Event{
        return eventRepository.save(event)
    }
    fun list (pageable: Pageable, event: Event):Page<Event>{
        val matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withMatcher(("description"), ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        return eventRepository.findAll(Example.of(event, matcher), pageable)
    }

    fun listById (id: Long?): Event?{
        return  eventRepository.findById(id)
    }

    fun update(event:Event):Event{
        try {
            eventRepository.findById(event.id)
                ?: throw Exception("ID no existe")
            return  eventRepository.save(event)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }

    }

    fun updateDescription(event:Event): Event {
        try{
            val response = eventRepository.findById(event.id)
                ?: throw Exception("ID no existe")
            response.apply {
                description=event.description
            }
            return eventRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }


    fun delete (id: Long?):Boolean?{
        eventRepository.findById(id) ?:
        throw  Exception()
        eventRepository.deleteById(id!!)
        return true
    }
}