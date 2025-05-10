package com.example.dopatox.ui.home.fragment

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import com.example.dopatox.R
import com.example.dopatox.databinding.FragmentSettingsBinding
import com.example.dopatox.ui.home.profile.ProfileActivity
import androidx.core.graphics.drawable.toDrawable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class SettingsFragment : Fragment() {
    lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bar.title.text = getString(R.string.settings)
        initListener()
    }

    private fun initListener() {
        binding.accountItem.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            intent.putExtra("tab", 0)
            startActivity(intent)
        }
        binding.badgesItem.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            intent.putExtra("tab", 1)
            startActivity(intent)
        }
        binding.notificationBtn.setOnClickListener {
            // TODO write notification logic
        }
        binding.themeBtn.setOnClickListener {
            // TODO write theme logic
        }
        binding.shareItem.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, Constants.shareText)
            startActivity(Intent.createChooser(intent, getString(R.string.share_with_friends)))
        }
        binding.privacyItem.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.privacy_policy_layout)
            val webView = dialog.findViewById<WebView>(R.id.webView)
            webView.webViewClient = WebViewClient()
            // TODO change link with the actual one
            webView.loadUrl("https://www.dopatox.site")
            dialog.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            dialog.show()
        }
        binding.termsItem.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.privacy_policy_layout)
            val title = dialog.findViewById<TextView>(R.id.title)
            val webView = dialog.findViewById<WebView>(R.id.webView)
            title.text = getString(R.string.terms_of_services)
            webView.webViewClient = WebViewClient()
            // TODO change link with the actual one
            webView.loadUrl("https://www.dopatox.site")
            dialog.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            dialog.show()
        }
    }
}