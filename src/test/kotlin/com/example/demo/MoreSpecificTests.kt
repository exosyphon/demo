package com.example.demo

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class MoreSpecificTests : BaseTests() {
    @TestFactory
    fun baseTestBehavior(): List<DynamicTest> {
        return commonBehavior({ baseImpl -> MoreSpecificImpl(baseImpl) })
    }
}