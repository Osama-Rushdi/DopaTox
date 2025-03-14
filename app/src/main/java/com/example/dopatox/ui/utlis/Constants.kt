import android.content.Context
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.view.View
import com.example.dopatox.R
import com.example.dopatox.domain.model.BoardItem

import android.view.animation.Animation
import android.view.animation.AnimationUtils

object Constants {

    fun runAnimation(context: Context, animId: Int): Animation =
        AnimationUtils.loadAnimation(context, animId)

    val boards: List<BoardItem>
        get() = listOf(
            BoardItem(
                R.drawable.frame_1,
                "Break free from digital addiction. Live more.",
                "Encouraging you to break away from unhealthy screen habits and experience a more satisfying, realistic journey.",
            ),
            BoardItem(
                R.drawable.frame_2,
                "Smart habits start here. Balance digital life.",
                "The app helps you build a healthier digital routine and find harmony between online and offline life.",
            ),
            BoardItem(
                R.drawable.frame_3,
                "Less screen, more life. Are you ready?",
                "A motivational challenge to inspire you to reduce screen time and engage in beneficial activities.",
            )
        )

    fun blurView(view: View, degree: Float) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val blurEffect = RenderEffect.createBlurEffect(degree, degree, Shader.TileMode.CLAMP)
            view.setRenderEffect(blurEffect)
        }
    }

    fun showBlur(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            view.setRenderEffect(null)
    }

}
