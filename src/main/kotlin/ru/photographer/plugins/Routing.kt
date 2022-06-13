package ru.photographer.plugins

import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.photographer.models.ImageResponseModel
import java.io.File

fun Application.configureRouting() {

    routing {

        get("/hello/") {
            call.respondText("Hello!")
        }

        static("/") {
            staticRootFolder = File("assets")
            files(".")
        }

        post("/upload") {
            val multipartData = call.receiveMultipart()
            var fileDescription = ""
            var fileName = ""
            multipartData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        fileDescription = part.value
                    }
                    is PartData.FileItem -> {
                        fileName = part.originalFileName as String
                        val fileBytes = part.streamProvider().readBytes()
                        File("assets/$fileName").writeBytes(fileBytes)
                    }
                    else -> {}
                }
                part.dispose()
            }

            call.respond(ImageResponseModel("$fileDescription is uploaded to 'assets/$fileName'"))
        }
    }
}
