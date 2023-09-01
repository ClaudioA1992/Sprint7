package cl.awakelab.sprint7

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import cl.awakelab.sprint7.data.local.SuperHeroesDao
import cl.awakelab.sprint7.data.local.SuperHeroesDatabase
import cl.awakelab.sprint7.data.local.entities.SuperHeroEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class RoomDatabaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var superHeroesDao: SuperHeroesDao
    private lateinit var db: SuperHeroesDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SuperHeroesDatabase::class.java).build()
        superHeroesDao = db.getSuperHeroesDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertSuperHeroes_empty() = runBlocking {
        // Given
        val superHeroesList = listOf<SuperHeroEntity>()

        // When
        superHeroesDao.insertSuperHeroes(superHeroesList)

        // Then A
        val it = superHeroesDao.selectSuperHeroes().getOrAwaitValue()
        assertThat(it).isNotNull()
        assertThat(it).isEmpty()

        // Then B
        superHeroesDao.selectSuperHeroes().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertSuperHeroes_happyCase_1element() = runBlocking {
        // Given
        val superHeroesList = listOf(SuperHeroEntity(1, "Wolverine", "Canada", "www.yahoo.com",
            "Curación acelerada", 1980))

                // When
        superHeroesDao.insertSuperHeroes(superHeroesList)

        // Then
        superHeroesDao.selectSuperHeroes().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun insertSuperHeroes_happyCase_3elements() = runBlocking {
        // Given
        val superHeroesList = listOf(SuperHeroEntity(1, "Heroe A", "Alaska", "", "", 2000),
            SuperHeroEntity(2, "Heroe B", "China", "", "", 1992),
            SuperHeroEntity(3, "Heroe C", "Inglaterra", "", "", 3000))

        // When
        superHeroesDao.insertSuperHeroes(superHeroesList)

        // Then
        superHeroesDao.selectSuperHeroes().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(3)
        }
    }


    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS,
        afterObserve: () -> Unit = {}
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(value: T) {
                data = value
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)

        try {
            afterObserve.invoke()

            // Don't wait indefinitely if the LiveData is not set.
            if (!latch.await(time, timeUnit)) {
                throw TimeoutException("LiveData value was never set.")
            }

        } finally {
            this.removeObserver(observer)
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }


}