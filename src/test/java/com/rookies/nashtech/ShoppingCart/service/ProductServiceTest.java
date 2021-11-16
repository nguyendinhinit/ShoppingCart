package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.mapper.ProductMapper;
import com.rookies.nashtech.ShoppingCart.repository.ProductRepository;
import com.rookies.nashtech.ShoppingCart.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productsMapper;
    @InjectMocks
    ProductServiceImpl underTest;

    @DisplayName("Test findProductById() func")
    @Nested
    public class testFindProductByID {
        @Test
        public void findProductByIdReturnTrueWhenGiveExactlyID() {

        }


    }
}
