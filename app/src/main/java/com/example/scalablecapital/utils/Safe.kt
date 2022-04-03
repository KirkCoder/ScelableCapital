package com.example.scalablecapital.utils

sealed class Safe<T> {

    private data class Value<T>(val value: T) : Safe<T>()

    private data class Error<T>(val exception: Exception) : Safe<T>()

    fun throwError(): T {
        return when (this) {
            is Value -> this.value
            is Error -> throw this.exception
        }
    }

    fun ignoreError(): T? {
        return when (this) {
            is Value -> this.value
            is Error -> null
        }
    }

    fun ignoreError(defaultValue: T): T {
        return when (this) {
            is Value -> this.value
            is Error -> defaultValue
        }
    }

    fun ignoreError(defaultValueProvider: () -> T): T {
        return when (this) {
            is Value -> this.value
            is Error -> defaultValueProvider.invoke()
        }
    }

    fun consumeError(consumer: (Exception) -> Unit): T? {
        return when (this) {
            is Value -> this.value
            is Error -> {
                consumer.invoke(this.exception)
                null
            }
        }
    }

    fun ifPresent(consumer: (T) -> Unit): T? {
        return if (this is Value) {
            consumer.invoke(this.value)
            this.value
        } else {
            null
        }
    }

    companion object {

        inline operator fun <T> invoke(block: () -> T): Safe<T> {
            return try {
                value(block.invoke())
            } catch (e: Exception) {
                error(e)
            }
        }

        fun <T> value(value: T): Safe<T> {
            return Value(value)
        }

        fun <T> error(exception: Exception): Safe<T> {
            return Error(exception)
        }
    }
}