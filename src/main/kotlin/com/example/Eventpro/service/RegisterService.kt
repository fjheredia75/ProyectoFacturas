package com.example.Eventpro.service

import com.example.Eventpro.model.Register
import com.example.Eventpro.repository.ConferenceRepository
import com.example.Eventpro.repository.MemberRepository
import com.example.Eventpro.repository.RegisterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class RegisterService {
    @Autowired
    lateinit var registerRepository: RegisterRepository
    @Autowired
    lateinit var memberRepository: MemberRepository
    @Autowired
    lateinit var conferenceRepository: ConferenceRepository

    fun save (register:Register):Register{
        try{
            register.code?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Code no debe ser vacio")
            register.assisted?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("registerAt no debe ser vacio")


            return registerRepository.save(register)
        } catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }


    }



    fun list (pageable: Pageable, register: Register):Page<Register>{
        val matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withMatcher(("assisted"), ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        return registerRepository.findAll(Example.of(register, matcher), pageable)
    }

    fun listarConf(memberId:Long?): List<Register>? {
        return registerRepository.listarConf(memberId)
    }

    fun listById (id: Long?): Register?{
        return  registerRepository.findById(id)
    }

    fun update(register:Register):Register{
        try {
            registerRepository.findById(register.id)
                ?: throw Exception("ID no existe")
            return  registerRepository.save(register)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }

    }

    fun updateAssisted(register:Register): Register {
        try{
            val response = registerRepository.findById(register.id)
                ?: throw Exception("ID no existe")
            response.apply {
                assisted=register.assisted
            }
            // confAsistente(register)
            return registerRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun delete (id: Long?):Boolean?{
        registerRepository.findById(id) ?:
        throw  Exception()
        registerRepository.deleteById(id!!)
        return true
    }
    /*fun confAsistente (register: Register){
           val totalCalculated = registerRepository.sumarAsistente(register.conferenceId)
           val conferenceResponse = conferenceRepository.findById(register.conferenceId)
           conferenceResponse.apply {
               totalAttendees=totalCalculated
           }
           conferenceRepository.save(conferenceResponse)
       }*/

}