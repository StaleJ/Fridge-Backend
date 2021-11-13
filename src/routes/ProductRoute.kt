package at.stefangaller.routes

import at.stefangaller.data.InStorage
import at.stefangaller.data.PostInStorage
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun Route.product() {

    val productService by di().instance<ProductService>()
    val storagesService by di().instance<StorageService>()
    val inStorageService by di().instance<InStoragesService>()
    val dateFormat = DateTimeFormatter.ofPattern("dd MMM uuuu", Locale.ENGLISH)

    get("products") {
        val allProducts = productService.getAllProduct()
        call.respond(allProducts)
    }

    post("product") {
        val productRequest = call.receive<Product>()
        productService.addProduct(productRequest)
        call.respond(HttpStatusCode.Accepted)
    }
    
    post("inStorage") {
        val inStorageRequest = call.receive<PostInStorage>()
        val product = productService.getProduct(inStorageRequest.productId)
        val storage = storagesService.getStorage(inStorageRequest.storageId)
        val expiredDate = LocalDate.parse(inStorageRequest.expiredDate, dateFormat)
        val quality = inStorageRequest.quantity
        val productToStorage = InStorage(storage, product, quality, expiredDate)
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