package top.oatnil.kotlinmockk

import io.mockk.every
import io.mockk.spyk
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class UseSpky() {
    fun foo(): String {
        throw RuntimeException("Foo")
    }

    fun anotherFoo(): String {
        return "anotherFoo"
    }
}

class TestDrive1 {

    @Test
    fun `use spyk by normal constructor`() {
        val useSpky = spyk(UseSpky())
        every { useSpky.foo() } returns "mock"

        val fooResult = useSpky.foo()
        assertThat(fooResult).isEqualTo("mock")

        val anotherFooResult = useSpky.anotherFoo()
        assertThat(anotherFooResult).isEqualTo("anotherFoo")
    }
}
