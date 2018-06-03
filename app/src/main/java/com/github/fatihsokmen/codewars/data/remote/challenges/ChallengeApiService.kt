package com.github.fatihsokmen.codewars.data.remote.challenges

import com.github.fatihsokmen.codewars.data.remote.challengedetails.ChallengeDetailsDto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ChallengeApiService {

    @GET("users/{username}/code-challenges/completed")
    fun getCompletedChallenges(
            @Path("username") userName: String,
            @Query("page") page: Int): Observable<CompletedChallengesResponseDto>

    @GET("users/{username}/code-challenges/authored")
    fun getAuthoredChallenges(
            @Path("username") userName: String): Observable<AuthoredChallengesResponseDto>


    @GET("code-challenges/{challengeId}")
    fun getChallengeDetails(
            @Path("challengeId") challengeId: String): Observable<ChallengeDetailsDto>

}
