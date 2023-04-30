package com.rajit.activitylifecycleexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.SavedStateHandle
import com.rajit.activitylifecycleexample.databinding.ActivityMainBinding
import com.rajit.activitylifecycleexample.util.UiUtils.showShortToast
import com.rajit.activitylifecycleexample.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    // onCreate is called on First-time Launch or when activity is destroyed
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showShortToast(applicationContext, "Step 1: Application Launched, onCreate() Called")

        mainViewModel.counter.observe(this) {
            if(it == null)
                showShortToast(applicationContext, "counter is null")
            binding.counterTxt.text = (it ?: 0).toString()
        }

        binding.addCounterFab.setOnClickListener { mainViewModel.setCounter() }

    }

    // onStart is called when the activity is visited after being sent to background
    // also when it flows from onCreate()
    override fun onStart() {
        super.onStart()
        showShortToast(applicationContext, "Step 2: onStart() Called")
    }

    // onResume is called when the user visits activity again after:
    // onPause() or onStop() was called
    override fun onResume() {
        super.onResume()
        showShortToast(applicationContext, "Step 3: onResume Called")
    }

    // onPause() is called when another app is visible in the foreground over the activity
    override fun onPause() {
        super.onPause()
        showShortToast(applicationContext, "Step 4: onPause() Called")
    }

    // onStop() is called when the user leaves the app and goes to another app
    // But the app remains in the background
    override fun onStop() {
        super.onStop()
        mainViewModel.setCounter()
        showShortToast(applicationContext, "Step 5: onStop() Called")
    }

    // onRestart() is called every time when the user visits the app after onStop() was called
    override fun onRestart() {
        super.onRestart()
        showShortToast(applicationContext, "Step 6: onRestart() Called")
    }

    // onDestroy is called when the user exits the app completely
    // or finish() is called
    // or the user removes the app from the background
    override fun onDestroy() {
        super.onDestroy()
        showShortToast(applicationContext, "Step 7: onDestroy Called")
    }

}