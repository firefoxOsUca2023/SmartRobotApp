package com.example.zacatales.smartrobotapp.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View


class RouteView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val pathPaint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }
    private val arrowPaint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 5f
        style = Paint.Style.FILL
    }
    private val arrowPath = Path()

    private var routeList: List<String> = emptyList()

    fun setRouteList(routeList: List<String>) {
        this.routeList = routeList
        invalidate() // Vuelve a dibujar la vista cuando cambia la lista de rutas
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()

        val centerX = width / 2
        val centerY = height / 2

        var currentX = centerX
        var currentY = centerY

        routeList.forEach { route ->
            when (route) {
                "F" -> {
                    // Dibujar flecha hacia arriba
                    canvas.drawLine(currentX, currentY, currentX, currentY - 100f, pathPaint)
                    arrowPath.reset()
                    arrowPath.moveTo(currentX, currentY - 100f)
                    arrowPath.lineTo(currentX - 10f, currentY - 80f)
                    arrowPath.lineTo(currentX + 10f, currentY - 80f)
                    arrowPath.close()
                    canvas.drawPath(arrowPath, arrowPaint)

                    currentY -= 100f
                }
                "B" -> {
                    // Dibujar flecha hacia abajo
                    canvas.drawLine(currentX, currentY, currentX, currentY + 100f, pathPaint)
                    arrowPath.reset()
                    arrowPath.moveTo(currentX, currentY + 100f)
                    arrowPath.lineTo(currentX - 10f, currentY + 80f)
                    arrowPath.lineTo(currentX + 10f, currentY + 80f)
                    arrowPath.close()
                    canvas.drawPath(arrowPath, arrowPaint)

                    currentY += 100f
                }
                "L" -> {
                    // Dibujar línea hacia la izquierda
                    canvas.drawLine(currentX, currentY, currentX - 100f, currentY, pathPaint)

                    currentX -= 100f
                }
                "R" -> {
                    // Dibujar línea hacia la derecha
                    canvas.drawLine(currentX, currentY, currentX + 100f, currentY, pathPaint)

                    currentX += 100f
                }
            }
        }
    }
}
