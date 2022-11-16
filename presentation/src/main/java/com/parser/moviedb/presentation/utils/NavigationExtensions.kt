package com.parser.moviedb.presentation.utils

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.parser.moviedb.presentation.R

fun AppCompatActivity.nextActivity(
    secondActivity: Class<*>,
    extras: Bundle = Bundle(),
    finishCurrent: Boolean = true,
    animate: Boolean = true
) {
    val intent = Intent(this, secondActivity)
    intent.putExtras(extras)
    if (!animate) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
    }
    if (finishCurrent)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK and Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_CLEAR_TOP)
    startActivity(intent, null)
    if (finishCurrent) {
        finishAffinity()
        finishAndRemoveTask()
        if (animate) {
            overridePendingTransition(0, 0)
        }
    }
}

fun AppCompatActivity.addFragmentToActivity(
    fragment: androidx.fragment.app.Fragment,
    frameId: Int,
    tag: String,
    addToBackStack: Boolean
) {
    supportFragmentManager.transact {
        setCustomAnimations(
            R.animator.transition_slide_in_from_right, R.animator.transition_fade_out,
            R.animator.transition_fade_in, R.animator.transition_slide_out_to_right
        )
        add(frameId, fragment, tag)
        if (addToBackStack)
            addToBackStack(tag)
    }
}

fun AppCompatActivity.replaceFragmentInActivity(
    fragment: androidx.fragment.app.Fragment,
    frameId: Int,
    tag: String,
    addToBackStack: Boolean
) {
    supportFragmentManager.transact {
        setCustomAnimations(
            R.animator.transition_slide_in_from_right, R.animator.transition_fade_out,
            R.animator.transition_fade_in, R.animator.transition_slide_out_to_right
        )
        replace(frameId, fragment, tag)
        if (addToBackStack)
            addToBackStack(tag)
    }
}

private inline fun androidx.fragment.app.FragmentManager.transact(
    action: androidx.fragment.app
    .FragmentTransaction.() -> Unit
) {
    kotlin.runCatching {
        beginTransaction().apply {
            action()
        }.commit()
        executePendingTransactions()
    }.getOrElse {
        it.printStackTrace()
        Log.w("Fragment", "Error in fragment transaction")
    }
}