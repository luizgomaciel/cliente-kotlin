package br.kotlin.app.host

import br.kotlin.app.application.exception.BusinessException
import br.kotlin.app.application.exception.NoContentException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class HostHandler {
    @ExceptionHandler(NoContentException::class)
    fun handleNoContentException(ex: NoContentException?, request: WebRequest?): ResponseEntity<MessageError?>? {
        return ResponseEntity<MessageError?>(HttpStatus.NO_CONTENT)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(ex: BusinessException, request: WebRequest?): ResponseEntity<MessageError?>? {
        return ResponseEntity<MessageError?>(
            getMessage(
                ex,
                java.lang.String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value())
            ), HttpStatus.UNPROCESSABLE_ENTITY
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        ex: MethodArgumentNotValidException,
        request: WebRequest?
    ): ResponseEntity<MessageError?>? {
        return ResponseEntity<MessageError?>(
            getMessage(ex, java.lang.String.valueOf(HttpStatus.BAD_REQUEST.value())),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception, request: WebRequest?): ResponseEntity<MessageError?>? {
        return ResponseEntity<MessageError?>(
            getMessage(
                ex,
                java.lang.String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())
            ), HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    private fun getMessage(e: Exception, codigo: String): MessageError? {
        return MessageError(
            codigo = codigo,
            mensagem = e.message
        )
    }
}