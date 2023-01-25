package com.example.Eventpro.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotBlank


@Entity
@Table(name ="member")
class Member {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var nui: String?=null
    @NotBlank
    var fullname: String?=null
    var email: String?=null
    var age: Long? = null
}