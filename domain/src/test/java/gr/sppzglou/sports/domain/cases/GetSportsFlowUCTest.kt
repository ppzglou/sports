package gr.sppzglou.sports.domain.cases

import gr.sppzglou.sports.domain.Repository
import gr.sppzglou.sports.domain.models.SportDomain
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetSportsFlowUCTest {

    private val repository = mockk<Repository>()

    private val useCase = GetSportsFlowUC(repository)

    @Test
    fun `invoke returns sports from repository`() = runTest {
        val sports = listOf(
            SportDomain(
                id = "FOOT",
                name = "Football",
                events = emptyList()
            )
        )

        every {
            repository.getSports(emptyList())
        } returns flowOf(sports)

        val result = useCase(emptyList()).first()

        assertEquals(sports, result)
    }

    @Test
    fun `invoke passes favorite filtered sport ids correctly`() = runTest {
        val ids = listOf("FOOT", "BASK")

        every {
            repository.getSports(ids)
        } returns flowOf(emptyList())

        useCase(ids).first()

        io.mockk.verify(exactly = 1) {
            repository.getSports(ids)
        }
    }

    @Test
    fun `invoke returns empty list when repository has no sports`() = runTest {
        every {
            repository.getSports(emptyList())
        } returns flowOf(emptyList())

        val result = useCase(emptyList()).first()

        assertEquals(emptyList<SportDomain>(), result)
    }

    @Test
    fun `invoke returns multiple sports correctly`() = runTest {
        val sports = listOf(
            SportDomain(
                id = "FOOT",
                name = "Football",
                events = emptyList()
            ),
            SportDomain(
                id = "BASK",
                name = "Basketball",
                events = emptyList()
            )
        )

        every {
            repository.getSports(emptyList())
        } returns flowOf(sports)

        val result = useCase(emptyList()).first()

        assertEquals(2, result.size)
        assertEquals("FOOT", result[0].id)
        assertEquals("BASK", result[1].id)
    }
}