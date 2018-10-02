package com.example.demo

class MoreSpecificImpl(private val baseImpl: BaseImpl = BaseImpl()): Specific {
    override fun execute(): String {
        return baseImpl.execute()
    }
}