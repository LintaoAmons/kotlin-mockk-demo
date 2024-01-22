package top.oatnil.kotlinmockk

import com.google.common.base.Verify.verify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class VerifySecondCall(private val dependency: MyDependency) {
    fun foo(): String {
        return dependency.bar("arg1") + dependency.bar("arg2")

    }
}

class MyDependency {
    fun bar(arg1: String) = "Before mock: Call with $arg1"
}

class TestDrive() {
    @Test
    fun `some test`() {
        val mockDependency = mockk<MyDependency>()
        every { mockDependency.bar(any()) } returns "mock1" andThen "mock2"

        val result = VerifySecondCall(mockDependency).foo()

        assertThat(result).isEqualTo("mock1" + "mock2")
        verify {
            mockDependency.bar(withArg { assertThat(it).isEqualTo("arg1") })
            mockDependency.bar(withArg { assertThat(it).isEqualTo("arg2") })
        }
    }
}
