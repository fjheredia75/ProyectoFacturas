package com.example.Eventpro.Service

import com.example.Eventpro.model.Member
import com.example.Eventpro.repository.MemberRepository
import com.example.Eventpro.service.MemberService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class MemberServiceSave {
    @InjectMocks
    lateinit var memberService: MemberService

    @Mock
    lateinit var memberRepository: MemberRepository

    val memberMock = Member().apply {
        id=2
        fullname="Cristiano"
        email="@cris"
        age= 38
    }

    @Test
    fun saveMemberCorrect(){
        Mockito.`when`(memberRepository.save(Mockito.any(Member::class.java))).thenReturn(memberMock)
        val response = memberService.save(memberMock)
        Assertions.assertEquals(response.id, memberMock.id)
    }


    @Test
    fun saveMemberWhenFullnameIsBlank(){

        Assertions.assertThrows(Exception::class.java) {
            memberMock.apply { fullname=" "}
            Mockito.`when`(memberRepository.save(Mockito.any(Member::class.java))).thenReturn(memberMock)
            memberService.save(memberMock)
        }

    }

    @Test
    fun saveMemberWhenNuiIsBlank(){

        Assertions.assertThrows(Exception::class.java) {
            memberMock.apply { email=" "}
            Mockito.`when`(memberRepository.save(Mockito.any(Member::class.java))).thenReturn(memberMock)
            memberService.save(memberMock)
        }

    }
}
