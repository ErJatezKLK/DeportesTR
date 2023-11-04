package com.example.deportestr.datasource

import com.example.deportestr.datasource.remote.DeportesRemoteDataSource
import javax.inject.Inject

class DeportesRepositoryImpl @Inject constructor(
    private val deportesRemoteDataSource: DeportesRemoteDataSource
) : DeportesRepository {

}