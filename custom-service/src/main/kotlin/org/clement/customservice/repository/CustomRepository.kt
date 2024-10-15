package org.clement.customservice.repository

import org.clement.customservice.model.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomRepository : JpaRepository<Customer, Int> {
}