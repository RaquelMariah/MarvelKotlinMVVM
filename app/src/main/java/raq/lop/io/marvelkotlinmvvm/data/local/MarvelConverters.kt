package raq.lop.io.marvelkotlinmvvm.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import raq.lop.io.marvelkotlinmvvm.data.model.ThumbnailModel

class MarvelConverters {
    @TypeConverter
    fun fromThumbnails(thumbnailModel: ThumbnailModel): String = Gson().toJson(thumbnailModel)
    @TypeConverter
    fun toThumbnail(thumbnailModel: String): ThumbnailModel = Gson().fromJson(thumbnailModel, ThumbnailModel::class.java)
}