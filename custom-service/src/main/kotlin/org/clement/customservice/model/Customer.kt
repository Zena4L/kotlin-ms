package org.clement.customservice.model

import jakarta.persistence.*

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

    @Enumerated(EnumType.ORDINAL)
    var gender: Gender? = null
)

enum class Gender {
    MALE, FEMALE
}
