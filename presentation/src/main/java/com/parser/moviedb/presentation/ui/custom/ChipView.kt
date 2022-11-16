package com.parser.moviedb.presentation.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.parser.moviedb.presentation.databinding.ChipViewBinding
import com.parser.moviedb.presentation.utils.readOnly

class ChipView(
    context: Context,
    attrs: AttributeSet?,
    @AttrRes defStyleAttr: Int,
    @StyleRes defStyleRes: Int
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0, 0)

    private val binding = ChipViewBinding.inflate(LayoutInflater.from(context), this, false)

    private val _chipSelected = MutableLiveData(false)
    val chipSelected: LiveData<Boolean> = _chipSelected.readOnly

    init {
        binding.root.setOnClickListener {
            val newValue = !(_chipSelected.value ?: false)
            _chipSelected.value = newValue

            if (newValue) {
                binding.bg.setBackgroundColor(context.getColor(android.R.color.white))
                binding.tvTitle.setTextColor(context.getColor(android.R.color.black))
            } else {
                binding.bg.setBackgroundColor(context.getColor(android.R.color.black))
                binding.tvTitle.setTextColor(context.getColor(android.R.color.white))
            }

        }
        addView(binding.root)
    }

}