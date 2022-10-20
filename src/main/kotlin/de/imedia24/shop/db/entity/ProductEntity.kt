package de.imedia24.shop.db.entity

import de.imedia24.shop.domain.product.CreateProductDto
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.domain.product.UpdateProductDto
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.Random
import java.util.TimeZone
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "products")
data class ProductEntity(
    @Id
    @Column(name = "sku", nullable = false)
    val sku: String,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "description")
    val description: String? = null,

    @Column(name = "price", nullable = false)
    val price: BigDecimal,

    @UpdateTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: ZonedDateTime,

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    val updatedAt: ZonedDateTime

){
    constructor(): this ("", "", null, BigDecimal(0), ZonedDateTime.now(), ZonedDateTime.now())
    companion object {

        fun fromDto(create: UpdateProductDto, default: ProductEntity) = ProductEntity(
            sku = default.sku!!,
            name = create.name ?: default.name,
            description = create.description ?: default.description,
            price = create.price ?: default.price,
            createdAt = default.createdAt,
            updatedAt = ZonedDateTime.now()
        )

        fun fromDto(product: ProductResponse) = ProductEntity(
            sku = product.sku,
            name = product.name,
            description = product.description,
            price = product.price,
            createdAt = ZonedDateTime.now(),
            updatedAt = ZonedDateTime.now()
        )

        fun fromDto(create: CreateProductDto) = ProductEntity(
            sku = create.sku ?:  Random().nextInt(999).toString(),
            name = create.name,
            description = create.description,
            price = create.price,
            createdAt = ZonedDateTime.now(),
            updatedAt = ZonedDateTime.now()
        )
    }
}
