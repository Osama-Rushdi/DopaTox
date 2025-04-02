import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.renderer.BarChartRenderer
import com.github.mikephil.charting.utils.ViewPortHandler
import com.github.mikephil.charting.animation.ChartAnimator

class RoundedBarChartRenderer(
    chart: BarChart,
    animator: ChartAnimator,
    viewPortHandler: ViewPortHandler,
    private val radius: Float
) : BarChartRenderer(chart, animator, viewPortHandler) {

    private val barRect = RectF()

    override fun drawDataSet(c: Canvas, dataSet: IBarDataSet, index: Int) {
        val barBorderPaint = Paint().apply {
            style = Paint.Style.FILL
            isAntiAlias = true
        }

        val trans = mChart.getTransformer(dataSet.axisDependency)
        val barData = mChart.barData
        val phaseY = mAnimator.phaseY

        for (i in 0 until dataSet.entryCount) {
            val entry = dataSet.getEntryForIndex(i) as BarEntry
            val color = dataSet.getColor(i)
            barBorderPaint.color = color

            val left = entry.x - barData.barWidth / 2f
            val right = entry.x + barData.barWidth / 2f
            val top = entry.y * phaseY
            val bottom = 0f

            barRect.set(left, top, right, bottom)
            trans.rectValueToPixel(barRect)

            c.drawRoundRect(barRect, radius, radius, barBorderPaint)
        }
    }
}
