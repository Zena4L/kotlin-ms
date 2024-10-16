package org.clement.customservice.dto


import org.clement.customservice.model.Gender


data class UpdateCustomer(

    val firstName: String? = null,

    val lastName: String? = null,

    val email: String? = null,

    val phone: String? = null,

    val address: String? = null,

    val gender: Gender? = null
) {

}
