package com.example.demo

class SpecificImpl(private val baseImpl: BaseImpl = BaseImpl()): Specific {
    override fun execute(): String {
        return baseImpl.execute()
    }
}