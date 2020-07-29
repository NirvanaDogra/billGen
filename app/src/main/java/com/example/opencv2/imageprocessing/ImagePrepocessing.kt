package com.example.opencv2.imageprocessing

import android.graphics.Bitmap
import androidx.core.graphics.createBitmap
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.core.MatOfPoint
import org.opencv.core.Scalar
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc

class ImagePrepocessing(private val bitmap: Bitmap){

    fun toGray():Bitmap{

        val img=Mat()
        val gray=Mat()
        Utils.bitmapToMat(bitmap, img)
        Imgproc.cvtColor(img, gray, Imgproc.COLOR_BGR2GRAY)
        val bitmapImg= Bitmap.createBitmap(gray.cols(), gray.rows(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(gray, bitmapImg)
        return bitmapImg
    }

    fun extractDigit():Bitmap{
        val img=Mat()
        val grayImg=Mat()
        val cannyImg=Mat()
        val blurImg=Mat()
        val hierarchy=Mat()
        Utils.bitmapToMat(bitmap, img)
        Imgproc.cvtColor(img, grayImg, Imgproc.COLOR_BGR2GRAY)
        Imgproc.GaussianBlur(grayImg, blurImg, Size(5.0, 5.0), 0.0)
        Imgproc.Canny(grayImg, cannyImg, 50.0, 150.0)

        val cannyEdgeList=ArrayList<MatOfPoint>()
        Imgproc.findContours(cannyImg, cannyEdgeList, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE)
        Imgproc.drawContours(grayImg, cannyEdgeList, -1, Scalar(255.0, 255.0, 255.0), 3)
        val bitmapImg= Bitmap.createBitmap(grayImg.cols(), grayImg.rows(), Bitmap.Config.ARGB_8888)

        Utils.matToBitmap(grayImg, bitmapImg)
        return bitmapImg

    }

}