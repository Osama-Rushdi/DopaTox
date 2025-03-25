package com.example.dopatox.ui.home.fragment.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar
import com.example.dopatox.R
import com.example.dopatox.databinding.FragmentHomeBinding
import com.example.dopatox.ui.utlis.GetAppUsage
import com.example.dopatox.ui.utlis.successToast
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private val calendar = Calendar.getInstance()
    private var selectedDayIndex = calendar.get(Calendar.DAY_OF_WEEK) - 2
    private var usageHours = 0
    private var usageMinutes = 0

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            initUi()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        handleChart()
        updateDate()
        initListeners()
        initShimmer()
    }

    private suspend fun initUi() {
        binding.appRv.adapter = AppUsageAdapter(GetAppUsage.getAppUsage(requireContext())) {
            successToast(
                requireContext(),
                "${Constants.getHours(it.usageTime)}hr : ${Constants.getMinutes(it.usageTime)}min"
            )
        }
        binding.usageShimmer.hideShimmer()
        usageHours = Constants.getHours(GetAppUsage.allTotalTime)
        usageMinutes = Constants.getMinutes(GetAppUsage.allTotalTime)
        binding.hours.text = usageHours.toString()
        binding.min.text = usageMinutes.toString()
        initProgressBar()
    }

    private fun initShimmer() {
        val shimmerAdapter = AppShimmerAdapter()
        binding.appRv.adapter = shimmerAdapter
    }

    private fun exceededProgressLimit(progress: RoundCornerProgressBar) {
        val red = ContextCompat.getColor(requireContext(), R.color.red)
        val lightRed = ContextCompat.getColor(requireContext(), R.color.lightRed)
        progress.setProgressColor(red)
        binding.hours.setTextColor(red)
        binding.min.setTextColor(lightRed)
    }

    private fun refillProgressLimit(progress: RoundCornerProgressBar) {
        val darkTeal = ContextCompat.getColor(requireContext(), R.color.dark_teal)
        val lightTeal = ContextCompat.getColor(requireContext(), R.color.light_teal)
        progress.setProgressColor(darkTeal)
        progress.setSecondaryProgressColor(lightTeal)
        binding.hours.setTextColor(darkTeal)
        binding.min.setTextColor(lightTeal)
    }

    private fun initListeners() {
        binding.leftBack.setOnClickListener {
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            selectedDayIndex = if (selectedDayIndex == 0) 6 else selectedDayIndex - 1
            updateDate()
            handleChart()
        }

        binding.rightBack.setOnClickListener {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            selectedDayIndex = if (selectedDayIndex == 6) 0 else selectedDayIndex + 1
            updateDate()
            handleChart()
        }

        binding.setDailyLimits.setOnClickListener {
            showScreenTimeLimitDialog()
        }
    }

    private fun initProgressBar() {
        val hours = Constants.getHours(GetAppUsage.allTotalTime)
        val minutes = (Constants.getMinutes(GetAppUsage.allTotalTime).toFloat()) / 60
        binding.usageProgress.setProgress(hours)
        binding.usageProgress.setSecondaryProgress(hours + minutes)
        if (hours + minutes >= binding.usageProgress.getMax()) {
            exceededProgressLimit(binding.usageProgress)
        }
        else {
            refillProgressLimit(binding.usageProgress)
        }
    }

    private fun resetProgressBar() {
        binding.usageShimmer.showShimmer(true)
        binding.usageShimmer.startShimmer()
        val progress = binding.usageProgress
        binding.hours.text = "0"
        binding.min.text = "0"
        progress.setProgress(0)
        progress.setSecondaryProgress(0)
    }

    private fun handleChart() {
        var isToday = false
        val barChart = binding.barChart
        val barEntries = listOf(
            BarEntry(0f, 2.0f),
            BarEntry(1f, 5.0f),
            BarEntry(2f, 4.5f),
            BarEntry(3f, 0.5f),
            BarEntry(4f, 0.3f),
            BarEntry(5f, 0.2f),
            BarEntry(6f, 0.4f)
        )

        val colors = mutableListOf<Int>().apply {
            val lightTeal = ContextCompat.getColor(requireContext(), R.color.dark_teal)
            val darkTeal = ContextCompat.getColor(requireContext(), R.color.light_teal)
            for (i in 0 until barEntries.size) {
                if (i == selectedDayIndex) {
                    add(lightTeal)
                    isToday = true
                } else {
                    add(darkTeal)
                    isToday = false
                }
            }
        }

        val dataSet = BarDataSet(barEntries, "Hours").apply {
            setColors(colors)
            valueTextSize = 12f
            valueTextColor = Color.BLACK
        }

        val barData = BarData(dataSet)
        barData.barWidth = 0.4f
        barChart.data = barData

        val days = arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        barChart.xAxis.apply {
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return days[value.toInt()]
                }
            }
            position = XAxis.XAxisPosition.BOTTOM
            granularity = 1f
            setDrawGridLines(false)
        }

        barChart.axisLeft.setDrawGridLines(false)
        barChart.axisRight.isEnabled = false
        barChart.legend.isEnabled = false
        barChart.description.isEnabled = false
        barChart.disableScroll()

        barChart.invalidate()
    }

    private fun updateDate() {
        val dateFormat = SimpleDateFormat("EEEE, MMMM d", Locale.getDefault())
        val dateText = dateFormat.format(calendar.time)

        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val suffix = when {
            day in 11..13 -> "th"
            day % 10 == 1 -> "st"
            day % 10 == 2 -> "nd"
            day % 10 == 3 -> "rd"
            else -> "th"
        }
        binding.dateTv.text = "$dateText$suffix"
    }

    override fun onPause() {
        super.onPause()
        resetProgressBar()
    }


    fun showScreenTimeLimitDialog() {
        val dialog = TimeLimitDialogFragment(binding.usageProgress) {
            initProgressBar()
        }
        dialog.show(childFragmentManager, "timeLimitDialog")
    }

}