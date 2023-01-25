package com.example.Eventpro.repository

import com.example.Eventpro.model.Register
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface RegisterRepository : JpaRepository<Register, Long?>{
    fun findById(id: Long?): Register?

    @Query(nativeQuery =true)//Va a leer jpa-named.....
    fun sumarAsistente(@Param ("conferenceId") conferenceId: Long?): Long?

    @Query(nativeQuery =true)//Va a leer jpa-named.....
    fun listarConf(@Param ("memberId") memberId: Long?): List<Register>?

}