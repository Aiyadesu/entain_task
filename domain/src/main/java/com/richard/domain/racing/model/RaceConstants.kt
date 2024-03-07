package com.richard.domain.racing.model

object RaceConstants {
    // Business Logic
    const val COUNTDOWN_TIMER_FINISH_TIME = -60
    const val POLL_RATE_IN_MILLISECONDS = 20000L

    // API Constraints
    const val START_INDEX = 0
    const val MAXIMUM_NUMBER_OF_RACES_DISPLAYED = 5
    const val RACES_METHOD_QUERY_PARAMETER = "nextraces"
    const val RACES_COUNT_QUERY_PARAMETER = 40

    // Category IDs
    const val GREYHOUND_RACING_CATEGORY_ID = "9daef0d7-bf3c-4f50-921d-8e818c60fe61"
    const val HARNESS_RACING_CATEGORY_ID = "161d9be2-e909-4326-8c2c-35ed71fb460b"
    const val HORSE_RACING_CATEGORY_ID = "4a2788f8-e825-4d36-9894-efd4baf1cfae"
}