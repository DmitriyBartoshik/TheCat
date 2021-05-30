package com.lab.mycats.domain.usecase

import com.lab.catreview.domain.entity.Image
import com.lab.catreview.domain.executor.SchedulerProvider
import com.lab.catreview.domain.usecase.BaseUseCase
import com.lab.mycats.domain.repository.RemoteRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetFavoriteImagesUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository, scheduler: SchedulerProvider
) : BaseUseCase<List<Image>, GetFavoriteImagesUseCase.GetFavoriteImagesUseCaseParams>(scheduler) {

    override fun buildUseCase(params: GetFavoriteImagesUseCaseParams?): Observable<List<Image>> =
        remoteRepository.loadFavoriteCat(params?.subId, params?.limit, params?.page)

    class GetFavoriteImagesUseCaseParams(val subId: String, val limit: Int, val page: Int?)
}