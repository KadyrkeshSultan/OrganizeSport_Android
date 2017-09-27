package org.organizesport.android.view.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_sample.*

import org.jetbrains.anko.longToast
import org.organizesport.android.R

/**
 * This class refers to the 'SampleActivity' View, following the MVP architectural pattern.
 *
 * It is intended for test purposes only.
 *
 * @author psor1i
 * @since 1.0
 */
class SampleActivity : AppCompatActivity() {

    companion object {
        val TAG = "SampleActivity"
    }

    private val tvPlaceHolder: TextView? by lazy { tv_place_holder_activity_sample }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)


//        this.firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task: Task<AuthResult> ->
//            if (task.isSuccessful) {
//                //Registration OK
//                Log.d(TAG, "In-showLoginUi()")
//                val firebaseUser = this.firebaseAuth.currentUser!!

//            } else {
//                //Registration error
//            }
//        }

    }
}