package com.thepyprogrammer.gaitanalyzer.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentSplashBinding
import com.thepyprogrammer.gaitanalyzer.model.account.base.User
import com.thepyprogrammer.gaitanalyzer.model.firebase.FirebaseUtil
import java.io.File
import java.util.*


class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root

        val slideAnimation = AnimationUtils.loadAnimation(activity, R.anim.side_slide)
        binding.splashScreenImage.startAnimation(slideAnimation)

        val accountDetails = File(requireActivity().filesDir, "accountDetails.txt")
        if (accountDetails.exists()) {
            val sc = Scanner(accountDetails)
            if (sc.hasNext()) {
                val nric = sc.nextLine()
                if (nric != "null") {
                    val name = sc.nextLine()
                    val password = sc.nextLine()
                    val type = sc.nextLine()
                    FirebaseUtil.user = User(
                            name, password, type
                    )
                    Handler().postDelayed({
                        Navigation.findNavController(binding.splashScreenImage).navigate(R.id.nav_main)
                    }, SPLASH_TIME_OUT.toLong())
                    return view
                }
            }
        }

        Handler().postDelayed({
            Navigation.findNavController(binding.splashScreenImage).navigate(R.id.nav_identification)
        }, SPLASH_TIME_OUT.toLong())

        return view
    }

    override fun onResume() {
        super.onResume()
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onPause() {
        super.onPause()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    companion object {
        private const val SPLASH_TIME_OUT = 3000
    }
}