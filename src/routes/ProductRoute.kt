package at.stefangaller.routes

import at.stefangaller.data.Product
import at.stefangaller.services.ProductService
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

    get("products") {
        val allProducts = productService.getAllProduct()
        call.respond(allProducts)
    }

    post("product") {
        val productRequest = call.receive<Product>()
        productService.addProduct(productRequest)
        call.respond(HttpStatusCode.Accepted)
    }

    delete("product/{id}") {
        val productId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
        productService.deleteProduct(productId)
        call.respond(productService.getAllProduct())
        call.respond(HttpStatusCode.OK)
    }
}