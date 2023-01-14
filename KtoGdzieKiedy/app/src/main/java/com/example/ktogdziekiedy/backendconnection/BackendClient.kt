package com.example.ktogdziekiedy.backendconnection

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.*

@Serializable
data class LoginData(val login: String, val password: String)

@Serializable
data class LoginResponse(val token: String)

@Serializable
data class User(val id: Int, val login: String)

@Serializable
data class MeResponse(val user: User)

@Serializable
data class TaskData(val category: String, val name: String)

@Serializable
data class Task(
    val name: String,
    val category: String,
    val userId: Int,
    val endTime: String?,
    val id: Int,
    val createdAt: String
)

@Serializable
data class AddTaskResponse(val task: Task)

@Serializable
data class RunningTasksResponse(val tasks: List<Task>)

@Serializable
data class ChangeTaskStatusResponse(val task: Task)


object BackendClient {
    private var host = "https://ktogdziekiedy.scuroguardiano.net"
    // TOKEN jest PUBLICZNY więc można go przechować gdzieś,
    // żeby można było logować się potem tylko pinem,
    // póki nie nastąpi wciśnięcie przycisku logout
    var authToken: String = ""
    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
        expectSuccess = true
    }

    fun setHost(host: String) {
        BackendClient.host = host
    }

    suspend fun login(username: String, password: String) {
        val response = client.post(host) {
            url {
                appendPathSegments("login")
            }
            contentType(ContentType.Application.Json)
            setBody(LoginData(username, password))
        }

        val parsed: LoginResponse = response.body()
        authToken = parsed.token
    }

    suspend fun me(): User {
        val response = client.get(host) {
            url {
                appendPathSegments("me")
            }
            headers {
                append(HttpHeaders.Authorization, "Bearer $authToken")
            }
        }

        val parsed: MeResponse = response.body()
        return parsed.user
    }

    suspend fun addTask(category: String, name: String): Task {
        val response = client.post(host) {
            url {
                appendPathSegments("tasks")
            }

            headers {
                append(HttpHeaders.Authorization, "Bearer $authToken")
            }

            contentType(ContentType.Application.Json)
            setBody(TaskData(category, name))
        }

        val parsed: AddTaskResponse = response.body()
        return parsed.task
    }

    suspend fun runningTasks(): List<Task> {
        val response = client.get(host) {
            url {
                appendPathSegments("running-tasks")
            }
            headers {
                append(HttpHeaders.Authorization, "Bearer $authToken")
            }
        }

        val parsed: RunningTasksResponse = response.body()
        return parsed.tasks
    }

    suspend fun changeTaskStatus(id: Int): Task {
        val response = client.put(host) {
            url {
                appendPathSegments("running-tasks", id.toString(), "change-status")
            }
            headers {
                append(HttpHeaders.Authorization, "Bearer $authToken")
            }
        }
        val parsed: ChangeTaskStatusResponse = response.body()
        return parsed.task
    }


    fun logout() {
        authToken = ""
    }
}