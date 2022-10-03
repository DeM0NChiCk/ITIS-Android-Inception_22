package com.samples.itis_android_inception_22.presentation.base

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class BaseActivity(layout: Int) : AppCompatActivity(layout) {

    abstract val fragmentsContainerId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.registerFragmentLifecycleCallbacks(object :
            FragmentManager.FragmentLifecycleCallbacks() {

            override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
                super.onFragmentViewCreated(fm, f, v, savedInstanceState)
                if (f is BaseFragment) {
                    f.getToolbar()?.let { toolbar ->
                        this@BaseActivity.setSupportActionBar(toolbar)
                        supportActionBar?.setDisplayHomeAsUpEnabled(supportFragmentManager.backStackEntryCount > 0)
                    }
                }
            }
        }, false)
    }

    fun addFragment(fragment: Fragment, tag: String, detachCurrent: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()

        if (detachCurrent) {
            supportFragmentManager.findFragmentById(fragmentsContainerId)?.let { currentFragment ->
                transaction.detach(currentFragment)
            }
        }

        transaction
            .add(fragmentsContainerId, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    fun addWithRemove(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        supportFragmentManager.findFragmentById(fragmentsContainerId)?.let { currentFragment ->
            transaction.remove(currentFragment)
        }
        transaction
            .add(fragmentsContainerId, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(fragmentsContainerId, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                checkCurrentFragmentType()
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }


    private fun checkCurrentFragmentType(): Boolean {
        val currentFragment = supportFragmentManager.findFragmentById(fragmentsContainerId)
        return if (currentFragment is BaseFragment) {
            currentFragment.onFragmentBackPressed()
        } else {
            false
        }
    }
}