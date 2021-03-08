package com.pocket.kumbhashree.extensions

import android.app.Activity
import android.content.Intent

fun Activity.startNewActivity(intent: Intent, isFinish: Boolean) {

    this.startActivity(intent)
    if (isFinish) finish()
}