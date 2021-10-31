package at.stefangaller.services

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

fun DI.MainBuilder.bindServices(){
    bind<StorageService>() with singleton { StorageService() }
    bind<ProductService>() with singleton { ProductService() }
    bind<InStoragesService>() with singleton { InStoragesService() }
}