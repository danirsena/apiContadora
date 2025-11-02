package com.danirsena.apiContadora.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "url_tracker") // Mapeia para a tabela 'url_tracker' do V1
data class Link(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "url", nullable = false, length = 512)
    var url: String,

    @Column(name = "click_count", nullable = false)
    var contador: Int = 0,

    // ----------------------------------------------------------------
    // Mapeamento do Relacionamento OneToMany (1:N)
    // ----------------------------------------------------------------
    // Um 'link' possui muitos 'clicks'
    @OneToMany(
        mappedBy = "id", // O nome da propriedade que mapeia este relacionamento na classe clicks
        cascade = [CascadeType.ALL], // Operações de persistência (salvar, deletar) se propagam
        orphanRemoval = true,       // Remove linhas órfãs do banco
        fetch = FetchType.LAZY
    )
    val clicks: MutableSet<Click> = mutableSetOf()
)

