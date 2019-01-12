package boggle

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.*
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.request.receiveParameters
import io.ktor.response.*
import io.ktor.routing.*

fun Application.main() {
    log.info("Starting boggle thingee")
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {

        static("/static") {
            resources("static")
        }

        get("/") {
            call.respond(FreeMarkerContent("index.ftl",null,""))
        }

        get("/hi") {
            call.respondText("hi boggle", ContentType.Text.Html)
        }

        post( "/solve") {
            val post = call.receiveParameters()
            val board = post["board"]
            if (board?.length != 16) {
                call.respond(FreeMarkerContent("index.ftl", mapOf("error" to "Invalid board")))
            } else {
                call.respond(FreeMarkerContent("solved.ftl", mapOf("board" to Board(board))))
            }
        }
    }
}
