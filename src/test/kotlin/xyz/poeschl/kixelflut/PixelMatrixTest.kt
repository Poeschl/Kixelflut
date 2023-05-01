package xyz.poeschl.kixelflut

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.awt.Color

internal class PixelMatrixTest {

    @Test
    fun processData_asPixel() {
        //WHEN
        val matrix = PixelMatrix(10, 10)
        val testPixel1 = Pixel(Point(1, 1), Color.BLACK)
        val testPixel2 = Pixel(Point(1, 1), Color.WHITE)
        val testPixel3 = Pixel(Point(2, 1), Color.RED)
        matrix.insert(testPixel1)
        matrix.insert(testPixel2)
        matrix.insert(testPixel3)

        val captureList = mutableListOf<Pixel>()

        //THEN
        matrix.processData { pixel -> captureList.add(pixel) }

        //
        assertThat(captureList).containsOnly(testPixel2, testPixel3)
    }

    @Test
    fun processData_asMapping() {
        //WHEN
        val matrix = PixelMatrix(10, 10)
        val testPixel1 = Pixel(Point(1, 1), Color.BLACK)
        val testPixel2 = Pixel(Point(1, 1), Color.WHITE)
        val testPixel3 = Pixel(Point(2, 1), Color.RED)
        matrix.insert(testPixel1)
        matrix.insert(testPixel2)
        matrix.insert(testPixel3)

        val captureList = mutableListOf<Pixel>()

        //THEN
        matrix.processData { pixel -> captureList.add(pixel) }

        //
        assertThat(captureList).containsOnly(testPixel2, testPixel3)
    }

    @Test
    fun get() {
        // WHEN
        val matrix = PixelMatrix(10, 10)
        val testPixel = Pixel(Point(1, 1), Color.CYAN)
        matrix.insert(testPixel)

        // THEN
        val result = matrix.get(testPixel.point)

        // VERIFY
        assertThat(result).isEqualTo(testPixel)
    }

    @Test
    fun get_notSet() {
        // WHEN
        val matrix = PixelMatrix(10, 10)

        // THEN
        val result = matrix.get(Point(1, 1))

        // VERIFY
        assertThat(result).isNull()
    }
}
