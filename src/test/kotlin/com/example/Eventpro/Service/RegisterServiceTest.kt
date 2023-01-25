package com.example.Eventpro.Service

import com.example.Eventpro.model.Register
import com.example.Eventpro.repository.RegisterRepository
import com.example.Eventpro.service.RegisterService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest

class RegisterServiceSave {
    @InjectMocks
    lateinit var registerService: RegisterService

    @Mock
    lateinit var registerRepository: RegisterRepository

    val registerMock = Register().apply {
        id=1
        code= "50"
        assisted ="pepe"

    }

    @Test
    fun saveRegisterCorrect(){
        Mockito.`when`(registerRepository.save(Mockito.any(Register::class.java))).thenReturn(registerMock)
        val response = registerService.save(registerMock)
        Assertions.assertEquals(response.id, registerMock.id)
    }


    @Test
    fun saveRegisterWhenCodeIsBlank(){

        Assertions.assertThrows(Exception::class.java) {
            registerMock.apply { code=" "}
            Mockito.`when`(registerRepository.save(Mockito.any(Register::class.java))).thenReturn(registerMock)
            registerService.save(registerMock)
        }

    }

    @Test
    fun saveRegisterWhenAssisted_atIsBlank(){

        Assertions.assertThrows(Exception::class.java) {
            registerMock.apply { assisted=" "}
            Mockito.`when`(registerRepository.save(Mockito.any(Register::class.java))).thenReturn(registerMock)
            registerService.save(registerMock)
        }

    }
}
