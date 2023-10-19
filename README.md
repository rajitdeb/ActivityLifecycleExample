# ActivityLifecycleExample
Understanding in depth about Android Activity Lifecycle

# Activity Lifecycle Diagram
![Activity_Lifecycle.jpg](https://developer.android.com/guide/components/images/activity_lifecycle.png)
<br><br>Image Credits: <a href="https://developer.android.com">_Google Android Developer Documentation_</a>
# Explanation
The following are the methods that are present in the Activity Lifecycle:
| Method | Explanation |
| ------ | ----------- |
| **onCreate()** | `onCreate()` is called on First-time Launch or when activity is destroyed and recreated. This method is where we do:<ol><li>`Variable Initialization`, and</li><li>`View Creation`</li></ol>**NOTE: User still doesn't see the UI while the App's State is onCreate()**|
| **onStart()** | `onStart()` is called when the activity is visited after being sent to background also when it flows from onCreate() <br> Basically, `onCreate() -> onStart()` <br><br> **NOTE: Here, User can see the Views but can't INTERACT with it.** |
| **onResume()** | `onResume()` is called when: <ol><li>It flows from `onStart()`. Basically, `onStart() -> onResume()`</li><li>When the User visits activity again after `onPause()` or `onStop()` was called</li></ol> **NOTE:<ol><li>Here, the Activity is visible to the User and the User can INTERACT with it</li><li>And it stays in this state, until another Activity comes in the Foreground (basically displayed over it)</li></ol>**|
| **onPause()** | **IMPORTANT: <br>If we need to release resources, we always do it in onPause() to ensure they are release every single time.** <br><br> `onPause()` is called when another `Activity` is visible in the `foreground` over the activity <br><br> **NOTE: <br>Not only any Activity but also Ui Elements like Dialog <br> From here, if the `User` comes back to the `Activity`, then the flow jumps to `onPause() -> onResume()`** |
| **onStop()** | onStop() is called when the user leaves the app and goes to another app, but the app remains in the background <br> **Lifecycle Flow**: `onStop() -> [If users comes back to the activity] onRestart()` <br><br> **NOTE:<ol><li>`onStop()` is called when the `Activity` is no longer visible at all [No dialogs or any element is visible to the user]</li><li>Difference between onPause() and onStop(): <br><ul><li>Both onPause() and onStop() are called one after another</li><li>If the user gets any notification dialog or any other dialog, that means the rest of the activity ui is still visible, in that case onPause() state is present</li><li>But if the onStop() is called, it is sure that none of the UI elements of the activity are visible to the User</li></ul></li></ol>** |
| **onRestart()** | `onRestart()` is called every time when the user visits the app after onStop() was called <br> **Flow: `onStop() -> onRestart()` <br><br>NOTE: `onRestart()` is called, when the user returned to the `Activity`, after it went to `onStop()` state** |
| **onDestroy()** | `onDestroy()` is called when: <ol><li>The `User` exits the app completely [By back press button]</li><li>`finish()` is called from the `Activity`</li><li>The `User` removes the app from the `background`</li><li>The `Android OS` decides to kill your `Activity`, to free up more memory<li>When there is a `GLOBAL CONFIGURATION CHANGE` (such as Screen Rotation) where the screen needs to be recreated with a different UI or configuration</li></ol> **NOTE: [_SPECIAL CASE_]<ol><li>`onStop()` and `onDestroy()` might not get called in special cases, so we should keep that in mind</li><li>2. And always release resources or save the state in onPause()</li></ol>**

# Author
Rajit Deb
