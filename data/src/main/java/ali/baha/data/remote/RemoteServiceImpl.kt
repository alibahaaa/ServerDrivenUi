package ali.baha.data.remote

import javax.inject.Inject


class RemoteServiceImpl @Inject constructor(private val remoteHelper: RemoteHelper) :
    RemoteService {

    override suspend fun getServerDrivenUiData() = remoteHelper.getServerDrivenUiData()

}