package com.rookies.nashtech.ShoppingCart.controller;

import com.rookies.nashtech.ShoppingCart.exception.NotFoundException;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Objects;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
        "/add-user.sql", "/add-category.sql", "/add-product.sql", "/add-order.sql", "/add-order-product.sql"
})
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {
        "/remove-order-product.sql", "/remove-order.sql", "/remove-product.sql", "/remove-category.sql", "/remove-user.sql"
})
public class OrderControllerIntTests {
  @Autowired
  MockMvc mockMvc;

  @DisplayName(value = "Test Get Order should work properly")
  @Nested
  @WithMockUser(username = "test", password = "1234")
  public class TestGetOrder {
    @Test
    public void testGetAllOrdersShouldReturnAllOrders() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders
                      .get("/api/v1/order/all"))
              .andDo(print())
              .andExpect(status().isOk())
              .andExpect(jsonPath("$").isArray())
              .andExpect(jsonPath("$", IsCollectionWithSize.hasSize(2)))
              .andExpect(jsonPath("$[*].id").isNotEmpty());
    }

    @ParameterizedTest(name = "Test Get Order by correct ID should return correct Order")
    @ValueSource(ints = {1, 2})
    public void testGetOrderByIdShouldReturnCorrectOrder(int orderId) throws Exception {
      mockMvc.perform(MockMvcRequestBuilders
                      .get("/api/v1/order/" + orderId)
                      .accept(MediaType.APPLICATION_JSON))
              .andDo(print())
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.id").isNotEmpty())
              .andExpect(jsonPath("$.username").isNotEmpty())
              .andExpect(jsonPath("$.dateCreated").isNotEmpty())
              .andExpect(jsonPath("$.totalCost").isNotEmpty())
              .andExpect(jsonPath("$.state").isNotEmpty());
    }

    @ParameterizedTest(name = "Test Get Order by invalid ID should throw NotFoundException")
    @ValueSource(ints = {10, 100, 1000})
    public void testGetOrderByIdGivenNonExistentIdShouldThrowNotFoundException(int orderId) throws Exception {
      mockMvc.perform(MockMvcRequestBuilders
                      .get("/api/v1/order/" + orderId)
                      .accept(MediaType.APPLICATION_JSON))
              .andDo(print())
              .andExpect(status().isNotFound())
              .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof NotFoundException))
              .andExpect(result -> Assertions.assertEquals(Objects.requireNonNull(result.getResolvedException()).getMessage(), "No order of id " + orderId));
    }
  }

  @DisplayName(value = "Test: Delete Order should work properly")
  @Nested
  @WithMockUser(username = "test", password = "1234")
  public class TestDeleteOrder {
    @ParameterizedTest(name = "Test Delete Order by correct ID should delete Order")
    @ValueSource(ints = {1, 2})
    public void testDeleteOrderByIdGivenValidIdShouldDeleteOrder(int orderId) throws Exception {
      mockMvc.perform(MockMvcRequestBuilders
                      .delete("/api/v1/order/" + orderId)
                      .accept(MediaType.APPLICATION_JSON))
              .andDo(print())
              .andExpect(status().isOk());
    }

    @ParameterizedTest(name = "Test Delete Order by invalid ID should throw exception")
    @ValueSource(ints = {10, 100, 1000})
    public void testDeleteOrderByIdGivenInvalidIdShouldThrowException(int orderId) throws Exception {
      mockMvc.perform(MockMvcRequestBuilders
                      .delete("/api/v1/order/" + orderId)
                      .accept(MediaType.APPLICATION_JSON))
              .andDo(print())
              .andExpect(status().isNotFound())
              .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof NotFoundException))
              .andExpect(result -> Assertions.assertEquals("No order of id " + orderId, Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
  }
}
