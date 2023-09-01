package cl.awakelab.sprint7.data.remote.models

import androidx.room.PrimaryKey
import cl.awakelab.sprint7.data.local.entities.SuperHeroEntity

class SuperHero(val id: Int, val nombre: String, val origen: String,
                val imagenLink: String, val poder: String, val Año_creacion: Int) {

    fun transformToEntity(superHero: SuperHero): SuperHeroEntity {
        return SuperHeroEntity(id, nombre, origen, imagenLink, poder, Año_creacion)
    }

}