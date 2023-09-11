@file:OptIn(ExperimentalPermissionsApi::class)

package com.david.tot.ui.cameraxtutorial.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.david.tot.ui.cameraxtutorial.Main2Activity
import com.david.tot.ui.cameraxtutorial.ui.camera.CameraScreen
import com.david.tot.ui.cameraxtutorial.ui.no_permission.NoPermissionScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
//import de.yanneckreiss.cameraxtutorial.ui.camera.CameraScreen
//import de.yanneckreiss.cameraxtutorial.ui.no_permission.NoPermissionScreen

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun MainScreen(mContext: Main2Activity) {

    val cameraPermissionState: PermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    //val cameraPermissionState: PermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    MainContent(
        mContext=mContext,
        hasPermission = cameraPermissionState.status.isGranted,
        onRequestPermission = cameraPermissionState::launchPermissionRequest
    )
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
private fun MainContent(
    mContext:Main2Activity,
    hasPermission: Boolean,
    onRequestPermission: () -> Unit
) {

    if (hasPermission) {
        CameraScreen(mContext)
    } else {
        NoPermissionScreen(onRequestPermission)
    }
}


@RequiresApi(Build.VERSION_CODES.R)
@Composable
private fun Preview_MainContent(m2Context:Main2Activity) {
    MainContent(
        mContext=m2Context,
        hasPermission = true,
        onRequestPermission = {}
    )
}
