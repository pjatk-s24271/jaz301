package pjatk.s24271.jaz301.api.objects

fun filterChampions(champions: List<ChampionDTO>?, keys: List<Int>): List<ChampionDTO>? =
    champions?.filter {
        keys.contains(it.key)
    }