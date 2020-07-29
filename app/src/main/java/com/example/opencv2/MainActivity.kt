package com.example.opencv2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.opencv2.imageprocessing.ImagePrepocessing
import kotlinx.android.synthetic.main.activity_main.*
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

class MainActivity : AppCompatActivity() {
    private val cameraReqCode=30

    init{
        System.loadLibrary("opencv_java4")
        System.loadLibrary("dummy-lib")
        if(OpenCVLoader.initDebug()){
            Log.d("MainActivity", "OpenCV is triggered or connected successfully")
        }
        else{
            Log.d("MainActivity", "OpenCV not Loaded, libraries failed to initialize")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun captureImage(view: View){
        val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, cameraReqCode)

    }

    fun testImageLoad(view:View){
        val bitmapImgResult=BitmapFactory.decodeResource(resources, R.drawable.example)
        val bitmapDisplay=ImagePrepocessing(bitmapImgResult).extractDigit()
        imageView.setImageBitmap(bitmapDisplay)
        imageView.visibility=View.VISIBLE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==cameraReqCode && resultCode== Activity.RESULT_OK){
            val bitmapImgResult=data?.extras?.get("data") as Bitmap

            val bitmapDisplay=ImagePrepocessing(bitmapImgResult).extractDigit()
            imageView.setImageBitmap(bitmapDisplay)
            imageView.visibility=View.VISIBLE



        }
    }


}