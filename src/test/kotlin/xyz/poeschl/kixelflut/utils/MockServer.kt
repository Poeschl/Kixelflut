package xyz.poeschl.kixelflut.utils

import mu.KotlinLogging
import java.net.ServerSocket
import kotlin.concurrent.thread

class MockServer(port: Int) {

    companion object {
        val LOGGER = KotlinLogging.logger {}
    }

    val requests = ArrayList<String>()

    private val whenCases = HashMap<String, String>()
    private var running = false;
    private val serverSocket = ServerSocket(port)

    fun start() {
        running = true
        thread {
            val client = serverSocket.accept()
            LOGGER.info { "Client connected: ${client.inetAddress.hostAddress}" }

            thread {
                while (running) {
                    val reader = client.getInputStream().bufferedReader()
                    val writer = client.getOutputStream().bufferedWriter()
                    var wholeCommand = ""
                    do {
                        val command = reader.readLine();
                        wholeCommand += command + "\n"
                    } while (reader.ready())

                    LOGGER.info { "Received command '${wholeCommand.replace("\n", "|")}'" }
                    requests.add(wholeCommand)
                    val response = whenCases.getOrDefault(wholeCommand, "")
                    writer.write(response + '\n')
                    writer.flush()
                }
            }
        }
    }

    fun stop() {
        running = false
        serverSocket.close()
    }

    fun whenRequest(command: String, response: String) {
        whenCases.put(command, response)
    }
}
