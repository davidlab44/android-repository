package com.david.tot.ui.cameraxtutorial.ui.camera

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import com.david.tot.domain.model.Reportable
import com.david.tot.domain.model.Sync
import com.david.tot.domain.reportable.AddOneReportableToLocalDatabaseUseCase
import com.david.tot.domain.reportable.GetAllReportablesFromLocalDatabaseUseCase
import com.david.tot.domain.sync.AddOneSyncFromLocalDatabaseUseCase
import com.david.tot.domain.sync.GetAllSyncFromLocalDatabaseUseCase
import com.david.tot.ui.cameraxtutorial.Main2Activity
import com.david.tot.util.Dates
import com.david.tot.util.ReportableSaver
import com.david.tot.util.SyncSaver
import com.yeslab.fastprefs.FastPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val addOneSyncFromLocalDatabaseUseCase: AddOneSyncFromLocalDatabaseUseCase,
    private val addOneReportableToLocalDatabaseUseCase: AddOneReportableToLocalDatabaseUseCase,
    private val getAllReportablesFromLocalDatabaseUseCase: GetAllReportablesFromLocalDatabaseUseCase,
    private val getAllSyncFromLocalDatabaseUseCase: GetAllSyncFromLocalDatabaseUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CameraState())
    lateinit var mContext:Main2Activity
    val state = _state.asStateFlow()


    @RequiresApi(Build.VERSION_CODES.R)
    fun onPhotoCaptured2(context:Main2Activity) {
        mContext=context
        CoroutineScope(Dispatchers.IO).launch {

        }
    }


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

                //SHARED PREFERENCES
                //val prefs = FastPrefs(mContext)
                //prefs.setString("Reportable",Dates().dateAsInt().toString())
                //val value = prefs.getString("Reportable","defaultValue")
                //Log.e("TG",""+value)

                val reportable = Reportable(generatedId = time, photo= photoUrl,description = "")
                val reportableToSave = addOneReportableToLocalDatabaseUseCase.invoke(reportable)
                val reportableList = getAllReportablesFromLocalDatabaseUseCase.invoke()
                Log.e("TG",""+reportableList.size)
                //val reportableToSave = ReportableSaver().addOneReportableToLocalDatabase(reportable)
                val sync = Sync(objectId=Dates().dateAsInt(),dataType="Reportable", createdAt=Dates().geDateAsString())
                //val syncToSave = SyncSaver().addOneSyncToLOcalDatabase(sync)
                val syncToSave = addOneSyncFromLocalDatabaseUseCase.invoke(sync)
                val syncList = getAllSyncFromLocalDatabaseUseCase.invoke()
                Log.e("TG",""+syncList.size)
                mContext.finish()
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
