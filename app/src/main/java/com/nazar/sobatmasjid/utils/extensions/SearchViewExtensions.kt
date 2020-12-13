package com.nazar.sobatmasjid.utils.extensions

import androidx.appcompat.widget.SearchView

fun SearchView.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            afterTextChanged.invoke(newText.toString())
            return true
        }
    })
}