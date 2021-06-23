package jacs.apps.mlbucket.ui.image_classification

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import jacs.apps.mlbucket.R
import jacs.apps.mlbucket.databinding.FragmentImageClassificationBinding

class ImageClassificationFragment : Fragment() {

    private lateinit var imageClassificationViewModel: ImageClassificationViewModel
    private var _binding: FragmentImageClassificationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        imageClassificationViewModel =
                ViewModelProvider(this).get(ImageClassificationViewModel::class.java)
        Log.d("viewModel",imageClassificationViewModel.imageClasses.toString())
        _binding = FragmentImageClassificationBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var listAdapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), R.layout.image_listview_item, imageClassificationViewModel.imageClasses)
        binding.listview.adapter = listAdapter;
        binding.listview.setOnItemClickListener{parent,view,position,id ->
            Log.d("position", position.toString())
            val intent = Intent(requireContext(), BirdClassification::class.java).apply {
                putExtra("Position", position)
            }
            startActivity(intent)
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}