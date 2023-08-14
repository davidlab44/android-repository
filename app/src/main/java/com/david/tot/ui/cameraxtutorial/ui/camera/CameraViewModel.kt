package com.david.tot.ui.cameraxtutorial.ui.camera

import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import com.david.tot.data.database.dao.ReportableDao
import com.david.tot.domain.model.Reportable
import com.david.tot.domain.reportable.AddOneReportableToLocalDatabaseUseCase
import com.david.tot.util.Converter
import com.david.tot.util.ReportableSaver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date

class CameraViewModel : ViewModel() {

    private val _state = MutableStateFlow(CameraState())
    val state = _state.asStateFlow()

    fun onPhotoCaptured(bitmap: Bitmap) {
        CoroutineScope(Dispatchers.IO).launch {
            // TODO: Process your photo, for example store it in the MediaStore
            // here we only do a dummy showcase implementation
            //updateCapturedPhotoState(bitmap)
            //saveMediaToStorage(bitmap)
            val convertidoABLob = Converter().fromBitmap(bitmap)
            val reportable = Reportable(0, photo = convertidoABLob, description = "bla bla bla..")

            val reportableDao = ReportableSaver().addOneReportableToLocalDatabase(reportable)

            Log.e("TAG4", "" + reportable)



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


    /*
    fun storeImage(image: Bitmap): Uri? {
        var pictureFile: File = File(Environment.getExternalStorageDirectory().path + "/Folder")
        val timeStamp = SimpleDateFormat("yyyy-MM-dd_HHmm").format(Date())
        val name = "$timeStamp.jpg"
        pictureFile = File(pictureFile.path + File.separator + name)



        try {
            val fos = FileOutputStream(pictureFile)
            image.compress(Bitmap.CompressFormat.JPEG, 90, fos)
            fos.close()
            return pictureFile.toUri()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
*/
    /*
    fun storeImage(image: Bitmap): Uri? {
        var pictureFile: File = File(Environment.getExternalStorageDirectory().path + "/Folder")
        val timeStamp = SimpleDateFormat("yyyy-MM-dd_HHmm").format(Date())
        val name = "$timeStamp.jpg"
        pictureFile = File(pictureFile.path + File.separator + name)




        try {
            val fos = FileOutputStream(pictureFile)
            image.compress(Bitmap.CompressFormat.JPEG, 90, fos)
            fos.close()
            return pictureFile.toUri()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
    */



/*
    fun saveMediaToStorage(mcontext: Context, bitmap: Bitmap) {
        //Generating a file name
        val filename = "${System.currentTimeMillis()}.jpg"

        //Output stream
        var fos: OutputStream? = null

        //For devices running android >= Q
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //getting the contentResolver
            mcontext?.contentResolver?.also { resolver ->

                //Content resolver will process the contentvalues
                val contentValues = ContentValues().apply {

                    //putting file information in content values
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                //Inserting the contentValues to contentResolver and getting the Uri
                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                //Opening an outputstream with the Uri that we got
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            //These for devices running on android < Q
            //So I don't think an explanation is needed here
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }

        fos?.use {
            //Finally writing the bitmap to the output stream that we opened
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Log.e("tagtag","Saved to Photos")
            //context?.toast("")
        }
    }
    */

}
