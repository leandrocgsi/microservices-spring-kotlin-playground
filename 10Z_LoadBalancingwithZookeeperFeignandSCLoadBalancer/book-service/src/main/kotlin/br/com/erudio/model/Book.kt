package br.com.erudio.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import java.util.*

@Entity(name = "book")
@JsonIgnoreProperties("hibernateLazyInitializer", "handler")
data class Book (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "author", nullable = false, length = 180)
    var author:  String = "",

    @Column(name = "launch_date", nullable = false)
    @Temporal(TemporalType.DATE)
    var launchDate: Date? = null,

    @Column(nullable = false)
    var price: Double? = null,

    @Column(nullable = false, length = 250)
    var title:  String = "",

    @Transient
    var currency:  String = "",

    @Transient
    var environment: String? = null
)