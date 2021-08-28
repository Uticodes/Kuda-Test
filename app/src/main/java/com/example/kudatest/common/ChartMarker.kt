package com.example.kudatest.common

import android.content.Context
import android.view.View
import android.widget.TextView
import com.example.kudatest.R
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF

class ChartMarker(context: Context, layoutResource: Int) : MarkerView(context, layoutResource) {

    override fun onViewAdded(child: View?) {
        super.onViewAdded(child)

    }

    override fun refreshContent(entry: Entry?, highlight: Highlight?) {
        val value = entry?.y?.toDouble() ?: 0.0
        val dateValue = entry?.x?.toString() ?: ""
        var resText = ""
        var price = findViewById<TextView>(R.id.tvPrice)
        var date = findViewById<TextView>(R.id.tvDate)
        if (value.toString().length > 8) {
            resText = value.toString()
        } else {
            resText = value.toString()
        }
        price.text = "₦ $resText"
        when {
            dateValue.contains("0.0") -> {
                date.text = resources.getString(R.string.aug_5_2021)
            }
            dateValue.contains("1.0") -> {
                date.text = resources.getString(R.string.aug_23_2021)
            }
            dateValue.contains("2.0") -> {
                date.text = resources.getString(R.string.aug_23_2021)
            }
            dateValue.contains("3.0") -> {
                date.text = resources.getString(R.string.aug_24_2021)
            }
            dateValue.contains("4.0") -> {
                date.text = resources.getString(R.string.aug_24_2021)
            }
            else -> {
                date.text = dateValue
            }
        }

        super.refreshContent(entry, highlight)
    }

    override fun getOffsetForDrawingAtPoint(xpos: Float, ypos: Float): MPPointF {
        return MPPointF(-width / 2f, -height - 10f)
    }
}
