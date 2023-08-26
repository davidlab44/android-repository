package com.david.tot.domain.spotable

import com.david.tot.data.SpotableRepository
import com.david.tot.domain.model.Spotable
import com.david.tot.util.SpotableLists
import javax.inject.Inject

class GetAllSpotablesFromHardcodedListUseCase @Inject constructor(private val repository: SpotableRepository) {
    suspend operator fun invoke():List<Spotable> {
        return SpotableLists
    }
}
