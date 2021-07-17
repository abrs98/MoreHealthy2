package barrios.abrahan.morehealthy2.utilities

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import barrios.abrahan.morehealthy2.R

class CustomBarDrawable: Drawable {
    var coordenadas: RectF? = null
    var context: Context? = null
    var progress: progress? = null

    constructor(context: Context, valor: progress){
        this.context = context
        this.progress = valor
    }

    override fun draw(canvas: Canvas) {
        val fondo: Paint = Paint()
        fondo.style = Paint.Style.FILL
        fondo.isAntiAlias = true
        fondo.color = context?.resources?.getColor(R.color.azul3) ?: R.color.azul3
        val ancho: Float = (canvas.width - 10).toFloat()
        val alto: Float = (canvas.height - 10).toFloat()

        coordenadas = RectF(25.0F, 25.0F, ancho, alto)

        canvas.drawRect(coordenadas!!, fondo)

        if(this.progress != null){
            val porcentaje: Float = this.progress!!.porcentaje * (canvas.width -10) / 100
            var coordenadas2 = RectF(0.0F, 0.0F, porcentaje, alto)

            var seccion: Paint = Paint()
            seccion.style = Paint.Style.FILL
            seccion.isAntiAlias = true
            seccion.color = ContextCompat.getColor(this.context!!, progress!!.color)

            canvas.drawRect(coordenadas2!!, seccion)
        }

    }

    override fun setAlpha(alpha: Int) {

    }

    override fun setColorFilter(colorFilter: ColorFilter?) {

    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }
}