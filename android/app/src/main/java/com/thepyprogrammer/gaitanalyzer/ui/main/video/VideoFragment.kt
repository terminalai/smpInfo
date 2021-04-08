package com.thepyprogrammer.gaitanalyzer.ui.main.video

import android.app.PictureInPictureParams
import android.graphics.PixelFormat
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.util.Rational
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentVideoBinding
import com.thepyprogrammer.gaitanalyzer.model.utils.getTTS
import com.thepyprogrammer.gaitanalyzer.model.utils.io.getUriFromRaw
import com.thepyprogrammer.gaitanalyzer.model.view.web.GitHubWebViewClient
import com.thepyprogrammer.gaitanalyzer.model.view.web.WebAppInterface
import com.thepyprogrammer.gaitanalyzer.model.view.web.WebBrowserClient
import java.util.*


class VideoFragment : Fragment() {

    private lateinit var binding: FragmentVideoBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentVideoBinding.inflate(inflater, container, false)
        val view = binding.root

        val sc = Scanner(resources.openRawResource(R.raw.github_markdown))
        val scBuilder = StringBuilder()
        while (sc.hasNext()) {
            scBuilder.append(sc.nextLine() + "\n")
        }

        with(binding.hub) {
            webViewClient = GitHubWebViewClient(requireActivity())
            settings.javaScriptEnabled = true
            addJavascriptInterface(WebAppInterface(requireActivity()), "Android")
            webChromeClient = WebBrowserClient()
            loadUrl("file:///android_asset/www/about/index.html")
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onStart() {
        super.onStart()

        conFigVideo()
    }

    private fun conFigVideo() {
        requireActivity().window.setFormat(PixelFormat.TRANSLUCENT)
        binding.videoView.setVideoURI(getUriFromRaw(requireContext(), R.raw.gaitmonitoring)) // placeholder

        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mediaController)

        binding.videoView.setOnPreparedListener {
            Log.i(
                    "VIDEO",
                    "Duration = " + it.duration
            )
        }

        binding.videoView.setPlayPauseListener {
            Log.d("VIDEO", if (it) "Play!" else "Pause!")
        }

        binding.videoView.start()


        binding.ttsButton.setOnClickListener {
            TextToSpeech(requireContext(), null).getTTS(requireContext(), text)
        }

        binding.pip.setOnClickListener { enterPIP() }
    }

    override fun onPictureInPictureModeChanged(isPictureinPictureMode: Boolean) {
        super.onPictureInPictureModeChanged(isPictureinPictureMode)

        if (isPictureinPictureMode) {
            requireActivity().actionBar?.hide()
            binding.buttonLayout.visibility = View.GONE
            binding.scrollingView.visibility = View.GONE
        } else {
            requireActivity().actionBar?.show()
            binding.buttonLayout.visibility = View.VISIBLE
            binding.scrollingView.visibility = View.VISIBLE
        }

    }

    private fun enterPIP() {
        val rational = Rational(binding.videoView.width, binding.videoView.height)
        val params = PictureInPictureParams.Builder().setAspectRatio(rational).build()
        binding.videoView.setMediaController(null)
        requireActivity().enterPictureInPictureMode(params)
    }

    companion object {
        const val text =
                "Parkinson’s disease, or PD is a neurodegenerative disorder that affects the dopamine-producing neurons in the substantia nigra, an area of the brain, leading to shaking, stiffness and difficulty walking. Parkinson’s patients frequently exhibit the debilitating condition freezing of gait, or FOG, which is when patients cannot move their feet forward despite the intention to walk. While the feet remain in place, the torso still has forward momentum, making falls very common. At the start, FOG can be triggered by stress, tight spaces or a sudden change in direction. As the disease progresses, this happens more frequently, a fact extremely detrimental to the patient’s health and mental well-being. This study aims to compare all the ways of classifying FOG in PD patients and determine the best parameter to utilise while creating an algorithm for data analysis. It also aims to compare multiple machine learning models based on acceleration data from accelerometers placed on the thigh. Public datasets of PD patients will be analysed to extract the motion pattern of PD patients. A Freeze Index value is postulated and used to predict FOG based on these parameters. An algorithm was then developed to identify the most suitable parameter for the classification of FOG in PD patients. Multiple machine learning models were then compared based on acceleration data from accelerometers placed on the thigh. After analyzing, the most suitable parameters for classification are freezeY and freezeZ based on the acceleration data in the public datasets and the best model is the Linear Kernel model in terms of sensitivity. Furthermore, a prototype has been created using an Arduino Nano 33 BLE board. It can be implemented to test the performance of the identified most suitable parameters. This system has now been connected to this Android Application such that notifications can be sent to the caregiver’s phone to alert them to a fall."
    }
}