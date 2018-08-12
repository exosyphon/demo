package com.example.demo

import java.sql.Timestamp
import java.time.LocalTime
import javax.persistence.*

interface BaseThing {
    var name: String
    var _secondName: Timestamp
    var id: Long
}

@Entity
@Table(name = "thing")
data class Thing(
        override var name: String = "",
        @Column(name = "second_name")
        override var _secondName: Timestamp = Timestamp(0),
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long = 0
) : BaseThing {
    constructor(name: String, _secondName: LocalTime, id: Long = 0) : this(name, Timestamp.valueOf("1970-01-01 $_secondName"), id)

    @Transient
    var secondName: LocalTime = _secondName.toLocalDateTime().toLocalTime()
        set(value) {
            _secondName = Timestamp.valueOf("1970-01-01 $value")
            field = value
        }
}

@Entity
@Table(name = "other_thing")
data class OtherThing(
        override var name: String = "",
        @Column(name = "second_name")
        override var _secondName: Timestamp = Timestamp(0),
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long = 0
) : BaseThing {
    constructor(name: String, _secondName: LocalTime, id: Long = 0) : this(name, Timestamp.valueOf("1970-01-01 $_secondName"), id)

    @Transient
    var secondName: LocalTime = _secondName.toLocalDateTime().toLocalTime()
        set(value) {
            _secondName = Timestamp.valueOf("1970-01-01 $value")
            field = value
        }
}
