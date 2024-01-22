package top.oatnil.kotlinmockk

import io.mockk.every
import io.mockk.mockkObject
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test


class MockCompanion {
    fun bar() = LintaoCompanion.foo()
}

class LintaoCompanion {
    companion object {
        fun foo() = "Haha"
    }
}

class MockCompanionTest {
    private val target = MockCompanion()

    @Test
    fun `should return mocked value`() {
        println(target.bar()) // Haha
        // highlight-next-line
        mockkObject(LintaoCompanion)
        // mockkObject(LintaoCompanion::class) 这种写法是错误的，不能加 `::class`
        every { LintaoCompanion.foo() } returns "mocked value"

        val bar = target.bar()

        assertThat(bar).isEqualTo("mocked value")
    }
}
