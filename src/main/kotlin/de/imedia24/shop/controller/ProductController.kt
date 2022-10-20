package de.imedia24.shop.controller

import de.imedia24.shop.domain.product.CreateProductDto
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.domain.product.UpdateProductDto
import de.imedia24.shop.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam

@RestController
class ProductController(private val productService: ProductService) {

    private val logger = LoggerFactory.getLogger(ProductController::class.java)!!

    @GetMapping("/product/{sku}", produces = ["application/json;charset=utf-8"])
    fun findProductBySku(@PathVariable("sku") sku: String): ResponseEntity<ProductResponse> {
        logger.info("Request for product $sku")

        val product = productService.findProductBySku(sku)
        return if(product == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(product)
        }
    }

    @GetMapping("/products/{skus}", produces = ["application/json;charset=utf-8"])
    fun findProductsBySKUs(@PathVariable("skus") skus: String) : ResponseEntity<List<ProductResponse>> {
        logger.info("Request for products $skus")
        val products = skus
            .split(",")
            .mapNotNull { productService.findProductBySku(it) }
            .toList()
        return if(products.isEmpty()) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(products)
        }
    }

    @PostMapping("/product", produces = ["application/json;charset=utf-8"])
    fun addProduct(@RequestBody create: CreateProductDto): ResponseEntity<ProductResponse> {
        logger.info("Create new product")
        val product = productService.createProduct(create)
        return if (product == null) {
            ResponseEntity.badRequest().build()
        } else {
            ResponseEntity.ok(product)
        }
    }

    @PatchMapping("/product/{sku}", produces = ["application/json;charset=utf-8"])
    fun updateProduct(@PathVariable("sku") sku: String, @RequestBody update: UpdateProductDto): ResponseEntity<ProductResponse>  {
        logger.info("Update product ${sku}")
        val productUpdated = productService.updateProduct(update, sku)
        return if(productUpdated != null) {
            ResponseEntity.ok(productUpdated)
        } else {
            ResponseEntity.badRequest().build()
        }
    }

}
