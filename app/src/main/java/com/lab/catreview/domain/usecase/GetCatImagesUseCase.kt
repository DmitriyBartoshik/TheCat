package com.lab.catreview.domain.usecase

import com.lab.catreview.domain.entity.Image
import com.lab.catreview.domain.executor.SchedulerProvider
import com.lab.mycats.domain.repository.RemoteRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetCatImagesUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository, scheduler: SchedulerProvider
) : BaseUseCase<List<Image>, GetCatImagesUseCase.GetCatImagesUseCaseParams>(scheduler) {

    override fun buildUseCase(params: GetCatImagesUseCaseParams?): Observable<List<Image>> =
        remoteRepository.loadCat(params?.limit, params?.page)


    class GetCatImagesUseCaseParams(val limit: Int, val page: Int?)
}
