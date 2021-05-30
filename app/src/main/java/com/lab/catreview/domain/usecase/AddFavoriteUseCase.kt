package com.lab.catreview.domain.usecase

import com.lab.catreview.domain.executor.SchedulerProvider
import com.lab.mycats.domain.repository.RemoteRepository
import io.reactivex.Observable
import javax.inject.Inject


class AddFavoriteUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository, scheduler: SchedulerProvider
) : BaseUseCase<String, AddFavoriteUseCase.AddFavoriteUseCaseParams>(scheduler) {

    override fun buildUseCase(params: AddFavoriteUseCaseParams?): Observable<String> =
        remoteRepository.addFavorite(params?.id, params?.subId)

    class AddFavoriteUseCaseParams(val id: String?, val subId: String?)
}