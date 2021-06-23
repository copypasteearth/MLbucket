package jacs.apps.mlbucket.ui.image_classification

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import jacs.apps.mlbucket.ml.LiteModelAiyVisionClassifierBirdsV13
import jacs.apps.mlbucket.ml.LiteModelAiyVisionClassifierFoodV11
import jacs.apps.mlbucket.ml.LiteModelObjectDetectionMobileObjectLabelerV11
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.label.Category

class BirdClassificationViewModel(application: Application) : AndroidViewModel(application) {
    var imageUri: Uri? = null
    var probabilities : ArrayList<Category> = ArrayList<Category>()
    var position = 0
    fun modelResults(context: Context){

        when(position){
            0 -> birds(context)
            1 -> food(context)
            2 -> objects(context)
        }



    }
    fun birds(context: Context){
        var bitmap : Bitmap
        var model = LiteModelAiyVisionClassifierBirdsV13.newInstance(getApplication())
        if(Build.VERSION.SDK_INT < 28) {
            bitmap = MediaStore.Images.Media.getBitmap(
                context.contentResolver,
                imageUri
            )
            bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)

        } else {
            val source = ImageDecoder.createSource(context.contentResolver, imageUri!!)
            bitmap = ImageDecoder.decodeBitmap(source)
            bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        }
        val image = TensorImage.fromBitmap(bitmap)

// Runs model inference and gets result.
        val outputs = model.process(image)
        var prob = outputs.probabilityAsCategoryList.apply {
            sortByDescending { it.score } // Sort with highest confidence first
        }.take(10) // take the top results
        probabilities = prob as ArrayList<Category>;



// Releases model resources if no longer used.
        model.close()
        Log.d("bitmap", probabilities[0].score.toString())
    }
    fun food(context: Context){
        var bitmap : Bitmap
        val model = LiteModelAiyVisionClassifierFoodV11.newInstance(context)
        if(Build.VERSION.SDK_INT < 28) {
            bitmap = MediaStore.Images.Media.getBitmap(
                context.contentResolver,
                imageUri
            )
            bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)

        } else {
            val source = ImageDecoder.createSource(context.contentResolver, imageUri!!)
            bitmap = ImageDecoder.decodeBitmap(source)
            bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        }
        val image = TensorImage.fromBitmap(bitmap)

// Runs model inference and gets result.
        val outputs = model.process(image)
        var prob = outputs.probabilityAsCategoryList.apply {
            sortByDescending { it.score } // Sort with highest confidence first
        }.take(10) // take the top results
        probabilities = prob as ArrayList<Category>;



// Releases model resources if no longer used.
        model.close()
        Log.d("bitmap", probabilities[0].score.toString())
    }
    fun objects(context: Context){
        var bitmap : Bitmap
        val model = LiteModelObjectDetectionMobileObjectLabelerV11.newInstance(context)
        if(Build.VERSION.SDK_INT < 28) {
            bitmap = MediaStore.Images.Media.getBitmap(
                context.contentResolver,
                imageUri
            )
            bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)

        } else {
            val source = ImageDecoder.createSource(context.contentResolver, imageUri!!)
            bitmap = ImageDecoder.decodeBitmap(source)
            bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        }
        val image = TensorImage.fromBitmap(bitmap)

// Runs model inference and gets result.
        val outputs = model.process(image)
        var prob = outputs.probabilityAsCategoryList.apply {
            sortByDescending { it.score } // Sort with highest confidence first
        }.take(10) // take the top results
        probabilities = prob as ArrayList<Category>;



// Releases model resources if no longer used.
        model.close()
        Log.d("bitmap", probabilities[0].score.toString())
    }

}