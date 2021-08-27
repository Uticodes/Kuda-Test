package com.example.kudatest

//import androidx.activity.viewModels
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kudatest.databinding.ActivityMainBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.animation.Easing.EaseOutBounce
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider

import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.DecimalFormat


class MainActivity : AppCompatActivity(), OnChartValueSelectedListener {

//    @Inject
//    lateinit var factory: ViewModelFactory
    lateinit var xAxis: XAxis
    lateinit var yAxis: YAxis
    lateinit var set1: LineDataSet
    lateinit var chart: LineChart
    private var xAxislabel = ArrayList<String>()
    val values: ArrayList<Entry> = ArrayList()
    private var isOneMonth = true

    private lateinit var binding: ActivityMainBinding
//    private val mainViewModel: MainViewModel by viewModel {
//        factory
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initChatView()
    }

    private fun initChatView() {
        chart = binding.chart


        val mMonths = listOf("", "Aug 5", "Aug 23", "Aug 23", "Aug 24", "Aug 24")

        //val yy = arrayOf("₦0", "₦25", "₦50", "₦75", "₦100")
        val yy = arrayOf(0f, 25f, 50f, 75f, 100f)
        for (int in yy.indices){
            values.add(Entry(yy[int.toInt()], int.toFloat()))
        }
        //var xVlaue = float.pa
//        values.add(Entry(100f, 99f))
//        values.add(Entry(75f, 10f))
//        values.add(Entry(50f, 50f))
//        values.add(Entry(25f, 80f))
//        values.add(Entry(0f, 70f))

        val xAxisLabel: ArrayList<String> = ArrayList()
        xAxisLabel.add("Mon")
        xAxisLabel.add("Tue")
        xAxisLabel.add("Wed")
        xAxisLabel.add("Thu")
        xAxisLabel.add("Fri")
        xAxisLabel.add("Sat")
        xAxisLabel.add("Sun")

        set1 = LineDataSet(values, "")
        //set1.lineWidth = 4f
//        chart.axisRight.isEnabled = false;

        set1.isHighlightEnabled = true

        set1.setDrawValues(false)
        //set1.setDrawFilled(true)
        //set1.lineWidth = 1f

        //chart.axisLeft.zeroLineWidth = 0.0f
        chart.notifyDataSetChanged()

        chart.apply {

            set1.mode = LineDataSet.Mode.CUBIC_BEZIER
            set1.setDrawCircles(false)
            set1.lineWidth = 3f
            set1.circleRadius = 6f
            set1.setCircleColor(Color.GREEN)
            set1.color = Color.GREEN

            xAxis.labelRotationAngle = 0f
            axisRight.isEnabled = false
            xAxis.axisMaximum = 5f
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            xAxis.axisLineColor = Color.GREEN
            xAxis.setLabelCount(5, true)
            xAxis.valueFormatter = MyXAxisFormatter()


            yAxis = axisLeft
            yAxis.setLabelCount(6, true)
            yAxis.axisLineColor = R.color.green
            yAxis.valueFormatter = MyYAxisFormatter()

            setTouchEnabled(true)
            setPinchZoom(false)

            legend.isEnabled = false
            description.isEnabled = false
            setNoDataText("")
            // set listeners
            //setOnChartValueSelectedListener(this@MainActivity)
            setDrawGridBackground(false)
            setDrawGridBackground(false)
            data = LineData(set1)
            data.notifyDataChanged()
            animateXY(2000,2000)
            invalidate()
        }


        val markerView = CustomMarker(this, R.layout.marker_view)
        chart.marker = markerView
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        //set1.setDrawCircles(false)

    }

    override fun onNothingSelected() {
        TODO("Not yet implemented")



    }
}

class CustomMarker(context: Context, layoutResource: Int):  MarkerView(context, layoutResource) {
    //lateinit var binding: MarkerViewBinding
    //var tvPrice = TextView(context)

    override fun onViewAdded(child: View?) {
        super.onViewAdded(child)

    }
    override fun refreshContent(entry: Entry?, highlight: Highlight?) {
        val value = entry?.y?.toDouble() ?: 0.0
        val dateValue = entry?.x?.toDouble() ?: 0.0
        var resText = ""
        var price =  findViewById<TextView>(R.id.tvPrice)
        var date = findViewById<TextView>(R.id.tvDate)
        if(value.toString().length > 8){
            resText = "Val: " + value.toString().substring(0,7)
        }
        else{
            resText = "Val: " + value.toString()
        }
        price.text = resText
        date.text = dateValue.toString()
        super.refreshContent(entry, highlight)
    }

    override fun getOffsetForDrawingAtPoint(xpos: Float, ypos: Float): MPPointF {
        return MPPointF(-width / 2f, -height - 10f)
    }
}

class MyXAxisFormatter : ValueFormatter() {
    private val days = arrayOf("Aug 5","Aug 5", "Aug 23", "Aug 23", "Aug 24", "Aug 24")
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return days.getOrNull(value.toInt()) ?: value.toString()
    }
}


class MyYAxisFormatter : ValueFormatter() {
    val amounts = arrayOf(0f, 25f, 50f, 75f, 100f)
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        axis?.setLabelCount(6, true)
        var index = value
        if (index < 0 ){
            return "₦$value"
        }

       return amounts[index.toInt()].toString()
        //return amounts.getOrNull(value.toInt()) ?: value.toFl
    }
}

class MyValueFormatter : ValueFormatter() {

    val yy = arrayOf(0f, 25f, 50f, 75f, 100f)
    private val format = DecimalFormat("###,##0")
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
        axis?.setLabelCount(6, true)
        var index = value
        if (index < 0 || index >= yy.size){
            return "₦$value"
        }

        return format.format(index.toInt())
    }
    
}
