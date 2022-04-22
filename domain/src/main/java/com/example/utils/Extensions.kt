package com.example.utils


fun String.mapUrl(): String {
    return "${BASE_URL}${this}"
}