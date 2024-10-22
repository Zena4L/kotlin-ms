package org.clement.customservice.service

import org.clement.customservice.dto.CreateCustomRequest
import org.clement.customservice.dto.GenericMessage
import org.clement.customservice.dto.UpdateCustomer
import org.clement.customservice.exception.CustomerNotFoundException
import org.clement.customservice.model.Customer
import org.clement.customservice.repository.CustomRepository
import org.clement.customservice.utils.ValidationMessages.UPDATE_SUCC
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CustomerServiceImp(private val repository: CustomRepository,private val template: KafkaTemplate<String,Customer>) :
    CustomerService {
    override fun createCustomer(request: CreateCustomRequest): Customer {
        val customer: Customer = Customer(
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            phone = request.phone,
            address = request.address,
            gender = request.gender
        )

        template.send("customerCreated",customer)

        return repository.save(customer)
    }

    override fun getAllCustomers(page: Int, size: Int): Page<Customer> {
        val pageable: Pageable = PageRequest.of(page, size)
        return repository.findAll(pageable)
    }

    override fun getCustomer(id: Int): Customer {
        return retrieveCustomerById(id)
    }


    override fun updateCustomer(request: UpdateCustomer, id: Int): GenericMessage {
        val customer = retrieveCustomerById(id)

        request.firstName?.let { customer.firstName = it }
        request.lastName?.let { customer.lastName = it }
        request.gender?.let { customer.gender = it }
        request.email?.let { customer.email = it }
        request.phone?.let { customer.phone = it }
        request.address?.let { customer.address = it }

        return GenericMessage(UPDATE_SUCC)
    }

    private fun retrieveCustomerById(id: Int) =
        repository.findCustomerById(id) ?: throw CustomerNotFoundException("Customer with id : $id not found")


}