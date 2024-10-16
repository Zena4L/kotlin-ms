package org.clement.customservice.service

import org.clement.customservice.dto.CreateCustomRequest
import org.clement.customservice.model.Customer
import org.springframework.data.domain.Page


interface CustomerService {
    fun createCustomer(request: CreateCustomRequest): Customer
    fun getAllCustomers(page: Int, size: Int): Page<Customer>
    fun getCustomer(id: Int): Customer

}