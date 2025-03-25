package com.example.dopatox.ui.auth.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.dopatox.R
import com.example.dopatox.databinding.FragmentLoginBinding
import com.example.dopatox.ui.home.MainActivity


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.signIn.setOnClickListener {
            startActivity(Intent(context, MainActivity::class.java))
        }
        binding.signUpBtn.setOnClickListener {
            findNavController().popBackStack(R.id.registerFragment2, true)
            findNavController().navigate(
                R.id.action_loginFragment_to_registerFragment2
            )
        }

        binding.forgetPassword.setOnClickListener {
            findNavController().navigate(
                R.id.action_loginFragment_to_resetPasswordFragment
            )
        }
    }
}