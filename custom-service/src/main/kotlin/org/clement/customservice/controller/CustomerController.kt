package org.clement.customservice.controller

import jakarta.validation.Valid
import org.clement.customservice.dto.CreateCustomRequest
import org.clement.customservice.dto.GenericMessage
import org.clement.customservice.dto.UpdateCustomer
import org.clement.customservice.model.Customer
import org.clement.customservice.service.CustomerService
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
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

    @GetMapping("/")
    fun fetchAllCustomers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): CollectionModel<EntityModel<Customer>> {
        val customersPage = customerService.getAllCustomers(page, size)

        val customerModels = customersPage.content.map { customer ->
            EntityModel.of(
                customer,
                linkTo(methodOn(this::class.java).fetchACustomer(customer.id!!)).withSelfRel(),
                linkTo(methodOn(this::class.java).fetchAllCustomers(page, size)).withRel("customers")
            )
        }

        return CollectionModel.of(
            customerModels,
            linkTo(methodOn(this::class.java).fetchAllCustomers(page, size)).withSelfRel()
        )
    }

    @GetMapping("/{id}")
    fun fetchACustomer(@PathVariable("id") id: Int): EntityModel<Customer?> {
        val customer: Customer = customerService.getCustomer(id)

        return EntityModel.of(
            customer,
            linkTo(methodOn(this::class.java).fetchACustomer(id)).withSelfRel(),
            linkTo(methodOn(this::class.java).fetchAllCustomers(0, 10)).withRel("customers")
        )
    }

    @PatchMapping("/{id}")
    fun updateCustomer(@RequestBody request: UpdateCustomer, @PathVariable("id") id: Int): EntityModel<GenericMessage> {
        val updateCustomer = customerService.updateCustomer(request, id)

        return EntityModel.of(
            updateCustomer,
            linkTo(methodOn(this::class.java).fetchACustomer(id)).withRel("customer")
        )
    }
}