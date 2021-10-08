package com.sparta.springcore.Integration;

import com.sparta.springcore.dto.ProductMypriceRequestDto;
import com.sparta.springcore.dto.ProductRequestDto;
import com.sparta.springcore.dto.SignupRequestDto;
import com.sparta.springcore.model.Product;
import com.sparta.springcore.model.User;
import com.sparta.springcore.service.ProductService;
import com.sparta.springcore.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static com.sparta.springcore.service.ProductService.MIN_MY_PRICE;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserProductIntegrationTest {
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    Long userId = 100L;
    Product createdProduct = null;
    int updatedMyPrice = -1;

    @Test
    @Order(1)
    @DisplayName("회원 가입전 상품 등록")
    void test1() {
// given
        userId = null;
        String title = "Apple <b>에어팟</b> 2세대 유선충전 모델 (MV7N2KH/A)";
        String imageUrl = "https://shopping-phinf.pstatic.net/main_1862208/18622086330.20200831140839.jpg";
        String linkUrl = "https://search.shopping.naver.com/gate.nhn?id=18622086330";
        int lPrice = 77000;
        ProductRequestDto requestDto = new ProductRequestDto(
                title,
                imageUrl,
                linkUrl,
                lPrice
        );

// when
//        Product product = productService.createProduct(requestDto, userId);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.createProduct(requestDto, userId);
        });

// then
        assertEquals(
                "회원 Id 가 유효하지 않습니다.",
                exception.getMessage()
        );
    }

    @Test
    @Order(2)
    @DisplayName("회원가입")
    void test2() {
// given
        SignupRequestDto signupRequestDto = new SignupRequestDto();
        signupRequestDto.setUsername("이승준");
        signupRequestDto.setPassword("password");
        signupRequestDto.setEmail("email");

// when

        User user = userService.registerUser(signupRequestDto);
// then
        String imsi =passwordEncoder.encode("password") ;
        System.out.println(imsi);
        System.out.println(user.getPassword());
        System.out.println(passwordEncoder.matches("password", user.getPassword()));
        assertNull(user.getKakaoId());
        assertEquals("이승준", user.getUsername());
        assertTrue(passwordEncoder.matches("password", user.getPassword()));
        assertEquals("email", user.getEmail());
//        assertEquals("ROLE_USER",user.getRole());

    }
    @Test
    @Order(3)
    @DisplayName("가입회원 상품 등록")
    void test3() {
// given
        userId = 100L;
        String title = "Apple <b>에어팟</b> 2세대 유선충전 모델 (MV7N2KH/A)";
        String imageUrl = "https://shopping-phinf.pstatic.net/main_1862208/18622086330.20200831140839.jpg";
        String linkUrl = "https://search.shopping.naver.com/gate.nhn?id=18622086330";
        int lPrice = 77000;
        ProductRequestDto requestDto = new ProductRequestDto(
                title,
                imageUrl,
                linkUrl,
                lPrice
        );

// when
        Product product = productService.createProduct(requestDto, userId);

// then
        assertNotNull(product.getId());
        assertEquals(userId, product.getUserId());
        assertEquals(title, product.getTitle());
        assertEquals(imageUrl, product.getImage());
        assertEquals(linkUrl, product.getLink());
        assertEquals(lPrice, product.getLprice());
        assertEquals(0, product.getMyprice());
        createdProduct = product;
    }
    @Test
    @Order(4)
    @DisplayName("상품 업데이트")
    void test4() {
// given
        Long productId = this.createdProduct.getId();
        int myPrice = 70000;
        ProductMypriceRequestDto requestDto = new ProductMypriceRequestDto(myPrice);

// when
        Product product = productService.updateProduct(productId, requestDto);

// then
        assertNotNull(product.getId());
        assertEquals(userId, product.getUserId());
        assertEquals(this.createdProduct.getTitle(), product.getTitle());
        assertEquals(this.createdProduct.getImage(), product.getImage());
        assertEquals(this.createdProduct.getLink(), product.getLink());
        assertEquals(this.createdProduct.getLprice(), product.getLprice());
        assertEquals(myPrice, product.getMyprice());
        this.updatedMyPrice = myPrice;
    }

    @Test
    @Order(5)
    @DisplayName("관심상품 조회")
    void test5() {
// given

// when
        //Long userId, int page, int size, String sortBy, boolean isAsc
        List<Product> productList = productService.getProducts_test(userId);

// then
// 1. 전체 상품에서 테스트에 의해 생성된 상품 찾아오기 (상품의 id 로 찾음)
        Long createdProductId = this.createdProduct.getId();
        Product foundProduct = productList.stream()
                .filter(product -> product.getId().equals(createdProductId))
                .findFirst()
                .orElse(null);

// 2. Order(1) 테스트에 의해 생성된 상품과 일치하는지 검증
        assertNotNull(foundProduct);
        assertEquals(userId, foundProduct.getUserId());
        assertEquals(this.createdProduct.getId(), foundProduct.getId());
        assertEquals(this.createdProduct.getTitle(), foundProduct.getTitle());
        assertEquals(this.createdProduct.getImage(), foundProduct.getImage());
        assertEquals(this.createdProduct.getLink(), foundProduct.getLink());
        assertEquals(this.createdProduct.getLprice(), foundProduct.getLprice());

// 3. Order(2) 테스트에 의해 myPrice 가격이 정상적으로 업데이트되었는지 검증
        assertEquals(this.updatedMyPrice, foundProduct.getMyprice());
    }

}
