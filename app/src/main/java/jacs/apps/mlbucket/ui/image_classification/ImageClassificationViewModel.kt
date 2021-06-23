package jacs.apps.mlbucket.ui.image_classification

import android.app.Application
import android.widget.ArrayAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import jacs.apps.mlbucket.R


class ImageClassificationViewModel(application: Application) : AndroidViewModel(application) {


    private val _text = MutableLiveData<String>().apply {
        value = "image classification"
    }
    val text: LiveData<String> = _text


    val imageClasses : ArrayList<String> = ArrayList(application.resources.getStringArray(R.array.image_class).asList())




}