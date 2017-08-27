package org.organizesport.android.view.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
    }
}