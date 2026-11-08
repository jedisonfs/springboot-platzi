package com.education.platzicurso.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ProductEntityRepostitoryTest {

    @Autowired
    private ProductRepostitory productRepostitory;

    @Test
    void getAll() {
        assertNotNull(productRepostitory.getAll());
    }
}