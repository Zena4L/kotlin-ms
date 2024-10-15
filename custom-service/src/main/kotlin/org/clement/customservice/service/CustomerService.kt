package org.clement.customservice.service

import org.clement.customservice.dto.CreateCustomRequest
import org.clement.customservice.model.Customer


interface CustomerService {
    fun createCustomer(request: CreateCustomRequest): Customer

}