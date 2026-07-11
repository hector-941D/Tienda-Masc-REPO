package com.Tienda_Masc_REPO.Producto.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.Tienda_Masc_REPO.Producto.Model.*;
import com.Tienda_Masc_REPO.Producto.Repository.ProductoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    void setUp(){

        Tipo tipo = new Tipo();
        tipo.setNombreDelTipo("Alimento");

        Marca marca = new Marca();
        marca.setNombreMarca("Purina");

        Especie especie = new Especie();
        especie.setNombreEspecie("Perro");

        producto = new Producto();
        producto.setIdProductos(1);
        producto.setNombreProductos("Dog Chow");
        producto.setPrecio(15000);
        producto.setTipo(tipo);
        producto.setMarca(marca);
        producto.setEspecie(especie);
    }

    @Test
    void buscarPorId(){
        when(productoRepository.findById(1))
                .thenReturn(Optional.of(producto));
        assertEquals("Dog Chow",
                productoService.buscarPorId(1).getNombreProductos());
        verify(productoRepository).findById(1);
    }
    @Test
    void obtenerTodos(){
        when(productoRepository.findAll())
                .thenReturn(List.of(producto));
        assertEquals(1,
                productoService.obtenerTodos().size());
        verify(productoRepository).findAll();
    }

    @Test
    void guardarProducto(){
        when(productoRepository.save(any(Producto.class)))
                .thenReturn(producto);
        assertEquals("Dog Chow",
                productoService.guardarProducto(producto).getNombreProductos());
        verify(productoRepository).save(producto);
    }

    @Test
    void eliminarProducto(){
        when(productoRepository.findById(1))
                .thenReturn(Optional.of(producto));
        String resultado = productoService.eliminarProducto(1);
        assertTrue(resultado.contains("exitosamente"));
        verify(productoRepository).delete(producto);
    }

    @Test
    void buscarPorTipo(){
        when(productoRepository.findByTipoNombreDelTipo("Alimento"))
                .thenReturn(List.of(producto));
        assertEquals(1,
                productoService.buscarPorTipo("Alimento").size());
        verify(productoRepository).findByTipoNombreDelTipo("Alimento");
    }
}