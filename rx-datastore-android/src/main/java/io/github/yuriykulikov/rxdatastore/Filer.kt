/*
 * MIT License
 *
 * Copyright (c) 2020 Yuriy Kulikov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.github.yuriykulikov.rxdatastore

import android.content.Context
import okio.BufferedSink
import okio.BufferedSource
import okio.Okio
import java.io.FileOutputStream

/**
 * Provides [BufferedSource] and [BufferedSink] using [Context].
 */
class Filer(private val context: Context) {
    fun source(name: String): BufferedSource? {
        return runCatching {
            val inputStream = context.openFileInput(name)
            Okio.buffer(Okio.source(inputStream))
        }.getOrNull()
    }

    fun sink(name: String): BufferedSink {
        val outputStream: FileOutputStream = context.openFileOutput(name, Context.MODE_PRIVATE)
        return Okio.buffer(Okio.sink(outputStream))
    }
}
