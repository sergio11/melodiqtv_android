package com.dreamsoftware.melodiqtv.domain.validation

interface IBusinessEntityValidator<T> {
    fun validate(entity: T): Map<String, String>
}