package com.rajit.activitylifecycleexample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rajit.activitylifecycleexample.databinding.ActivityMainBinding
import com.rajit.activitylifecycleexample.util.UiUtils.showShortToast
import com.rajit.activitylifecycleexample.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    /**
     * onCreate is() called on First-time Launch or when activity is destroyed and recreated
     * THIS METHOD IS WHERE WE DO:
     * 1. Variable Initialization, and
     * 2. View Creation
     *
     * NOTE: User still doesn't see the UI while the App's State is onCreate()
     */
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

    /**
     * onStart is called when the activity is visited after being sent to background
     * also when it flows from onCreate()
     *
     * Note: Here, User can see the Views but can't INTERACT with it.
     */
    override fun onStart() {
        super.onStart()
        showShortToast(applicationContext, "Step 2: onStart() Called")
    }

    /**
     * onResume is called when it flows from onStart()
     * onResume is called when the user visits activity again after:
     * onPause() or onStop() was called
     *
     * Note:
     * 1. Here, the Activity is visible to the User and the User can INTERACT with it.
     * 2. And it stays in this state, until another Activity comes in the Foreground (basically displayed over it).
     */
    override fun onResume() {
        super.onResume()
        showShortToast(applicationContext, "Step 3: onResume Called")
    }

    /**
     * IMPORTANT: TODO("If we need to release resources, we always do it in onPause() to ensure they are release every single time)
     *
     * onPause() is called when another Activity is visible in the foreground over the activity
     *
     * Note:
     * Not only any Activity but also Ui Elements like Dialog
     *
     * From here, if the User comes back to the Activity, then the flow jumps to onPause() -> onResume()
     * Basically, the onResume() is called from the onPause() state
     *
     */
    override fun onPause() {
        super.onPause()
        showShortToast(applicationContext, "Step 4: onPause() Called")
    }

    /**
     * onStop() is called when the user leaves the app and goes to another app
     * But the app remains in the background
     *
     * Lifecycle Flow: onStop() -> [If users comes back to the activity] onRestart()
     *
     * NOTE:
     * 1. onStop() is called when the activity is no longer visible at all
     *      [No dialogs or any element is visible to the user]
     *
     * 2. Difference between onPause() and onStop():
     *      - Both onPause() and onStop() are called one after another
     *      - If the user gets any notification dialog or any other dialog, that means the rest of the activity ui is still visible,
     *          in that case onPause() state is present
     *      - But if the onStop() is called, it is sure that none of the UI elements of the activity are visible to the User.
     */
    override fun onStop() {
        super.onStop()
        mainViewModel.setCounter()
        showShortToast(applicationContext, "Step 5: onStop() Called")
    }

    /**
     * onRestart() is called every time when the user visits the app after onStop() was called
     * Flow: onStop() -> onRestart() -> onStart()
     *
     * NOTE: onRestart() is called, when the user returned to the Activity, after it went to onStop() state.
     */
    override fun onRestart() {
        super.onRestart()
        showShortToast(applicationContext, "Step 6: onRestart() Called")
    }

    /**
     * onDestroy is called when:
     * 1. The user exits the app completely [By back press button ]
     * 2. finish() is called from the activity
     * 3. The user removes the app from the background
     * 4. The Android OS decides to kill your activity, to free up more memory
     * 5. When there is a GLOBAL CONFIGURATION CHANGE (such as Screen Rotation) where the screen needs
     *    to be recreated with a different UI or configuration.
     *    
     * NOTE: [SPECIAL CASE]:
     * 1. onStop() and onDestroy() might not get called in special cases, so we should keep that in mind
     * 2. And always release resources or save the state in onPause()
     */
    override fun onDestroy() {
        super.onDestroy()
        showShortToast(applicationContext, "Step 7: onDestroy Called")
    }

}