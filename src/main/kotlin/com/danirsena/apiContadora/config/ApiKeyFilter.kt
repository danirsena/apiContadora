package com.danirsena.apiContadora.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class ApiKeyFilter(
    @Value("\${app.api.key}") private val apiKey: String
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val method = request.method
        val path = request.requestURI

        // 🔓 Permitir GETs e rota específica sem API key
        val rotaLivre = path.startsWith("/api/abrirLink") // coloque aqui sua rota
        if (method == "GET" || rotaLivre) {
            filterChain.doFilter(request, response)
            return
        }

        // 🔒 Verificar API key
        val headerKey = request.getHeader("X-API-KEY")

        if (headerKey == null || headerKey != apiKey) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.write("API key invalida ou ausente.")
            return
        }

        // ✅ Autorizado
        filterChain.doFilter(request, response)
    }
}
