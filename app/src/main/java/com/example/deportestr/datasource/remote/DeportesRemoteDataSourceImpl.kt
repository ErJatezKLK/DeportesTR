package com.example.deportestr.datasource.remote

import com.example.deportestr.util.DispatcherProvider
import javax.inject.Inject

class DeportesRemoteDataSourceImpl @Inject constructor(
    private val deportesAPI: DeportesAPI,
    private val dispatcherProvider: DispatcherProvider
): DeportesRemoteDataSource {
}