package com.core.app.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import io.ktor.serialization.kotlinx.json.json

object HttpClientFactory {
    fun getInstance(): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json(json = Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
                )
            }
            expectSuccess = true
            install(HttpTimeout){
                requestTimeoutMillis = 10000
                connectTimeoutMillis = 10000
                socketTimeoutMillis = 10000
            }
            install(DefaultRequest) {
                url {
                    host = "api.escuelajs.co"
                    protocol = URLProtocol.HTTPS
                }
                header("Content-Type", "application/json")
            }
        }
    }
}