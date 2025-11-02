package com.danirsena.apiContadora.repository

import com.danirsena.apiContadora.entities.Click
import org.springframework.data.jpa.repository.JpaRepository

interface ClickRepository: JpaRepository<Click, Long> {
}