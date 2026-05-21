package gr.sppzglou.sports.domain.cases

import gr.sppzglou.sports.domain.Repository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SwitchFavoriteUCTest {

    private val repository = mockk<Repository>(relaxed = true)

    private val useCase = SwitchFavoriteUC(repository)

    @Test
    fun `invoke calls repository switchFavorite with correct event id`() = runTest {
        val eventId = "event_1"

        useCase(eventId)

        coVerify(exactly = 1) {
            repository.updateFavorite(eventId)
        }
    }

    @Test
    fun `invoke calls repository only once`() = runTest {
        val eventId = "event_123"

        useCase(eventId)

        coVerify(exactly = 1) {
            repository.updateFavorite(any())
        }
    }

    @Test
    fun `invoke passes different ids correctly`() = runTest {
        val firstId = "event_1"
        val secondId = "event_2"

        useCase(firstId)
        useCase(secondId)

        coVerify {
            repository.updateFavorite(firstId)
            repository.updateFavorite(secondId)
        }
    }
}