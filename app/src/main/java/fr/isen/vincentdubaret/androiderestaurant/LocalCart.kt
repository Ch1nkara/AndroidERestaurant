package fr.isen.vincentdubaret.androiderestaurant

data class LocalCart (
    var listOfOrder: ArrayList<AnOrder>
) {
    fun add(newOrder: AnOrder) {
        listOfOrder.add(newOrder)
    }
}
