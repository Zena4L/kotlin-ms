package org.clement.customservice.controller

import jakarta.validation.Valid
import org.clement.customservice.dto.CreateCustomRequest
import org.clement.customservice.model.Customer
import org.clement.customservice.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/customer")
class CustomerController(private val customerService: CustomerService) {

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@Valid @RequestBody request: CreateCustomRequest): Customer {
        return customerService.createCustomer(request)
    }
}