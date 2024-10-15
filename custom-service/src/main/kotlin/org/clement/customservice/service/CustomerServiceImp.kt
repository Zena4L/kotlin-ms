package org.clement.customservice.service

import org.clement.customservice.dto.CreateCustomRequest
import org.clement.customservice.model.Customer
import org.clement.customservice.repository.CustomRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CustomerServiceImp(private val repository: CustomRepository) : CustomerService {
    override fun createCustomer(request: CreateCustomRequest): Customer {
        val customer: Customer = Customer(
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            phone = request.phone,
            address = request.address,
            gender = request.gender
        )

        return repository.save(customer)
    }
}