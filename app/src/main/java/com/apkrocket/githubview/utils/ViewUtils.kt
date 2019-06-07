package com.apkrocket.githubview.utils

import android.view.View

fun View.visible() { this.visibility = View.VISIBLE }

fun View.invisible() { this.visibility = View.INVISIBLE }

fun View.gone() { this.visibility = View.GONE }

fun View.goneIf(boolean: Boolean) { this.visibility = if (boolean) View.GONE else View.VISIBLE }

fun View.invisibleIf(boolean: Boolean) { this.visibility = if (boolean) View.INVISIBLE else View.VISIBLE }