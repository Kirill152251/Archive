package com.example.local

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewModelScope
import com.example.api.R
import com.example.utils.PROFILE_PHOTO_NAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


class InternalStorageManager (private val context: Context) {

    fun savePhotoToStorage(uri: Uri): Boolean {
        context.deleteFile(PROFILE_PHOTO_NAME)
        val bitmap = transformUriToBitmap(uri)
        return try {
            context.openFileOutput(PROFILE_PHOTO_NAME, Context.MODE_PRIVATE)
                .use { stream ->
                    if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 95, stream)) {
                        throw IOException("Couldn't save bitmap")
                    }
                }
            true
        } catch (e: IOException) {
            false
        }
    }

    suspend fun loadPhotoFromStorage(): List<Bitmap> {
        return withContext(Dispatchers.IO) {
            val files = context.filesDir.listFiles()
            files?.filter { it.name == PROFILE_PHOTO_NAME }?.map {
                val bytes = it.readBytes()
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                bitmap
            } ?: listOf()
        }
    }

    fun transformUriToBitmap(uri: Uri): Bitmap {
        return if (Build.VERSION.SDK_INT < 28) {
            MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, uri)
            ImageDecoder.decodeBitmap(source)
        }
    }
}