package org.clement.customservice.exception

import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class GlobalExceptionHandler: ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
       val errors = ex.bindingResult.fieldErrors.joinToString(",") { "${it.field}: ${it.defaultMessage}"}
        return exceptionBuilder(errors,HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
   fun handleGenericException(ex : Exception) : ResponseEntity<Any>{
       return exceptionBuilder(ex.message ?: "Something went wrong, try again !!!", HttpStatus.INTERNAL_SERVER_ERROR)
   }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintsException(ex : ConstraintViolationException): ResponseEntity<Any>{
        val errors = ex.constraintViolations.joinToString(",") { it.message  }
        return exceptionBuilder(errors,HttpStatus.BAD_REQUEST)
    }


    private fun exceptionBuilder(errors: String, status: HttpStatus): ResponseEntity<Any> {
        val problemDetail: ProblemDetail = ProblemDetail.forStatus(status)
        problemDetail.detail = errors
        return ResponseEntity(problemDetail,status)
    }


}