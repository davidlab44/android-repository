package com.david.tot.domain

import com.david.tot.data.ConsumibleRepository

import java.io.File
import javax.inject.Inject

class UpdateImageProductUseCase @Inject constructor(private val repository: ConsumibleRepository) {

    suspend operator fun invoke(idProduct:Int,file:File):Int{
        return repository.updateImageProduct(idProduct,file)
    }
}
