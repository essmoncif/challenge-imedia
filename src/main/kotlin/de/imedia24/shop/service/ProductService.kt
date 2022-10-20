package de.imedia24.shop.service

import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.db.repository.ProductRepository
import de.imedia24.shop.domain.product.CreateProductDto
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.domain.product.ProductResponse.Companion.toProductResponse
import de.imedia24.shop.domain.product.UpdateProductDto
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun findProductBySku(sku: String): ProductResponse? {
        return productRepository.findBySku(sku)?.toProductResponse()
    }

    fun createProduct(product: CreateProductDto): ProductResponse? {
        val productEntity = ProductEntity.fromDto(product)
        return productRepository.save(productEntity)?.toProductResponse()
    }

    fun updateProduct(product: UpdateProductDto, sku: String): ProductResponse? {
        val defaultProduct = findProductBySku(sku)?.let { ProductEntity.fromDto(it) }
        if ( defaultProduct != null) {
            val productEntity = ProductEntity.fromDto(product, defaultProduct)
            return productRepository.save(productEntity)?.toProductResponse()
        }
        return null
    }
}
