package br.com.rchlo.store.repository;

import br.com.rchlo.store.domain.Product;
import br.com.rchlo.store.dto.product.ProductByColorDto;
import mother.ProductMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/schema.sql")
@ActiveProfiles("test")
class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void persistProducts() {
        entityManager.persist(ProductMother.aBlueJacket());
        entityManager.persist(ProductMother.aCheapTankTop());
        entityManager.persist(ProductMother.aTShirt());
    }

    @Test
    void shouldListAllProductsOrderedByName() {

        List<Product> products = productRepository.findAllByOrderByName();

        assertEquals(3, products.size());

        Product firstProduct = products.get(0);
        assertEquals(5L, firstProduct.getCode());
        assertEquals("Camiseta Azul", firstProduct.getName());

        Product secondProduct = products.get(1);
        assertEquals("Jaqueta Puffer Juvenil Com Capuz Super Mario Branco", secondProduct.getName());
        assertEquals(7L, secondProduct.getCode());

        Product thirdProduct = products.get(2);
        assertEquals(1L, thirdProduct.getCode());
        assertEquals("Regata Infantil Mario Bros Branco", thirdProduct.getName());



    }

    @Test
    void shouldReturnProductsByColor() {
        List<ProductByColorDto> productByColor = productRepository.productsByColor();

        ProductByColorDto whiteReport = productByColor.get(0);
        assertEquals("Branca", whiteReport.getColor());
        assertEquals(2L, whiteReport.getAmount());

        ProductByColorDto blueReport = productByColor.get(1);
        assertEquals("Azul", blueReport.getColor());
        assertEquals(1L, blueReport.getAmount());

    }
}