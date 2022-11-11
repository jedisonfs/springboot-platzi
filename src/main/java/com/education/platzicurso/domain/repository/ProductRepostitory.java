package com.education.platzicurso.domain.repository;

import com.education.platzicurso.domain.ProductDTO;
import com.education.platzicurso.persistence.entity.Product;

import java.util.List;
import java.util.Optional;

/**
 * Con esta interface se define las reglas que utilizara nuestra aplicacion
 * cuando cualquier repositorio quiera utilizar a productos en una base de datos.
 * Esto nos permite no acoplar la solucion a una base de datos especifica es decir nos
 * enfocamos a solo basar la aplicacion en Dominio
 *
 */

public interface ProductRepostitory {

    List<ProductDTO> getAll();
    Optional<List<ProductDTO>> getByCategory(int categpryId);
    Optional<List<ProductDTO>> getScarseProducts(int quantity);
    Optional<ProductDTO> getProduct(int productId);
    ProductDTO save(ProductDTO productDTO);
    void delete(int productId);

}
