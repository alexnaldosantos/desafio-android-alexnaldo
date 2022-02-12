package br.com.viavarejo.desafio.utils

import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object Utils {
    fun md5(stringToHash: String): String {
        val md5 = "MD5"

        try {
            val digest = MessageDigest.getInstance(md5)
            digest.update(stringToHash.toByteArray())
            val messageDigest = digest.digest()

            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2) {
                    h = "0$h"
                }
                hexString.append(h)
            }
            return hexString.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""
    }

    class InfiniteScrollListener(private val layoutManager: androidx.recyclerview.widget.LinearLayoutManager, private val func: () -> Unit) : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
        private var previousTotal = 0
        private var loading = true
        private var visibleThreshold = 2
        private var firstVisibleItem = 0
        private var visibleItemCount = 0
        private var totalItemCount = 0

        override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            if (dy > 0) {
                visibleItemCount = recyclerView.childCount
                totalItemCount = layoutManager.itemCount
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                val needsLoad = (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false
                        previousTotal = totalItemCount
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                    <= (firstVisibleItem + visibleThreshold)) {
                    func()
                    loading = true
                }
            }
        }
    }

}