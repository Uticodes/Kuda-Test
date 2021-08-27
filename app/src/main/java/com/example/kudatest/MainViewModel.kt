package com.example.kudatest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet

class MainViewModel constructor() : ViewModel() {

    private val dayData = mutableListOf<Entry>()
    private val _lineDataSet = MutableLiveData(LineDataSet(dayData, CHART_LABEL))
    val lineDataSet: LiveData<LineDataSet> = _lineDataSet

    init {
        dayData.add(Entry(0f, 5f))
        dayData.add(Entry(1f, 4f))
        dayData.add(Entry(2f, 7f))
        dayData.add(Entry(3f, 8f))
        dayData.add(Entry(4f, 10f))
        dayData.add(Entry(5f, 7f))
        dayData.add(Entry(6f, 3f))
        dayData.add(Entry(7f, 6f))
        dayData.add(Entry(8f, 5f))
        dayData.add(Entry(9f, 8f))

        _lineDataSet.value = LineDataSet(dayData, CHART_LABEL)
    }

    companion object{
        private const val CHART_LABEL = "ACCOUNT_CHART"
    }

}