package cl.awakelab.sprint7.data.remote.models

import cl.awakelab.sprint7.data.local.entities.SuperHeroEntity
import org.junit.Assert.*

import org.junit.Test

class SuperHeroTest {

    @Test
    fun transformToEntity() {
        //Given
        val id = 1
        val name = "Iron Man"
        val origen = "Manhattan"
        val imagenLink = "www.google.cl"
        val poder = "Dinero"
        val Año_creacion = 1960
        val superHero = SuperHero(id, name, origen, imagenLink, poder, Año_creacion)
        //When
        val superHeroEntity = superHero.transformToEntity(superHero)
        //Then
        assertEquals(superHeroEntity.id, superHero.id)
        assertEquals(superHeroEntity.name, superHero.nombre)
        assertEquals(superHeroEntity.origin, superHero.origen)
        assertEquals(superHeroEntity.imageUrl, superHero.imagenLink)
        assertEquals(superHeroEntity.power, superHero.poder)
        assertEquals(superHeroEntity.creationYear, superHero.Año_creacion)
    }

}