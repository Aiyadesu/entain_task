package com.richard.domain.racing.model

data class RacesDomainModel(
    var allRaces: List<RacingDomainModel>,
    var horseRaces: List<RacingDomainModel>,
    var harnessRaces: List<RacingDomainModel>,
    var greyhoundRaces: List<RacingDomainModel>,
)
