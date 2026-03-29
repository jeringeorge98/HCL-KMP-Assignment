package org.example.project.network.repository

import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.io.IOException
import org.example.project.domain.CountryDetail
import org.example.project.network.KtorClientProvider
import org.example.project.network.NetworkResponse
import org.example.project.network.models.CountryDetailsResponse
import org.example.project.network.toDomain

class CountryRepositoryImpl(private val apiService: KtorClientProvider): CountryRepository {
    val BASE_URL = "https://restcountries.com/v3.1/name"
    val cache = mutableMapOf<String, CountryDetail>()
    override suspend fun getCountriesByName(name: String): NetworkResponse<List<CountryDetail>> {
      return try {
          NetworkResponse.Loading
          if(cache.containsKey(name)){
              NetworkResponse.Success(listOf(cache[name]!!))
          }else{
          val response = apiService.restClient.get("${BASE_URL}/$name")
          if (response.status.value == 200){
              val list = response.body() as List<CountryDetailsResponse>
              val countryDetailList = list.map { item -> item.toDomain() }
              saveToCache(countryDetailList) //in memory cache
              NetworkResponse.Success(countryDetailList)

          }else if(response.status.value == 404){
              NetworkResponse.Error("The Search query returned no results.Check the query again or enter a different query!", code = 404)
          }
          else{
              NetworkResponse.Error("Response Failed with Error :${response.status.description}",response.status.value)
          }
              }
      }catch (e: IOException){
          NetworkResponse.Error("No Network Error : Your device is not connected to Internet!")
      }
        catch (e: Exception){
            NetworkResponse.Error("Unknown Error : ${e.message}")
        }

    }

    private fun saveToCache(countryDetailList: List<CountryDetail>){
      countryDetailList.forEach {
          cache[it.officialName] = it
      }
    }

}