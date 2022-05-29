package raq.lop.io.marvelkotlinmvvm.data.local

import com.google.gson.Gson
import raq.lop.io.marvelkotlinmvvm.data.model.ThumbnailModel

class MarvelConverters {
    fun fromThumbnails(thumbnailModel: ThumbnailModel): String = Gson().toJson(thumbnailModel)
    fun toThumbnail(thumbnailModel: String): ThumbnailModel = Gson().fromJson(thumbnailModel, ThumbnailModel::class.java)
}