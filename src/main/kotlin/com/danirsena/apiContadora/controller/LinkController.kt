package com.danirsena.apiContadora.controller

import com.danirsena.apiContadora.dto.LinkCreateDTO
import com.danirsena.apiContadora.dto.LinkGetDTO
import com.danirsena.apiContadora.dto.LinkUpdateDTO
import com.danirsena.apiContadora.entities.Click
import com.danirsena.apiContadora.entities.Link
import com.danirsena.apiContadora.repository.ClickRepository
import com.danirsena.apiContadora.repository.LinkRespository
import jakarta.transaction.Transactional
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/link")
class LinkController(val linkRespository: LinkRespository, val clickRepository: ClickRepository) {

    @GetMapping("/sayHello")
    fun sayHello(): String = "Hello!"

    @GetMapping("/getUrls/short")
    fun getAllContadores(): ResponseEntity<List<LinkGetDTO>> {
        val getAll = linkRespository.findAll()
        if (getAll.isEmpty()) return ResponseEntity.notFound().build()

        val responseLinks = getAll.map {
            LinkGetDTO(
                it.id,
                it.url,
                it.contador,
                it.clicks.map { click -> click.clickedAt.toString() }
            )
        }
        return ResponseEntity.ok().body(responseLinks)
    }

    @GetMapping("/getUrl/{id}")
    fun getUrl(@PathVariable id: Long): ResponseEntity<LinkGetDTO> {
        val getUrl = linkRespository.findById(id)

        val getLinks = clickRepository.findByLink(getUrl.get())

        if (getUrl.isEmpty()) return ResponseEntity.notFound().build()

        val returnUrl = LinkGetDTO(
            id = getUrl.get().id,
            url = getUrl.get().url,
            cont = getUrl.get().contador,
            listCLicks = getLinks.map { click -> click.clickedAt.toString() }
        )

        return ResponseEntity.ok().body(returnUrl)
    }

    @PostMapping("/addUrl")
    fun addUrl(@RequestBody addLinkDTO: LinkCreateDTO): ResponseEntity<Link> {

        var linkDto = Link(
            url = addLinkDTO.url,
            contador = 0
        )

        //salvar no repositorio

        linkRespository.save(linkDto)
        return ResponseEntity.ok().body(linkDto)
    }

    @PutMapping("/updateUrlLink/{id}")
    fun updateUrl(@PathVariable id: Long, @RequestBody updateLinkDTO: LinkUpdateDTO): ResponseEntity<Link> {

        val link = linkRespository.findById(id)

        if (link.isEmpty()) return ResponseEntity.notFound().build()

        link.get().url = updateLinkDTO.url.ifBlank { link.get().url }

        linkRespository.save(link.get())

        return ResponseEntity.ok().body(link.get())
    }

    @Transactional
    @PutMapping("/zerarContador/{id}")
    fun updateContador(@PathVariable id: Long): ResponseEntity<Link> {

        val link = linkRespository.findById(id)

        if (link.isEmpty()) return ResponseEntity.notFound().build()

        link.get().contador = 0

        clickRepository.deleteAllByLink(link.get())

        linkRespository.save(link.get())

        return ResponseEntity.ok().body(link.get())
    }

    // Exemplo de como ficaria a lógica de deleção robusta
    @DeleteMapping("/deleteUrl/{id}")
    fun deleteUrl(@PathVariable id: Long): ResponseEntity<Void> {

        val link = linkRespository.findById(id)

        if (link.isEmpty()) return ResponseEntity.notFound().build()

        // 1. Limpe a coleção de cliques (se houver um relacionamento Click com Cascade.ALL)
        link.get().clicks.clear()// Assumindo que 'clicks' é a sua coleção OneToMany de Click, limpa ela

        // 3. Salve as mudanças para garantir que os filhos sejam desvinculados
        linkRespository.save(link.get())

        linkRespository.delete(link.get())

        return ResponseEntity.noContent().build()
    }

    @GetMapping("/abrirLink/{id}")
    fun abriLink(@PathVariable id: Long): ResponseEntity<Void> {

        var url = linkRespository.findById(id)

        if (url.isEmpty()) return ResponseEntity.notFound().build()

        incrementarContador(id)

        val click = Click(
            id = 0,
            clickedAt = LocalDateTime.now(),
            link = url.get()
        )

        clickRepository.save(click)

        return ResponseEntity.status(302)
            .header("Location", url.get().url)
            .build();
    }

    private fun incrementarContador(id: Long) {
        var url = linkRespository.findById(id)
        url.get().contador++
        linkRespository.save(url.get())
    }
}