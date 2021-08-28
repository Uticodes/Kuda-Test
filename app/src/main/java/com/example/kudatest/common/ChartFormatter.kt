package com.example.kudatest.common

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.DecimalFormat

class MyXAxisFormatter constructor(var labelArray: ArrayList<String>) : ValueFormatter() {

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return  labelArray[value.toInt()]
    }
}


class MyYAxisFormatter : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        axis?.setLabelCount(5, true)
        var index = value

        return "₦${value.toInt()}"

    }
}

class MyValueFormatter : ValueFormatter() {

    val yy = arrayOf(0f, 25f, 50f, 75f, 100f)
    private val format = DecimalFormat("###,##00.0")

    // override this for e.g. LineChart or ScatterChart
    override fun getPointLabel(entry: Entry?): String {
        return format.format(entry?.y)
    }

    // override this for BarChart
    override fun getBarLabel(barEntry: BarEntry?): String {
        return format.format(barEntry?.y)
    }

    // override this for custom formatting of XAxis or YAxis labels
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        axis?.setLabelCount(5, true)
        var index = value
        if (index < 0 || index >= yy.size) {
            return "₦$value"
        }

        return format.format(index.toInt())
    }

}