package io.github.poeschl.kixelflut

import io.github.poeschl.kixelflut.utils.MockServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.awt.Color
import kotlin.random.Random

internal class PixelflutTest {

    private val testPort = 50000 + Random.nextInt(10)
    private val mockServer = MockServer(testPort)
    private val interfaceToTest = Pixelflut("localhost", testPort)

    @BeforeEach
    internal fun setUp() {
        mockServer.start()
    }

    @AfterEach
    internal fun tearDown() {
        mockServer.stop()
    }

    @Test
    fun getPlaygroundSize() {
        //WHEN
        val expected = Pair(42, 78)

        mockServer.whenRequest("SIZE", "SIZE ${expected.first} ${expected.second}")

        //THEN
        val size = interfaceToTest.getPlaygroundSize()

        //VERIFY
        assertThat(size).isEqualTo(expected)
    }

    @Test
    fun getPlaygroundSize_invalidPattern() {
        //WHEN
        mockServer.whenRequest("SIZE", "Something")

        //THEN
        val size = interfaceToTest.getPlaygroundSize()

        //VERIFY
        assertThat(size).isEqualTo(Pair(0,0))
    }

    @Test
    fun paintPixelSet() {
        //WHEN
        val pixels = setOf(Pixel(Point(12,23), Color.BLUE), Pixel(Point(21,32), Color.RED))

        //THEN
        interfaceToTest.paintPixelSet(pixels)

        //VERIFY
        assertThat(mockServer.requests).containsExactlyInAnyOrder("PX 12 23 0000FF", "PX 21 32 FF0000")
    }

    @Test
    fun getPixel() {
        //WHEN
        val point = Point(123,654)
        mockServer.whenRequest("PX ${point.x} ${point.y}", "PX ${point.x} ${point.y} 0000FF")

        //THEN
        val pixel = interfaceToTest.getPixel(point)

        //VERIFY
        assertThat(pixel.point).isEqualTo(point)
        assertThat(pixel.color).isEqualTo(Color.BLUE)
    }

    @Test
    fun getPixel_invalid() {
        //WHEN
        val point = Point(123,654)
        mockServer.whenRequest("PX ${point.x} ${point.y}", "I'm invalid")

        //THEN
        val pixel = interfaceToTest.getPixel(point)

        //VERIFY
        assertThat(pixel.point).isEqualTo(Point(0,0))
        assertThat(pixel.color).isEqualTo(Color.BLACK)
    }
}
