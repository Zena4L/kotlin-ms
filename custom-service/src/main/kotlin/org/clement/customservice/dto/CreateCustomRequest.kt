package org.clement.customservice.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.clement.customservice.model.Gender
import org.clement.customservice.utils.ValidationMessages
import org.clement.customservice.utils.ValidationMessages.EMAIl
import org.clement.customservice.utils.ValidationMessages.NOT_BLANK
import org.clement.customservice.utils.ValidationMessages.NOT_NULL

data class CreateCustomRequest(
    @field:NotBlank(message = NOT_BLANK)
    @field:NotNull(message = NOT_NULL)
    val firstName: String,

    @field:NotBlank(message = NOT_BLANK)
    @field:NotNull(message = NOT_NULL)
    val lastName: String,

    @field:NotBlank(message = NOT_BLANK)
    @field:NotNull(message = NOT_NULL)
    @field:Email(message = EMAIl )
    val email: String,

    val phone : String?,

    val address : String?,

    val gender : Gender?
    ) {

}
