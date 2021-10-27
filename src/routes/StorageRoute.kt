package at.stefangaller.routes

import at.stefangaller.data.Storage
import at.stefangaller.services.StorageService
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.di

fun Route.storage() {

    val storageService by di().instance<StorageService>()

    get("storages") {
        val allStorages = storageService.getAllStorages()
        call.respond(allStorages)
    }

    post("storage") {
        val storageRequest = call.receive<Storage>()
        storageService.addStorage(storageRequest)
        call.respond(HttpStatusCode.Accepted)
    }

    delete("storage/{id}") {
        val storageId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
        storageService.deleteStorage(storageId)
        call.respond(HttpStatusCode.OK)
    }

}