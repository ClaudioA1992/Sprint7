package cl.awakelab.sprint7.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "superhero")
class SuperHeroEntity(@PrimaryKey val id: Int, val name: String, val origin: String,
                      val imageUrl: String, val power: String, val creationYear: Int)