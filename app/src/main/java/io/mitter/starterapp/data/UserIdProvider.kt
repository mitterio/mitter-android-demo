package io.mitter.starterapp.data

object UserIdProvider {
    fun getUserId(username: String): String = when (username.toLowerCase()) {
        "sam" -> "5cfe3da1-4467-49b8-8325-3e85cec31c5a"
        "jason" -> "8ed92f3c-0696-4513-a842-085e3cee589e"
        "katie" -> "cb02bc00-979e-4db2-8625-116178c4ad95"
        else -> ""
    }
}
