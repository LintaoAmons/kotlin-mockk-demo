/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package top.oatnil.kotlinmockk

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import java.time.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertNotNull

class AppTest {
    @Test fun appHasAGreeting() {
        val classUnderTest = App()
        assertNotNull(classUnderTest.greeting, "app should have a greeting")
    }
    @Nested
    inner class MockStaticMethod {
        private val now = LocalDateTime.of(2023, 3, 3, 0 ,0)

        @Test
        fun `should return mocked datetime`() {
            mockkStatic(LocalDateTime::class)
            every { LocalDateTime.now() } returns now

            println(LocalDateTime.now()) // 2023-03-03T00:00

            unmockkAll()
        }
    }

}

class MockExtensionFunction(private val lintao: Lintao) {
    fun bar() = lintao.extensionFunc()
}

class Lintao

fun Lintao.extensionFunc() = "ExtensionFunc"

class MockExtensionMethod {
    private val lintaoMock = mockk<Lintao>()
    private val target = MockExtensionFunction(lintaoMock)

    @Test
    fun test() {
        mockkStatic(Lintao::extensionFunc) { // !important
            every { lintaoMock.extensionFunc() } returns "mocked function"

            val result = target.bar()

            assertThat(result).isEqualTo("mocked function")
        }
    }
}


