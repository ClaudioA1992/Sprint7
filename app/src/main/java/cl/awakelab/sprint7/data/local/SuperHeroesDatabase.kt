package cl.awakelab.sprint7.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.awakelab.sprint7.data.local.entities.SuperHeroDetailEntity
import cl.awakelab.sprint7.data.local.entities.SuperHeroEntity

@Database(entities = [SuperHeroEntity::class, SuperHeroDetailEntity::class], version = 1)
abstract class SuperHeroesDatabase: RoomDatabase() {

    abstract fun getSuperHeroesDao(): SuperHeroesDao

    companion object {
        @Volatile
        private var INSTANCE: SuperHeroesDatabase? = null

        fun getDatabase(context: Context): SuperHeroesDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SuperHeroesDatabase::class.java,
                    "superheroes_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }

}