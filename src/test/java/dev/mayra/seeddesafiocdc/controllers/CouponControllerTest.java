package dev.mayra.seeddesafiocdc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mayra.seeddesafiocdc.model.coupon.Coupon;
import dev.mayra.seeddesafiocdc.model.coupon.CouponRequestDTO;
import dev.mayra.seeddesafiocdc.repositories.CouponRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CouponController.class)
class CouponControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EntityManager entityManager;
    @MockBean
    private CouponRepository couponRepository;

    private CouponRequestDTO dto;
    private Coupon entity;

    @BeforeEach
    void setUp() {
        dto = new CouponRequestDTO("DISCOUNT50", 50, "31-12-2024");
        entity = new Coupon(dto.getCode(), dto.getPercentage(), LocalDate.parse("2024-12-31"));

        TypedQuery<Boolean> mockQuery = mock(TypedQuery.class);
        when(entityManager.createQuery(anyString(), any(Class.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter(anyString(), any())).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenReturn(false);
    }

    @Test
    void create__should_create_coupon_with_success() throws Exception {
        when(couponRepository.save(any(Coupon.class))).thenReturn(entity);

        mockMvc.perform(post("/coupon")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(dto.getCode()))
            .andExpect(jsonPath("$.percentage").value(dto.getPercentage()))
            .andExpect(jsonPath("$.expirationDate").value("2024-12-31"));
    }

    @ParameterizedTest
    @MethodSource("invalidCouponRequestDTOProvider")
    void create__should_throw_exception_for_invalid_fields(CouponRequestDTO invalidDto, String jsonPath, String errorMessage) throws Exception {
        when(couponRepository.save(any(Coupon.class))).thenReturn(entity);

        mockMvc.perform(post("/coupon")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDto)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath(jsonPath).value(errorMessage));
    }

    @Test
    void listAll__should_list_all_coupons_with_success() throws Exception {
        List<Coupon> coupons = List.of(entity);

        when(couponRepository.findAll()).thenReturn(coupons);

        mockMvc.perform(get("/coupon")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].code").value(dto.getCode()))
            .andExpect(jsonPath("$[0].percentage").value(dto.getPercentage()))
            .andExpect(jsonPath("$[0].expirationDate").value("2024-12-31"));
    }

    private static Stream<Arguments> invalidCouponRequestDTOProvider() {
        return Stream.of(
            Arguments.of(
                new CouponRequestDTO("", 50, "31-12-2024"),
                "$.errors.code",
                "Coupon code can't be null or empty"
            ),
            Arguments.of(
                new CouponRequestDTO("DISCOUNT50", null, "31-12-2024"),
                "$.errors.percentage",
                "Percentage can't be null"
            ),
            Arguments.of(
                new CouponRequestDTO("DISCOUNT50", -5, "31-12-2024"),
                "$.errors.percentage",
                "Percentage needs to be at least 1"
            ),
            Arguments.of(
                new CouponRequestDTO("DISCOUNT50", 50, null),
                "$.errors.expirationDate",
                "Expiration date can't be null"
            ),
            Arguments.of(
                new CouponRequestDTO("DISCOUNT50", 50, "01-01-2020"),
                "$.errors.expirationDate",
                "Expiration date needs to be in the future"
            )
        );
    }
}
