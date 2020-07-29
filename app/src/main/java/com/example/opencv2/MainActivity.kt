package com.example.opencv2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

class MainActivity : AppCompatActivity() {
    init{
        System.loadLibrary("opencv_java4")
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

        val img= Mat()
    }

    public fun captureImage(view: View){
        val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 30)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==30 && resultCode== Activity.RESULT_OK){
            val bitmapImgResult=data?.extras?.get("data") as Bitmap

            val img=Mat()
            val gray=Mat()
            Utils.bitmapToMat(bitmapImgResult, img)

            Imgproc.cvtColor(img, gray, Imgproc.COLOR_BGR2GRAY)
            val bitmapDisplay= Bitmap.createBitmap(gray.cols(), gray.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(gray, bitmapDisplay);
            val imageView=findViewById<ImageView>(R.id.imageView)
            imageView.setImageBitmap(bitmapDisplay)
            imageView.visibility=View.VISIBLE


        }
    }


}