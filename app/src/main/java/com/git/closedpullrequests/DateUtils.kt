package com.git.closedpullrequests

import java.text.SimpleDateFormat
import java.util.Locale

fun formatDate(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.getDefault())
    val date = inputFormat.parse(dateString)
    return outputFormat.format(date)
}