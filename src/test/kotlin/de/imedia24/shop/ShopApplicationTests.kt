package de.imedia24.shop

import com.ninjasquad.springmockk.MockkBean
import de.imedia24.shop.controller.ProductController
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.service.ProductService
import io.mockk.every
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal


@ExtendWith(SpringExtension::class)
@WebMvcTest
class ShopApplicationTests {

	@MockkBean
	private lateinit var productService: ProductService

	@Autowired
	private lateinit var productController: ProductController
	@Test
	fun `should get a list of products`() {
//		val refProduct = ProductResponse("123", "product 1", "Just a product", BigDecimal(123))
//		every { productService.findProductBySku("123")} returns refProduct
//
//		assertThat(productController.findProductBySku("123")).isEqualTo(ResponseEntity.ok(refProduct))

	}
}
