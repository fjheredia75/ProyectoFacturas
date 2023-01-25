package com.example.Eventpro.repository

import com.example.Eventpro.model.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface MemberRepository : JpaRepository<Member, Long?>{
    fun findById(id: Long?): Member?
}