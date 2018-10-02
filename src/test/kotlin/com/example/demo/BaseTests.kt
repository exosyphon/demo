package com.example.demo

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
open class BaseTests {
    private var baseImpl = mock<BaseImpl> {}

    fun commonBehavior(systemUnderTest: (BaseImpl) -> Specific): List<DynamicTest> {
        return mutableListOf(
                DynamicTest.dynamicTest(
                        "A Great Test"
                ) {
                    assertEquals(true, true)
                },
                DynamicTest.dynamicTest(
                        "Another Great Test"
                ) {
                    assertEquals(false, false)
                },
                DynamicTest.dynamicTest(
                        "getting data"
                ) {
                    baseImpl = mock {
                        on { execute() } doReturn "asdf"
                    }
                    assertEquals("asdf", systemUnderTest.invoke(baseImpl).execute())
                }
        )
    }
}