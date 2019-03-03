package com.github.mbarrben.moviedb.commons

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> LayoutInflater.inflateBinding(
    @LayoutRes layoutRes: Int,
    parent: ViewGroup?,
    attachToParent: Boolean = false
): T = DataBindingUtil.inflate(this, layoutRes, parent, attachToParent)

fun <T : ViewDataBinding> Context.inflateBinding(
    @LayoutRes layoutRes: Int,
    parent: ViewGroup?,
    attachToParent: Boolean = false
): T = LayoutInflater.from(this).inflateBinding(layoutRes, parent, attachToParent)

fun <T : ViewDataBinding> ViewGroup.inflateBinding(@LayoutRes layoutRes: Int, attachToParent: Boolean = true): T =
    context.inflateBinding(layoutRes, this, attachToParent)