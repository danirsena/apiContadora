package com.danirsena.apiContadora.repository

import com.danirsena.apiContadora.entities.Click
import com.danirsena.apiContadora.entities.Link
import org.springframework.data.jpa.repository.JpaRepository

interface ClickRepository: JpaRepository<Click, Long> {
    fun findByLink(link: Link): List<Click>
    fun deleteAllByLink(link: Link)
}