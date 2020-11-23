package com.example.iv_test_hazirah.traits

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

interface StartActivity: SuperClassSleuth {

    fun start(src: Context, bundle: Bundle? = null, flags: Int? = null) {
        val clazz : Class<*> = getBaseClass()
        val intent = Intent(src, clazz)
        bundle?.let {
            intent.putExtras(it)
        }
        flags?.let {
            intent.flags = it or intent.flags
        }
        setupIntentIfNotStartingFromActivity(intent, src)
        src.startActivity(intent)
    }

    fun startIntent(src: Context, bundle: Bundle? = null, flags: Int? = null): Intent {
        val clazz : Class<*> = getBaseClass()
        val intent = Intent(src, clazz)
        bundle?.let {
            intent.putExtras(it)
        }
        flags?.let {
            intent.flags = it or intent.flags
        }
        setupIntentIfNotStartingFromActivity(intent, src)
        return intent
    }


    fun restart(src: Context, bundle: Bundle? = null, flags: Int? = null) {
        val clazz : Class<*> = getBaseClass()
        val intent = Intent(src, clazz)
        bundle?.let {
            intent.putExtras(it)
        }
        flags?.let {
            intent.flags = it or intent.flags
        }
        setupIntentIfNotStartingFromActivity(intent, src)
        src.startActivity(Intent.makeRestartActivityTask(intent.component))
    }

    private fun setupIntentIfNotStartingFromActivity(intent: Intent, startFrom: Context) {
        if (startFrom !is Activity) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or intent.flags
        }
    }

}
