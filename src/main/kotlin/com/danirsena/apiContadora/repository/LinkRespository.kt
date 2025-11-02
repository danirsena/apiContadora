package com.danirsena.apiContadora.repository

import org.springframework.data.jpa.repository.JpaRepository

import com.danirsena.apiContadora.entities.Link

interface LinkRespository: JpaRepository<Link, Long > {
}