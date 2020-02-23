package io.github.poeschl.kixelflut

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.awt.Color

internal class DrawUtilsTest {

    @Test
    fun createHorizontalPixels() {
        //WHEN
        val startingPoint = Point(1, 1)

        //THEN
        val pixels = createHorizontalPixels(startingPoint, 5, Color.BLUE)

        //VERIFY
        assertThat(pixels).containsExactlyInAnyOrder(
            Pixel(Point(1, 1), Color.BLUE),
            Pixel(Point(2, 1), Color.BLUE),
            Pixel(Point(3, 1), Color.BLUE),
            Pixel(Point(4, 1), Color.BLUE),
            Pixel(Point(5, 1), Color.BLUE)
        )
    }

    @Test
    fun createVerticalPixels() {
        //WHEN
        val startingPoint = Point(1, 1)

        //THEN
        val pixels = createVerticalPixels(startingPoint, 5, Color.BLUE)

        //VERIFY
        assertThat(pixels).containsExactlyInAnyOrder(
            Pixel(Point(1, 1), Color.BLUE),
            Pixel(Point(1, 2), Color.BLUE),
            Pixel(Point(1, 3), Color.BLUE),
            Pixel(Point(1, 4), Color.BLUE),
            Pixel(Point(1, 5), Color.BLUE)
        )
    }

    @Test
    fun createRectPixels() {
        //WHEN
        val startingPoint = Point(1, 1)

        //THEN
        val pixels = createRectPixels(startingPoint, Pair(2, 2), Color.GREEN)

        //VERIFY
        assertThat(pixels).containsExactlyInAnyOrder(
            Pixel(Point(1, 1), Color.GREEN),
            Pixel(Point(1, 2), Color.GREEN),
            Pixel(Point(2, 1), Color.GREEN),
            Pixel(Point(2, 2), Color.GREEN)
        )
    }
}
