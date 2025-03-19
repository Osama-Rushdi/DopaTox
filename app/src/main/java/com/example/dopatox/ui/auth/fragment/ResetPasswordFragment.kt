package com.example.dopatox.ui.auth.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.example.dopatox.R
import com.example.dopatox.databinding.FragmentResetPasswordBinding
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle
import www.sanju.motiontoast.R as TR


class ResetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentResetPasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        resetPassword()
    }

    private fun resetPassword() {
        var clicked = false
        binding.signInBtn.setOnClickListener {
            if (clicked) {
                openGmail()
            } else {
                val email = binding.emailEditText.text.toString()
                val context = requireContext() as Activity
                MotionToast.darkToast(
                    context,
                    "Check your inbox!",
                    "A Link has been sent to $email",
                    MotionToastStyle.SUCCESS,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.SHORT_DURATION,
                    ResourcesCompat.getFont(context, TR.font.montserrat_bold)
                )
                binding.signInBtn.text = getString(R.string.Continue)
                clicked = true
            }
        }
    }

    private fun openGmail() {
        val context = requireContext() as Activity
        try {
            val intent = Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_APP_EMAIL)
                setPackage("com.google.android.gm")
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
        } catch (e: Exception) {
            MotionToast.darkToast(
                context,
                "Error",
                "Unable to open Gmail app. ${e.message}",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.SHORT_DURATION,
                ResourcesCompat.getFont(context, TR.font.montserrat_bold)
            )
        }
    }

}