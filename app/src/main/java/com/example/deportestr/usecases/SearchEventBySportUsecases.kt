package com.example.deportestr.usecases

import android.app.usage.UsageEvents.Event
import com.example.deportestr.ui.models.SportEvent
import retrofit2.Response

interface SearchEventBySportUsecases {

   suspend fun searchEventBySport(sportId: Int): Response<List<SportEvent>>
}
