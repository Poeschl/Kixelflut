package io.github.poeschl.kixelflut

/**
 * This class contains a basic construct to paint inside a render loop.
 */
abstract class Painter {

    private var runningRender = true

    /**
     * Starts the loop. Make sure you implemented your logic in {@link io.github.poeschl.kixelflut.Painter#render}.
     */
    fun start() {
        init()

        while (runningRender) {
            render()
        }

        afterStop()
    }

    /**
     * Stops the rendering loop.
     */
    fun stop() {
        runningRender = false
    }

    /**
     * Put any code which should run before the first render loop in here.
     */
    abstract fun init()

    /**
     * This method will be called every render cycle.
     */
    abstract fun render()

    /**
     * You might clean up after your work.
     */
    abstract fun afterStop()
}
