package com.bosch.composewithkotlin20.data.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Cafe(
    val id:String,
    val name:String,
    val image:String,
)

@Entity(tableName = "cafes")
data class CafeEntity(
    @PrimaryKey val id: String,
    val name: String,
    val image: String
)

fun Cafe.toEntity(): CafeEntity {
    return CafeEntity(
        id = this.id,
        name = this.name,
        image = this.image
    )
}

fun CafeEntity.toDomain(): Cafe {
    return Cafe(
        id = this.id,
        name = this.name,
        image = this.image
    )
}
