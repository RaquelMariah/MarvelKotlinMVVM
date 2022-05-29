package raq.lop.io.marvelkotlinmvvm.data.model.character

import com.google.gson.annotations.SerializedName
import raq.lop.io.marvelkotlinmvvm.data.model.ThumbnailModel
import java.io.Serializable

data class CharacterModel (

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailModel,
    @SerializedName("description")
    val description: String = ""


): Serializable