package com.wenubey.starwarswiki.data.dto

interface DomainMapper<T: Any> {
    fun mapToDomainModel(): T
}