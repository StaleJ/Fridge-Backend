package at.stefangaller.routes

import at.stefangaller.data.InStorage
import at.stefangaller.data.Product
import at.stefangaller.services.InStoragesService
import at.stefangaller.services.ProductService
import at.stefangaller.services.StorageService
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.di

fun Route.product() {

    val productService by di().instance<ProductService>()
    val storagesService by di().instance<StorageService>()
    val inStorageService by di().instance<InStoragesService>()

    get("products") {
        val allProducts = productService.getAllProduct()
        call.respond(allProducts)
    }

    post("product") {
        val productRequest = call.receive<Product>()
        productService.addProduct(productRequest)
        call.respond(HttpStatusCode.Accepted)
    }

    post("product/{productId}/storage/{storageId}/{quantity}") {
        val productId = call.parameters["productId"]?.toIntOrNull() ?: throw NotFoundException()
        val storageId = call.parameters["storageId"]?.toIntOrNull() ?: throw NotFoundException()
        val quality = call.parameters["quantity"]?.toIntOrNull() ?: throw NotFoundException()
        val product = productService.getProduct(productId)
        val storage = storagesService.getStorage(storageId)
        val productToStorage = InStorage(storage, product, quality)
        inStorageService.addProductToStorage(productToStorage)
        call.respond(HttpStatusCode.Accepted)
    }

    delete("product/{id}") {
        val productId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
        productService.deleteProduct(productId)
        call.respond(productService.getAllProduct())
        call.respond(HttpStatusCode.OK)
    }
}