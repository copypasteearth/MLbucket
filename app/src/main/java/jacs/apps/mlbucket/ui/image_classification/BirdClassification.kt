package jacs.apps.mlbucket.ui.image_classification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import jacs.apps.mlbucket.databinding.ActivityBirdClassificationBinding

class BirdClassification : AppCompatActivity() {
    private lateinit var birdClassificationViewModel: BirdClassificationViewModel
    private lateinit var binding: ActivityBirdClassificationBinding
    private val pickImage = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var position = intent.getIntExtra("Position",0)
        Log.d("intent", position.toString())
        binding = ActivityBirdClassificationBinding.inflate(layoutInflater)
        birdClassificationViewModel = ViewModelProvider(this).get(BirdClassificationViewModel::class.java)
        setContentView(binding.root)
        birdClassificationViewModel.position = position
        binding.pickImage.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            birdClassificationViewModel.imageUri = data?.data
            binding.imageViewClassification.setImageURI(birdClassificationViewModel.imageUri)
            birdClassificationViewModel.modelResults(applicationContext)
            var imageAdapter = ResultAdapter(applicationContext, birdClassificationViewModel.probabilities)
            binding.probability.adapter = imageAdapter;

        }
    }
}