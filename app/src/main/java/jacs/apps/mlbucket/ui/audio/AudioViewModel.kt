package jacs.apps.mlbucket.ui.audio

import android.R.attr.src
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel

import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer


class AudioViewModel(application: Application) : AndroidViewModel(application) {
    var byteBuffer : ByteBuffer? = null
    fun runModel(byteBuffer: ByteBuffer?){

    }
}