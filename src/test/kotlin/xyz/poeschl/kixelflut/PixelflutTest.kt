package xyz.poeschl.kixelflut

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import xyz.poeschl.kixelflut.utils.MockServer
import java.awt.Color
import java.lang.Thread.sleep

internal class PixelflutTest {

    private val testPort = 12345
    private val mockServer = MockServer(testPort)
    private val interfaceToTest = Pixelflut("127.0.0.1", testPort)

    @BeforeEach
    internal fun setUp() {
        mockServer.start()
    }

    @AfterEach
    internal fun tearDown() {
        interfaceToTest.close()
        mockServer.stop()
    }

    @Test
    fun getPlaygroundSize() {
        //WHEN
        val expected = Pair(42, 78)

        mockServer.whenRequest("SIZE\n", "SIZE ${expected.first} ${expected.second}")

        //THEN
        val size = interfaceToTest.getScreenSize()

        //VERIFY
        assertThat(size).isEqualTo(expected)
    }

    @Test
    fun getPlaygroundSize_invalidPattern() {
        //WHEN
        mockServer.whenRequest("SIZE", "Something")

        //THEN
        val size = interfaceToTest.getScreenSize()

        //VERIFY
        assertThat(size).isEqualTo(Pair(0, 0))
    }

    @Test
    fun drawPixel() {
        //WHEN
        val pixel = Pixel(Point(12, 23), Color.BLUE)

        //THEN
        interfaceToTest.drawPixel(pixel)

        //VERIFY
        sleep(100) //wait a bit for the requests
        assertThat(mockServer.requests).containsExactly("PX 12 23 0000FF\n")
    }

    @Test
    fun drawPixels() {
        //WHEN
        val pixels = setOf(Pixel(Point(12, 23), Color.BLUE), Pixel(Point(21, 32), Color.RED))

        //THEN
        interfaceToTest.drawPixels(pixels)

        //VERIFY
        sleep(100) //wait a bit for the requests
        assertThat(mockServer.requests).containsOnly("PX 12 23 0000FF\nPX 21 32 FF0000\n")
    }

    @Test
    fun getPixel() {
        //WHEN
        val point = Point(123, 654)
        mockServer.whenRequest("PX ${point.x} ${point.y}\n", "PX ${point.x} ${point.y} 0000FF")

        //THEN
        val pixel = interfaceToTest.getPixel(point)

        //VERIFY
        assertThat(pixel.point).isEqualTo(point)
        assertThat(pixel.color).isEqualTo(Color.BLUE)
    }

    @Test
    fun getPixel_invalid() {
        //WHEN
        val point = Point(123, 654)
        mockServer.whenRequest("PX ${point.x} ${point.y}", "I'm invalid")

        //THEN
        val pixel = interfaceToTest.getPixel(point)

        //VERIFY
        assertThat(pixel.point).isEqualTo(Point(0, 0))
        assertThat(pixel.color).isEqualTo(Color.BLACK)
    }

    @Test
    fun getPixelArea() {
        //WHEN
        val pixel1 = Pixel(Point(1, 1), Color.RED)
        val pixel2 = Pixel(Point(2, 1), Color.GREEN)

        mockServer.whenRequest(
            "PX ${pixel1.point.x} ${pixel1.point.y}\n" +
                    "PX ${pixel2.point.x} ${pixel2.point.y}\n",
            "PX ${pixel1.point.x} ${pixel1.point.y} FF0000\n" +
                    "PX ${pixel2.point.x} ${pixel2.point.y} 00FF00\n"
        )


        //THEN
        val pixels = interfaceToTest.getPixels(Point(1, 1), Point(2, 1))

        //VERIFY
        assertThat(pixels).containsExactly(pixel1, pixel2)
    }
}
