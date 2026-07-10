package com.Tienda_Masc_REPO.Boleta.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Tienda_Masc_REPO.Boleta.DTO.BoletaEntradaDTO;
import com.Tienda_Masc_REPO.Boleta.DTO.BoletaSalidaDTO;
import com.Tienda_Masc_REPO.Boleta.DTO.ClienteSalidaDTO;
import com.Tienda_Masc_REPO.Boleta.DTO.MetodoEnvioDTO;
import com.Tienda_Masc_REPO.Boleta.DTO.MetodoPagoDTO;
import com.Tienda_Masc_REPO.Boleta.DTO.ProductoSalidaDTO;
import com.Tienda_Masc_REPO.Boleta.model.Boleta;
import com.Tienda_Masc_REPO.Boleta.model.MetodoEnvio;
import com.Tienda_Masc_REPO.Boleta.model.MetodoPago;
import com.Tienda_Masc_REPO.Boleta.repository.BoletaRepository;
import com.Tienda_Masc_REPO.Boleta.services.BoletaService;
import com.Tienda_Masc_REPO.Boleta.services.BoletaValidaciones;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
class BoletaApplicationTests {

	@Mock
	private BoletaRepository boletaRepository;

    @Mock
    private BoletaValidaciones boletaValidaciones;
	
	@InjectMocks
	private BoletaService boletaService; 
	private Faker faker = new Faker(); 
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testBuscarPorId_Exitoso() {
		
		Integer idBoletaSimulado = faker.number().numberBetween(1, 500);
        Integer idClienteSimulado = faker.number().numberBetween(1, 500);
        Integer idProductoSimulado = faker.number().numberBetween(1, 500);
        String folioAleatorio = faker.bothify("######");
        

        int neto = 10000;              
        int iva = (int) (neto * 0.19); 
        int total = neto + iva;

		Boleta boletaFalsa = new Boleta();

        MetodoPago metodoPagoFalso = new MetodoPago();
        metodoPagoFalso.setIdMetodoPago(faker.number().numberBetween(1, 500));
        metodoPagoFalso.setNombreMetodoPago("Efectivo");

        MetodoEnvio metodoEnvioFalso = new MetodoEnvio();
        metodoEnvioFalso.setIdMetodoEnvio(faker.number().numberBetween(1, 500));
        metodoEnvioFalso.setTipoEnvio("Delivery");
        metodoEnvioFalso.setCostoEnvio(3990);

        MetodoPagoDTO metodoPagoDtoFalso = new MetodoPagoDTO();
        metodoPagoDtoFalso.setIdMetodoPago(metodoPagoFalso.getIdMetodoPago());
        metodoPagoDtoFalso.setNombreMetodoPago("Efectivo");

        MetodoEnvioDTO metodoEnvioDtoFalso = new MetodoEnvioDTO();
        metodoEnvioDtoFalso.setIdMetodoEnvio(metodoEnvioFalso.getIdMetodoEnvio());
        metodoEnvioDtoFalso.setTipoEnvio("Delivery");
        metodoEnvioDtoFalso.setCostoEnvio(3990);

		boletaFalsa.setIdBoleta(idBoletaSimulado);
		boletaFalsa.setIdCliente(idClienteSimulado);
        boletaFalsa.setIdProducto(idProductoSimulado);
        boletaFalsa.setNumeroFolio(folioAleatorio);
        boletaFalsa.setMetodoPago(metodoPagoFalso);
		boletaFalsa.setMetodoEnvio(metodoEnvioFalso);
        boletaFalsa.setHoraEmision(LocalTime.of(15, 30, 45));
        boletaFalsa.setFechaEmision(LocalDate.of(2001, 07, 28));
        boletaFalsa.setMontoNeto(neto);
        boletaFalsa.setMontoIva(iva);
        boletaFalsa.setMontoTotal(total);

        BoletaSalidaDTO dtoFalso = new BoletaSalidaDTO();
        dtoFalso.setNumeroFolio(folioAleatorio);
        dtoFalso.setFechaEmision(LocalDate.of(2001, 7, 28));
        dtoFalso.setHoraEmision(LocalTime.of(15, 30, 45));
        dtoFalso.setMetodoPago(metodoPagoDtoFalso);
        dtoFalso.setMetodoEnvio(metodoEnvioDtoFalso);
        dtoFalso.setMontoNeto(neto);
        dtoFalso.setMontoTotal(total);

		when(boletaRepository.findById(idBoletaSimulado)).thenReturn(Optional.of(boletaFalsa));

        when(boletaValidaciones.convertirADTO(boletaFalsa)).thenReturn(dtoFalso);

		BoletaSalidaDTO resultado = boletaService.buscarPorId(idBoletaSimulado);

		assertNotNull(resultado, "El DTO resultante no debería ser nulo");
		assertEquals(folioAleatorio, resultado.getNumeroFolio(), "El nombre transformado al DTO debe coincidir con el de la DB");
        assertEquals(LocalDate.of(2001, 7, 28), resultado.getFechaEmision(), "La fecha de emisión debe ser la misma");
        assertEquals(LocalTime.of(15, 30, 45), resultado.getHoraEmision(), "La hora de emisión debe ser la misma");
        assertNotNull(resultado.getMetodoPago(), "El método de pago no debería ser nulo en el DTO");
        assertEquals("Efectivo", resultado.getMetodoPago().getNombreMetodoPago(), "El nombre del método de pago debe coincidir");
        assertNotNull(resultado.getMetodoEnvio(), "El método de envío no debería ser nulo en el DTO");
        assertEquals("Delivery", resultado.getMetodoEnvio().getTipoEnvio(), "El tipo de envío debe coincidir");

		verify(boletaRepository, times(1)).findById(idBoletaSimulado);
	}

    @Test
    void testCrearBoleta_Exitoso() {
        BoletaEntradaDTO entradaDTO = new BoletaEntradaDTO();
        entradaDTO.setIdCliente(faker.number().numberBetween(1, 100));
        entradaDTO.setIdProducto(faker.number().numberBetween(1, 100));
        entradaDTO.setMontoNeto(10000); 

        Boleta boletaUltimaEnBD = new Boleta();
        boletaUltimaEnBD.setNumeroFolio("000042");

        ClienteSalidaDTO clienteExterno = new ClienteSalidaDTO();
        clienteExterno.setIdCliente(entradaDTO.getIdCliente());
        clienteExterno.setNombreCliente(faker.name().fullName());
        clienteExterno.setRunCliente(faker.idNumber().valid());
        clienteExterno.setRegion(faker.address().state());
        clienteExterno.setComuna(faker.address().city());

        ProductoSalidaDTO productoExterno = new ProductoSalidaDTO();
        productoExterno.setIdProducto(entradaDTO.getIdProducto());
        productoExterno.setNombreProductos(faker.commerce().productName());
        productoExterno.setMarca(faker.commerce().brand());
        productoExterno.setPrecio(11900);

        BoletaSalidaDTO dtoDeSalidaFalso = new BoletaSalidaDTO();
        dtoDeSalidaFalso.setNumeroFolio("000043"); 
        dtoDeSalidaFalso.setMontoNeto(10000);
        dtoDeSalidaFalso.setMontoTotal(11900);

        when(boletaRepository.findFirstByOrderByIdBoletaDesc()).thenReturn(Optional.of(boletaUltimaEnBD));
        
        when(boletaRepository.save(any(Boleta.class))).thenAnswer(invocation -> invocation.getArgument(0));

        when(boletaValidaciones.convertirADTO(any(Boleta.class))).thenReturn(dtoDeSalidaFalso);
        when(boletaValidaciones.obtenerClienteExterno(entradaDTO.getIdCliente())).thenReturn(clienteExterno);
        when(boletaValidaciones.obtenerProductoExterno(entradaDTO.getIdProducto())).thenReturn(productoExterno);

        BoletaSalidaDTO resultado = boletaService.crearBoleta(entradaDTO);

        assertNotNull(resultado, "La boleta creada no debería ser nula");
        assertEquals("000043", resultado.getNumeroFolio(), "El folio generado debió incrementar en 1");
        
        assertNotNull(resultado.getCliente(), "El cliente no debería ser nulo en la salida");
        assertEquals(clienteExterno.getNombreCliente(), resultado.getCliente().getNombreCliente());
        
        assertNotNull(resultado.getProducto(), "El producto no debería ser nulo en la salida");
        assertEquals(productoExterno.getNombreProductos(), resultado.getProducto().getNombreProductos());

        verify(boletaRepository, times(1)).save(any(Boleta.class));
    }

    @Test
    void testObtenerTodas_Exitoso() {
        
        Boleta b1 = new Boleta();
        b1.setNumeroFolio("000001");
        Boleta b2 = new Boleta();
        b2.setNumeroFolio("000002");
        
        List<Boleta> listaDeBaseDatos = List.of(b1, b2);

        BoletaSalidaDTO dto1 = new BoletaSalidaDTO();
        dto1.setNumeroFolio("000001");
        BoletaSalidaDTO dto2 = new BoletaSalidaDTO();
        dto2.setNumeroFolio("000002");

        when(boletaRepository.findAll()).thenReturn(listaDeBaseDatos);

        when(boletaValidaciones.convertirADTO(b1)).thenReturn(dto1);
        when(boletaValidaciones.convertirADTO(b2)).thenReturn(dto2);

        List<BoletaSalidaDTO> resultado = boletaService.obtenerTodas();

        assertNotNull(resultado, "La lista resultante no debe ser nula");
        assertEquals(2, resultado.size(), "La lista debe contener exactamente 2 boletas");
        assertEquals("000001", resultado.get(0).getNumeroFolio());
        assertEquals("000002", resultado.get(1).getNumeroFolio());

        verify(boletaRepository, times(1)).findAll();
    }
}    
