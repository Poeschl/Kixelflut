package io.github.poeschl.kixelflut.utils

import java.io.*
import java.net.ServerSocket
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.concurrent.thread

class MockServer(private val port: Int) {

    val requests = ArrayList<String>()

    private val whenCases = HashMap<String, String>()
    private var running = false;
    private val serverSocket = ServerSocket(port)

    fun start() {
        running = true
        thread {
            val client = serverSocket.accept()
            println("Client connected: ${client.inetAddress.hostAddress}")

            thread {
                while (running) {
                    val reader = Scanner(client.getInputStream())
                    val writer: BufferedWriter = client.getOutputStream().bufferedWriter()
                    val command = reader.nextLine();
                    println("Received command '$command'")
                    requests.add(command)

                    val response = whenCases.getOrDefault(command, "")
                    writer.write(response + '\n')
                    writer.flush()
                }
            }
        }
    }

    fun stop() {
        running = false
    }

    fun whenRequest(command: String, response: String) {
        whenCases.put(command, response)
    }
}
