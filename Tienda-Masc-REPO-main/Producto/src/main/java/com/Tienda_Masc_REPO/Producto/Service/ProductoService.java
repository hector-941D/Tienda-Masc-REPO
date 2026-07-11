package com.Tienda_Masc_REPO.Producto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tienda_Masc_REPO.Producto.DTO.ProductoDTO;
import com.Tienda_Masc_REPO.Producto.Model.Producto;
import com.Tienda_Masc_REPO.Producto.Repository.ProductoRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    private ProductoDTO convertirADTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(producto.getIdProductos());
        dto.setNombreProductos(producto.getNombreProductos());
        dto.setPrecio(producto.getPrecio());
        dto.setNombreTipo(producto.getTipo().getNombreDelTipo());
        dto.setNombreMarca(producto.getMarca().getNombreMarca());
        dto.setNombreEspecie(producto.getEspecie().getNombreEspecie());

        return dto;
    }

    public List<ProductoDTO> obtenerTodos(){
        log.info("Obteniendo todos los productos");
        return productoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ProductoDTO buscarPorId(Integer id){
        log.info("Buscando producto con ID {}", id);
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> {
                log.warn("No se encontró el producto con ID {}", id);
                return new RuntimeException("Producto no encontrado");
            });
        log.info("Producto '{}' encontrado correctamente", producto.getNombreProductos());
        return convertirADTO(producto);  
    }

    public String eliminarProducto (Integer id){
        log.info("Intentando eliminar producto con ID {}", id);
        try {
            Producto producto = productoRepository.findById(id)
                    .orElseThrow(() -> {
                    log.warn("No se encontró el producto con ID {}", id);
                    return new RuntimeException("No se pudo eliminar. La ID " + id + " no existe");
                });
            productoRepository.delete(producto);
            log.info("Producto '{}' eliminado correctamente", producto.getNombreProductos());
            return "El Producto " + producto.getNombreProductos() + " Ha sido retirado exitosamente";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public ProductoDTO guardarProducto(Producto productos) {
        log.info("Guardando producto '{}'", productos.getNombreProductos());
        Producto producto = productoRepository.save(productos);
        log.info("Producto guardado con ID {}", producto.getIdProductos());
        return convertirADTO(producto);
    }

    public ProductoDTO actualizarProducto(Integer id, Producto producto) {
        log.info("Actualizando producto con ID {}", id);
        Producto productos = productoRepository.findById(id)
                .orElseThrow(() -> {
                log.warn("No se encontró el producto con ID {}", id);
                return new RuntimeException("No existe el producto");
            });
        if(producto.getNombreProductos()!= null){
            productos.setNombreProductos(producto.getNombreProductos());
        }
        if(producto.getPrecio()!= null){
            productos.setPrecio(producto.getPrecio());
        }
        Producto actualizado = productoRepository.save(productos);
        log.info("Producto '{}' actualizado correctamente", actualizado.getNombreProductos());
        return convertirADTO(actualizado);
    }

    public List<ProductoDTO> buscarPorTipo(String nombreDelTipo){
        log.info("Buscando productos del tipo '{}'", nombreDelTipo);
        List<ProductoDTO> productos = productoRepository.findByTipoNombreDelTipo(nombreDelTipo)
            .stream()
            .map(this::convertirADTO)
            .toList();
        log.info("Se encontraron {} productos del tipo '{}'", productos.size(), nombreDelTipo);
        return productos;
    }
}