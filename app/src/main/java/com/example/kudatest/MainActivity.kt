package com.example.kudatest

import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kudatest.common.*
import com.example.kudatest.databinding.ActivityMainBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter


class MainActivity : AppCompatActivity(), OnChartValueSelectedListener {

    lateinit var xAxis: XAxis
    lateinit var yAxis: YAxis
    lateinit var set1: LineDataSet
    lateinit var chart: LineChart
    private var labelArray = ArrayList<String>()
    val values: ArrayList<Entry> = ArrayList()


    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: TransactionAdapter
    private lateinit var transactionList : List<TransactionEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initChatView()
        formatXandYAxis()
        showChartDataChart()
        // load data to transaction list
        loadLanguage()
        // initialize the adapter, and pass the required argument
        rvAdapter = TransactionAdapter(transactionList)

        // attach adapter to the recycler view
        binding.transRv.adapter = rvAdapter
    }

    // add items to the list manually in our case
    private fun loadLanguage() {
        transactionList = listOf(
            TransactionEntity("Money In","₦300" ,  resources.getString(R.string.money_in)),
            TransactionEntity("Money Out","₦100" ,  resources.getString(R.string.money_out)),
            TransactionEntity("Money In","₦250" ,  resources.getString(R.string.money_in)),
            TransactionEntity("Money In","₦1000" ,  resources.getString(R.string.money_in)),
            TransactionEntity("Money Out","₦500" , resources.getString(R.string.money_out)),
            TransactionEntity("Money In","₦1200", resources.getString(R.string.money_in)),
        )
    }

    private fun initChatView() {
        binding.chart.apply {
            setBackgroundColor(Color.WHITE)
            description.isEnabled = false
            setTouchEnabled(true)
            setOnChartValueSelectedListener(this@MainActivity)
            setDrawGridBackground(false)
            isDragEnabled = true
            setScaleEnabled(true)
            setPinchZoom(true)
            val markerView = ChartMarker(this@MainActivity, R.layout.marker_view )
            marker = markerView
            
        }
    }

    /**
     * set chart data
     */
    private fun setData() {
        // create a dataset and give it a type
        set1 = LineDataSet(values, "")
        set1.setDrawIcons(false)
        // draw dashed line
        set1.setDrawValues(false)
        set1.setDrawCircleHole(false)
        set1.setDrawCircles(false)
        set1.color = resources.getColor(R.color.green)

        // line thickness and point size
        set1.lineWidth = 4f
        set1.circleRadius = 4f
        // draw points as solid circles

        // customize legend entry
        set1.formLineWidth = 1f
        set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
        set1.formSize = 15f
        // text size of values
        set1.valueTextSize = 12f

        // set color of filled area
        // drawables only supported on api level 18 and above
        // drawables only supported on api level 18 and above
        val dataSets: ArrayList<ILineDataSet> = ArrayList()
        dataSets.add(set1) // add the data sets
        // create a data object with the data sets
        val data = LineData(dataSets)
        set1.isHighlightEnabled = true; // allow highlighting for DataSet

        // set data
        binding.chart.data = data
        binding.chart.data.notifyDataChanged()
        binding.chart.notifyDataSetChanged()
        set1.mode = LineDataSet.Mode.CUBIC_BEZIER;

    }
    /**
     * format chart X and Y axis
     */
    private fun formatXandYAxis() {
        xAxis = binding.chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.labelCount = 5

        // // Y-Axis Style // //
        yAxis = binding.chart.axisLeft
        yAxis.setDrawGridLines(true)

        // disable dual axis (only use LEFT axis)
        binding.chart.axisRight.isEnabled = false
        binding.chart.legend.isEnabled = false

        // draw limit lines behind data instead of on top
        yAxis.setDrawLimitLinesBehindData(true)
        // xAxis.setDrawLimitLinesBehindData(true)
        // draw points over time
        binding.chart.animateX(2000, Easing.EaseInCubic)

        setData()

    }

    private fun showChartDataChart() {

        val dummyChartData = listOf(
            ChartEntity(
                "Aug 5", listOf(
                    100F
                )
            ),
            ChartEntity(
                "Aug 23", listOf(
                    10F
                )
            ),
            ChartEntity(
                "Aug 23", listOf(
                    0F
                )
            ),
            ChartEntity(
                "Aug 24", listOf(
                    75F
                )
            ),
            ChartEntity(
                "Aug 24", listOf(
                    80F
                )
            )
        )
        for (label in dummyChartData) {
            labelArray.add(label.time)
        }
        xAxis.valueFormatter = MyXAxisFormatter(labelArray)

        /**
         * set chart values
         */
        for (i in dummyChartData.indices) {
            values.add(Entry(i.toFloat(), dummyChartData[i].values[0]-10))
        }

        yAxis.valueFormatter = MyValueFormatter()
        /**
         * update chart when data changes
         */
        set1.values = values
        set1.notifyDataSetChanged()
        binding.chart.data.notifyDataChanged()
        binding.chart.notifyDataSetChanged()
        binding.chart.invalidate()
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        //set1.setDrawCircles(true)
        //xAxis.position
        set1.setDrawHighlightIndicators(true)
        set1.setDrawCircleHole(true)
        print("Value clicked")
    }

    override fun onNothingSelected() {
        print("Nothing done here yet")
    }

}