package com.danirsena.apiContadora.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.sql.Timestamp
import java.time.LocalDateTime

@Entity
@Table(name = "click") // Mapeia para a tabela 'linked_url' do V2
data class Click(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "created_at")
    var clickedAt: LocalDateTime = LocalDateTime.now(),

    // ----------------------------------------------------------------
    // Mapeamento do Relacionamento ManyToOne (N:1)
    // ----------------------------------------------------------------
    // Muitos 'Click' pertencem a um único 'Link'
    @ManyToOne(fetch = FetchType.LAZY)

    // Indica a coluna da chave estrangeira (FK) na tabela 'linked_url'
    @JoinColumn(name = "link_id", nullable = false)
    var link: Link // Referência à entidade pai
)