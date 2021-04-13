package xyz.poeschl.kixelflut

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.awt.Color

internal class PixelTest {

    @Test
    internal fun equality() {
        //WHEN
        val point1 = Point(1, 1)
        val point2 = Point(2, 1)
        val pixel1 = Pixel(point1, Color.BLACK)
        val pixel2 = Pixel(point2, Color.RED)
        val pixel3 = Pixel(point1, Color.BLUE)

        //VERIFY
        assertThat(pixel1).isEqualTo(pixel1)
        assertThat(pixel2).isNotEqualTo(pixel1)
        assertThat(pixel3).isEqualTo(pixel1)
    }
}
