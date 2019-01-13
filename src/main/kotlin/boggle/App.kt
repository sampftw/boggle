package boggle

import boggle.solver.NaiveSolver
import boggle.solver.TrieSolver
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

@io.ktor.util.KtorExperimentalAPI
fun Application.main() {
    log.info("Starting boggle thingee")
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
    val dictionaryName = environment.config.property("dictionary.name")
    val dictionary: Dictionary = ClasspathDictionary(dictionaryName.getString())


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
            try {
                val board = Board(post["board"])
                val solver = TrieSolver(dictionary)
                solver.solve(board)
                val answers = solver.results.sorted()
                call.respond(FreeMarkerContent("solved.ftl", mapOf("board" to board, "answers" to answers, "answerCount" to answers.size)))
            } catch (e: Exception) {
                call.respond(FreeMarkerContent("index.ftl", mapOf("error" to e.message)))
            }
        }
    }
}
