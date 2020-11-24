package com.udacity.shoestore

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener


@BindingAdapter(value = ["app:holder", "app:holderAttrChanged"], requireAll = false)
fun setHolder(editText: EditText, double: Double, listener: InverseBindingListener) {
    editText.setText(double.toString())
    editText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            listener.onChange()
        }

        override fun afterTextChanged(s: Editable?) {
        }
    })
}

@InverseBindingAdapter(attribute = "app:holder", event = "app:holderAttrChanged")
fun getHolder(editText: EditText): Double {
    return editText.text.toString().toDouble()
}