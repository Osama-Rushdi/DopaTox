import android.content.Context
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils

object Constants {
    const val THEME = "theme"
    const val LANGUAGE = "language"
    const val IS_FIRST = "newUser"

    fun runAnimation(context: Context, animId: Int): Animation =
        AnimationUtils.loadAnimation(context, animId)

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
