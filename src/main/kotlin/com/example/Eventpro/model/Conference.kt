package com.example.Eventpro.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name ="conference")
class Conference {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    @NotBlank
    var title: String?=null
    var speaker: String?=null
    var hora: String?=null
    var day: String?=null
    @Column(name="total_attendees")
    var totalAttendees: Long?=null
    @Column(name="event_id")
    var eventId: Long?=null
}