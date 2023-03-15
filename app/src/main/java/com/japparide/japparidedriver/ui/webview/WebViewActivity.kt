package com.japparide.japparidedriver.ui.webview

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.webkit.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import com.japparide.japparidedriver.R
import com.japparide.japparidedriver.databinding.ActivityWebViewBinding
import com.japparide.japparidedriver.ui.maps.MapsActivity
import com.japparide.japparidedriver.visible

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding
    var url = "https://app.japparide.com/driver/login"

    private val TAG = "url"

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide();
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        binding.webViewPrivacy.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String
            ): Boolean {

//                if(url.contains("geo:")) {
//                    Uri gmmIntentUri = Uri.parse(url);
//                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                    mapIntent.setPackage("com.google.android.apps.maps");
//                    if (mapIntent.resolveActivity(getPackageManager()) != null) {
//                        startActivity(mapIntent);
//                    }

                    if (url.contains("dir")){

                        val gmmIntentUri = Uri.parse(url)
                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                        mapIntent.setPackage("com.google.android.apps.maps")
                        if (mapIntent.resolveActivity(packageManager) != null){
                            startActivity(mapIntent)
                        }

                        return true
                    }

//                BZtnrn4NHD8ecK8


                view.loadUrl(url)

                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                binding.fingerPrintLoginProgress.visible(true)


            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                binding.fingerPrintLoginProgress.visible(false)
                Log.d(TAG, "onPageFinished: $url ")
                Toast.makeText(applicationContext, "$url", Toast.LENGTH_SHORT).show()

            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)

                binding.fingerPrintLoginProgress.visible(false)

            }


        }


        binding.refreshLayout.setOnRefreshListener {

            refreshPage()


        }

        binding.scrollViewLayout.setOnScrollChangeListener(object : View.OnScrollChangeListener {
            override fun onScrollChange(
                v: View?,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                if (scrollY > 5) {
                    binding.scrollViewLayout.setEnabled(false)
                } else {
                    binding.scrollViewLayout.setEnabled(true)
                }
            }
        })


        binding.webViewPrivacy.loadUrl(url)
        binding.webViewPrivacy.settings.javaScriptEnabled = true
        binding.webViewPrivacy.settings.allowContentAccess = true
        binding.webViewPrivacy.settings.domStorageEnabled = true
        binding.webViewPrivacy.settings.useWideViewPort = true


        binding.webViewPrivacy.setOnKeyListener { _, _, keyEvent ->
            if (keyEvent.keyCode == KeyEvent.KEYCODE_BACK && !binding.webViewPrivacy.canGoBack()) {
                false
            } else if (keyEvent.keyCode == KeyEvent.KEYCODE_BACK && keyEvent.action == MotionEvent.ACTION_UP) {
                binding.webViewPrivacy.goBack()
                true
            } else true
        }


        onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                if (binding.webViewPrivacy.canGoBack()){
                    binding.webViewPrivacy.goBack()
                }else{
//
                    createDialog()
//                    alertDialog?.show()

                }
            }
        })
    }



    fun createDialog() {

        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
        finish()


    }



    fun refreshPage(){

        binding.webViewPrivacy.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String
            ): Boolean {
                view.loadUrl(url)

                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                binding.fingerPrintLoginProgress.visible(true)


            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                binding.fingerPrintLoginProgress.visible(false)
                binding.refreshLayout.isRefreshing = false


            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)

//                val directions = SignUpFragmentDirections.
//                actionSignUpFragmentToErrorScreenFragment("four")
//                findNavController().navigate(directions)
            }

            override fun onReceivedHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse?
            ) {
                super.onReceivedHttpError(view, request, errorResponse)

//                val directions = SignUpFragmentDirections.
//                actionSignUpFragmentToErrorScreenFragment("four")
//                findNavController().navigate(directions)

            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                super.onReceivedSslError(view, handler, error)

                handler?.cancel()
            }
        }



        binding.webViewPrivacy.loadUrl(binding.webViewPrivacy.url.toString())
        binding.webViewPrivacy.settings.javaScriptEnabled = true
        binding.webViewPrivacy.settings.allowContentAccess = true
        binding.webViewPrivacy.settings.domStorageEnabled = true
        binding.webViewPrivacy.settings.useWideViewPort = true
        binding.webViewPrivacy.settings.setMediaPlaybackRequiresUserGesture(false);
    }


}