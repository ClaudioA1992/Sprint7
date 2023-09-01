package cl.awakelab.sprint7.data.remote.models

import androidx.room.PrimaryKey
import cl.awakelab.sprint7.data.local.entities.SuperHeroEntity

class SuperHero(val id: Int, val name: String, val origin: String,
                val imageUrl: String, val power: String, val creationYear: Int) {

    fun transformToEntity(superHero: SuperHero): SuperHeroEntity {
        return SuperHeroEntity(id, name, origin, imageUrl, power, creationYear)
    }

}