package com.samples.itis_android_inception_22.presentation

fun main() {

    val messageLogger = MessagesLogger()
    val emailLogger = EmailLogger()

    val servicesObserver = ServicesObserver(messageLogger)
    servicesObserver.messagesLogger = emailLogger
}


class ServicesObserver(private val emailLogger: Logger) {

    lateinit var messagesLogger: Logger

    fun sendLogMessage() {
        messagesLogger.sendMessage()
    }


    fun sendEmailNotification() {
        emailLogger.sendMessage()
    }
}

class MessagesLogger : Logger {

    fun logMessage(message: String) {
        // Do something
    }

    override fun sendMessage() {
        TODO("Not yet implemented")
    }

    override fun doAction() {
        TODO("Not yet implemented")
    }
}

class EmailLogger : Logger {

    override fun sendMessage() {
        TODO("Not yet implemented")
    }

    override fun doAction() {
        TODO("Not yet implemented")
    }
}

interface Logger {

    fun sendMessage()
    fun doAction()
}