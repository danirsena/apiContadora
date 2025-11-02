package com.danirsena.apiContadora.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig(
    private val apiKeyFilter: ApiKeyFilter
) {

    @Bean
    fun registerApiKeyFilter(): FilterRegistrationBean<ApiKeyFilter> {
        val registration = FilterRegistrationBean<ApiKeyFilter>()
        registration.filter = apiKeyFilter
        registration.addUrlPatterns("/*") // protege todas as rotas
        registration.order = 1
        return registration
    }
}
