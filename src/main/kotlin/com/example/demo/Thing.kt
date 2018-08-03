package com.example.demo

import java.sql.Timestamp
import java.time.LocalTime
import javax.persistence.*

@Entity
data class Thing(
        var name: String = "",
        @Column(name = "second_name")
        var _secondName: Timestamp = Timestamp(0),
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
) {
    constructor(name: String, _secondName: LocalTime, id: Long = 0) : this(name, Timestamp.valueOf("1970-01-01 $_secondName"), id)

    @Transient
    var secondName: LocalTime = _secondName.toLocalDateTime().toLocalTime()
        set(value) {
            _secondName = Timestamp.valueOf("1970-01-01 $value")
            field = value
        }
}
