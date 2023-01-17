package backendconnection

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
data class User(val id: Int, val login: String, val isAdmin: Boolean)

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
    val createdAt: String,
    val duration: String? = null,
    val user: User? = null
)

@Serializable
data class AddTaskResponse(val task: Task)

@Serializable
data class RunningTasksResponse(val tasks: List<Task>)

@Serializable
data class ChangeTaskStatusResponse(val task: Task)

@Serializable
data class GetDoneTasksFromCurrentUserResponse(val tasks: List<Task>)

@Serializable
data class GetDoneTasksFromAllUsersResponse(val tasks: List<Task>)

@Serializable
data class GetAllDoneFromOneTaskResponse(val tasks: List<Task>)

@Serializable
data class GetMeanFromTaskResponse(val meanTime: String)

@Serializable
data class GetBestTimeEndedResponse(val bestTime: String)

@Serializable
data class TasksHierarchyName(val tasks: List<Task>)

typealias TasksHierarchyCategory = Map<String, TasksHierarchyName>
typealias TasksHierarchy = Map<String, TasksHierarchyCategory>

@Serializable
data class GetDoneTasksFromCurrentUserHierarchyResponse(val tasks: TasksHierarchy)

@Serializable
data class GetDoneTasksFromAllUsersHierarchyResponse(val tasks: TasksHierarchy)

object BackendClient {
    private var host = "https://ktogdziekiedy.scuroguardiano.net/"
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

    suspend fun getDoneTasksFromCurrentUser(id: Int): List<Task> {
        val response = client.get(host) {
            url {
                appendPathSegments("tasks", "me", "done")
            }
            headers {
                append(HttpHeaders.Authorization, "Bearer $authToken")
            }
        }

        val parsed: GetDoneTasksFromCurrentUserResponse = response.body()
        return parsed.tasks
    }

    suspend fun getDoneTasksFromCurrentUserHierarchy(): TasksHierarchy {
        val response = client.get(host) {
            url {
                appendPathSegments("tasks", "me", "done", "hierarchy")
            }
            headers {
                append(HttpHeaders.Authorization, "Bearer $authToken")
            }
        }
        val parsed: GetDoneTasksFromCurrentUserHierarchyResponse = response.body()
        return parsed.tasks
    }

    suspend fun getDoneTasksFromAllUsersHierarchy(): TasksHierarchy {
        val response = client.get(host) {
            url {
                appendPathSegments("tasks", "done", "hierarchy")
            }
            headers {
                append(HttpHeaders.Authorization, "Bearer $authToken")
            }
        }
        val parsed: GetDoneTasksFromAllUsersHierarchyResponse = response.body()
        return parsed.tasks
    }

    suspend fun getDoneTasksFromAllUsers(): List<Task> {
        val response = client.get(host) {
            url {
                appendPathSegments("tasks", "done")
            }
            headers {
                append(HttpHeaders.Authorization, "Bearer $authToken")
            }
        }

        val parsed: GetDoneTasksFromAllUsersResponse = response.body()
        return parsed.tasks
    }

    suspend fun getAllDoneFromOneTask(category: String, name: String): List<Task> {
        val response = client.get(host) {
            url {
                appendPathSegments("tasks", "done", category, name)
            }
            headers {
                append(HttpHeaders.Authorization, "Bearer $authToken")
            }
        }

        val parsed: GetAllDoneFromOneTaskResponse = response.body()
        return parsed.tasks
    }

    suspend fun getMeanFromTask(category: String, name: String): String {
        val response = client.get(host) {
            url {
                appendPathSegments("tasks", "done", category, name, "mean")
            }
            headers {
                append(HttpHeaders.Authorization, "Bearer $authToken")
            }
        }

        val parsed: GetMeanFromTaskResponse = response.body()
        return parsed.meanTime
    }

    suspend fun getBestTimeEnded(category: String, name: String): String {
        val response = client.get(host) {
            url {
                appendPathSegments("tasks", "done", category, name, "best")
            }
            headers {
                append(HttpHeaders.Authorization, "Bearer $authToken")
            }
        }

        val parsed: GetBestTimeEndedResponse = response.body()
        return parsed.bestTime
    }

    fun logout() {
        authToken = ""
    }
}
