package com.danirsena.apiContadora.dto

import java.time.LocalDateTime

data class ClickGetInfoDTO (
    val id: Long,
    val clickedAt: LocalDateTime,
    val urlLink: String
)