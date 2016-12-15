package com.github.mbarrben.moviedb.extensions

import com.github.mbarrben.moviedb.extensions.Timber.ifPlanted
import timber.log.Timber


object Timber {
  @JvmStatic inline fun ifPlanted(f: () -> Any) {
    if (Timber.treeCount() != 0) {
      f()
    }
  }

  @JvmStatic inline fun e(message: () -> String) = ifPlanted { Timber.e(message()) }

  @JvmStatic inline fun e(throwable: Throwable?, message: () -> String) = ifPlanted { Timber.e(throwable, message()) }

  @JvmStatic inline fun w(message: () -> String) = ifPlanted { Timber.w(message()) }

  @JvmStatic inline fun w(throwable: Throwable?, message: () -> String) = ifPlanted { Timber.w(throwable, message()) }

  @JvmStatic inline fun i(message: () -> String) = ifPlanted { Timber.i(message()) }

  @JvmStatic inline fun i(throwable: Throwable?, message: () -> String) = ifPlanted { Timber.i(throwable, message()) }

  @JvmStatic inline fun d(message: () -> String) = ifPlanted { Timber.d(message()) }

  @JvmStatic inline fun d(throwable: Throwable?, message: () -> String) = ifPlanted { Timber.d(throwable, message()) }

  @JvmStatic inline fun v(message: () -> String) = ifPlanted { Timber.v(message()) }

  @JvmStatic inline fun v(throwable: Throwable?, message: () -> String) = ifPlanted { Timber.v(throwable, message()) }

  @JvmStatic inline fun wtf(message: () -> String) = ifPlanted { Timber.wtf(message()) }

  @JvmStatic inline fun wtf(throwable: Throwable?, message: () -> String) = ifPlanted { Timber.wtf(throwable, message()) }

  @JvmStatic inline fun log(priority: Int, t: Throwable?, message: () -> String) = ifPlanted { Timber.log(priority, t, message()) }

  @JvmStatic inline fun log(priority: Int, message: () -> String) = ifPlanted { Timber.log(priority, message()) }

  @JvmStatic fun asTree(): Timber.Tree = Timber.asTree()

  @JvmStatic fun plant(tree: Timber.Tree) = Timber.plant(tree)

  @JvmStatic fun tag(tag: String): Timber.Tree = Timber.tag(tag)

  @JvmStatic fun uproot(tree: Timber.Tree) = Timber.uproot(tree)

  @JvmStatic fun uprootAll() = Timber.uprootAll()
}

inline fun Timber.Tree.e(message: () -> String) = ifPlanted { Timber.e(message()) }

inline fun Timber.Tree.e(throwable: Throwable?, message: () -> String) = ifPlanted { Timber.e(throwable, message()) }

inline fun Timber.Tree.w(message: () -> String) = ifPlanted { Timber.w(message()) }

inline fun Timber.Tree.w(throwable: Throwable?, message: () -> String) = ifPlanted { Timber.w(throwable, message()) }

inline fun Timber.Tree.i(message: () -> String) = ifPlanted { Timber.i(message()) }

inline fun Timber.Tree.i(throwable: Throwable?, message: () -> String) = ifPlanted { Timber.i(throwable, message()) }

inline fun Timber.Tree.d(message: () -> String) = ifPlanted { Timber.d(message()) }

inline fun Timber.Tree.d(throwable: Throwable?, message: () -> String) = ifPlanted { Timber.d(throwable, message()) }

inline fun Timber.Tree.v(message: () -> String) = ifPlanted { Timber.v(message()) }

inline fun Timber.Tree.v(throwable: Throwable?, message: () -> String) = ifPlanted { Timber.v(throwable, message()) }

inline fun Timber.Tree.wtf(message: () -> String) = ifPlanted { Timber.wtf(message()) }

inline fun Timber.Tree.wtf(throwable: Throwable?, message: () -> String) = ifPlanted { Timber.wtf(throwable, message()) }

inline fun Timber.Tree.log(priority: Int, t: Throwable?, message: () -> String) = ifPlanted { Timber.log(priority, t, message()) }

inline fun Timber.Tree.log(priority: Int, message: () -> String) = ifPlanted { Timber.log(priority, message()) }