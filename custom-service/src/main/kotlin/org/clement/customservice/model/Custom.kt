package org.clement.customservice.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "customers")
class Customer(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Int? = null,

    @Column(nullable = false)
    var firstName: String,

    @Column(nullable = false)
    var lastName: String,

    @Column(nullable = false)
    var email: String,

    @Column(nullable = false)
    var phone: String? = null,

    var address: String? = null,

    var sex : String? = null
)