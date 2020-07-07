package com.revolut.ratesconverter.base.data

interface BaseDataMapperInterface<I, O> {
    fun mapToDto(input: I): O
    fun mapToDtoList(input: List<I>): List<O>
}