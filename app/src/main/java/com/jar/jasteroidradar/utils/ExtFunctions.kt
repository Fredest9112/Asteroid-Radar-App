package com.jar.jasteroidradar.utils

fun String.normalize(): String {
    return this.replace("\n", " ")       // Replace newlines with spaces
        .replace(Regex("\\s+"), " ")     // Replace multiple spaces with a single space
        .trim()
}