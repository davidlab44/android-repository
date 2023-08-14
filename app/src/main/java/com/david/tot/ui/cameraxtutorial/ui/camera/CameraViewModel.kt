package com.david.tot.ui.cameraxtutorial.ui.camera

import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import com.david.tot.domain.model.Reportable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class CameraViewModel : ViewModel() {

    private val _state = MutableStateFlow(CameraState())
    val state = _state.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.R)
    fun onPhotoCaptured(bitmap: Bitmap) {
        CoroutineScope(Dispatchers.IO).launch {
            // TODO: Process your photo, for example store it in the MediaStore
            // here we only do a dummy showcase implementation
            //updateCapturedPhotoState(bitmap)
            //saveMediaToStorage(bitmap)
            //val convertidoABLob = Converter().fromBitmap(bitmap)
            //val reportable = Reportable(0, photo = convertidoABLob, description = "bla bla bla..")
            //val reportableDao = ReportableSaver().addOneReportableToLocalDatabase(reportable)
            updateProductImage(bitmap)
            //Log.e("TAG4", "" + reportable)
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun updateProductImage(bitmap: Bitmap){
        CoroutineScope(Dispatchers.IO).launch {
            val time = System.currentTimeMillis()
            val fileNameToSave = "APP_"+time+".png"
            val file = bitmapToFile(bitmap,fileNameToSave)
            Log.e("TAGFILE",""+file)
            if (file != null) {
                Log.e("TAG","file is not null")
                //save reportable-photo into db
                val photoUrl = "/storage/self/primary/Download" + File.separator + fileNameToSave
                val reportable = Reportable(photo= photoUrl,description = "")
            }else{
                Log.e("TAG","file is null")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun bitmapToFile(bitmap: Bitmap, fileNameToSave: String): File? {
        var file: File? = null
        var bitmapdata = byteArrayOf(0x2E, 0x38)
        return try {
            file = File("/storage/self/primary/Download" + File.separator + fileNameToSave)
            file.createNewFile()
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
            bitmapdata = bos.toByteArray()
            val fos = FileOutputStream(file)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            file
        }
    }

    fun onCapturedPhotoConsumed() {
        updateCapturedPhotoState(null)
    }

    private fun updateCapturedPhotoState(updatedPhoto: Bitmap?) {
        _state.value.capturedImage?.recycle()
        _state.value = _state.value.copy(capturedImage = updatedPhoto)
    }

    override fun onCleared() {
        _state.value.capturedImage?.recycle()
        super.onCleared()
    }

}
