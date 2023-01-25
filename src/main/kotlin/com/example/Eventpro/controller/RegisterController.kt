package com.example.Eventpro.controller

import com.example.Eventpro.model.Register
import com.example.Eventpro.service.RegisterService
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
@RequestMapping("/register")
class RegisterController {
    @Autowired
    lateinit var registerService: RegisterService
    @PostMapping
    fun save (@RequestBody @Valid register:Register):Register{
        return registerService.save(register)
    }


    @GetMapping
    fun list (register: Register, pageable: Pageable):ResponseEntity<*>{
        val response = registerService.list(pageable, register)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable ("id") id: Long): ResponseEntity<Register>{
        return ResponseEntity(registerService.listById(id), HttpStatus.OK)
    }

    @GetMapping("/conferences/{memberId}")
    fun listarConf (@PathVariable("memberId") memberId: Long ):ResponseEntity<*>{
        return ResponseEntity(registerService.listarConf(memberId), HttpStatus.OK)
    }

    @PutMapping
    fun update (@RequestBody register: Register):ResponseEntity<Register>{
        return ResponseEntity(registerService.update(register), HttpStatus.OK)
    }

    @PatchMapping
    fun updateAssisted (@RequestBody register:Register):ResponseEntity<Register>{
        return ResponseEntity(registerService.updateAssisted(register), HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return registerService.delete(id)
    }
}