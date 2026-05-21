package gr.sppzglou.sports.presentation.utils

fun Long.toCountdownText(): String {
    var seconds = this / 1000

    val months = seconds / (60 * 60 * 24 * 30)
    seconds %= (60 * 60 * 24 * 30)

    val weeks = seconds / (60 * 60 * 24 * 7)
    seconds %= (60 * 60 * 24 * 7)

    val days = seconds / (60 * 60 * 24)
    seconds %= (60 * 60 * 24)

    val hours = seconds / (60 * 60)
    seconds %= (60 * 60)

    val minutes = seconds / 60
    seconds %= 60

    return buildList {
        if (months > 0) add("${months}mo")
        if (weeks > 0) add("${weeks}w")
        if (days > 0) add("${days}d")
        if (hours > 0) add("${hours}h")
        if (minutes > 0) add("${minutes}m")

        add("${seconds}s")

    }.joinToString(" ")
}