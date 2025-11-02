package com.danirsena.apiContadora.controller

import com.danirsena.apiContadora.dto.ClickGetInfoDTO
import com.danirsena.apiContadora.entities.Click
import com.danirsena.apiContadora.repository.ClickRepository
import com.danirsena.apiContadora.repository.LinkRespository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.collections.map

@RestController
@RequestMapping("apiContadora/click")
class ClickController(
    val clickRepository: ClickRepository,
    val linkRespository: LinkRespository
) {

    @GetMapping("/sayHello")
    fun sayHello(): String = "Hello, Clicks!"

    @GetMapping("/getClick/{id}")
    fun getClicks(@PathVariable id: Long): ResponseEntity<ClickGetInfoDTO> {
        val getClick = clickRepository.findById(id)
        if (getClick.isEmpty()) return ResponseEntity.notFound().build()

        val responseClick = ClickGetInfoDTO (
            id = getClick.get().id,
            clickedAt = getClick.get().clickedAt,
            urlLink = getClick.get().link.url
        )

        return ResponseEntity.ok().body(responseClick)
    }

    @GetMapping("/getClicks")
    fun getAllClicks(): ResponseEntity<List<ClickGetInfoDTO>> {
        val getAll = clickRepository.findAll()
        if (getAll.isEmpty()) return ResponseEntity.notFound().build()

        val responseClicks = getAll.map {
            ClickGetInfoDTO(
                it.id,
                it.clickedAt,
                //pegue a url do
                it.link.url.orEmpty()
            )
        }
        return ResponseEntity.ok().body(responseClicks)
    }

    @DeleteMapping("/deleteClick/{id}")
    fun deleteClick(@PathVariable id: Long): ResponseEntity<Void> {
        val deleteClick = clickRepository.findById(id)
        if (deleteClick.isEmpty()) return ResponseEntity.notFound().build()
        clickRepository.delete(deleteClick.get())
        return ResponseEntity.noContent().build()
    }
}