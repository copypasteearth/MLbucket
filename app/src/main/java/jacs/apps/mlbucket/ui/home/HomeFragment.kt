package jacs.apps.mlbucket.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import jacs.apps.mlbucket.R
import jacs.apps.mlbucket.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var fancyAboutPage = binding.fancyAboutPage;
        fancyAboutPage.setCover(R.drawable.ai); //Pass your cover image
        fancyAboutPage.setName("John P. Rowan III");
        fancyAboutPage.setDescription("Android Developer for jacs@pps. Loves coding and keeping up with Android OS");
        fancyAboutPage.setAppIcon(R.drawable.icon); //Pass your app icon image
        fancyAboutPage.setAppName("MLbucket");
        fancyAboutPage.setVersionNameAsAppSubTitle("1.0.0");
        fancyAboutPage.setAppDescription("MLbucket is an app where I am gathering a bunch of different Machine Learning models and running them inside the app.");
        fancyAboutPage.findViewById<TextView>(R.id.appdescription).setTextColor(Color.BLACK)
        fancyAboutPage.addEmailLink("copypasteearth@gmail.com");     //Add your email id
        fancyAboutPage.addFacebookLink("https://www.facebook.com/copypasteearth");  //Add your facebook address url
        fancyAboutPage.addTwitterLink("https://twitter.com/Copypasteearth");
        fancyAboutPage.addLinkedinLink("https://www.linkedin.com/in/john-rowan-876115119/");
        fancyAboutPage.addGitHubLink("https://github.com/copypasteearth");


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}