package br.com.erudio.repository

import org.springframework.data.jpa.repository.JpaRepository

interface CambioRepository : JpaRepository<Cambio?, Long?> {
    fun findByFromAndTo(from: String?, to: String?): Cambio?
}