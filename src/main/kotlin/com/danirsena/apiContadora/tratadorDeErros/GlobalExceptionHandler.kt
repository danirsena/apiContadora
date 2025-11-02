package com.danirsena.apiContadora.tratadorDeErros

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.NoHandlerFoundException

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException::class)
    fun erro404(): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Puts, a página digitada não existe. Verifique a url digitada, porque provavelmete, está errada :( ")
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun erro405(): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
            .body("Puts, verifique o tipo de requisição que está fazendo (GET, PUT, DELETE...), porque provavelmente, ela está errada :( ")
    }
}