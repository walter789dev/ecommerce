package com.example.ecommerce;

import com.example.ecommerce.controllers.DetalleOrdenCompraController;
import com.example.ecommerce.entities.*;
import com.example.ecommerce.entities.enums.Rol;
import com.example.ecommerce.entities.enums.Sexo;
import com.example.ecommerce.entities.enums.TipoProducto;
import com.example.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class EcommerceApplication {

    @Autowired
    private PaisRepository paisRepository;
    @Autowired
    private ProvinciaRepository provinciaRepository;
    @Autowired
    private LocalidadRepository localidadRepository;
    @Autowired
    private DireccionRepository direccionRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private DetalleProductoRepository detalleProductoRepository;
    @Autowired
    private DescuentoRepository descuentoRepository;
    @Autowired
    private TalleRepository talleRepository;
    @Autowired
    private OrdenCompraRepository ordenCompraRepository;
    @Autowired
    private DetalleOrdenCompraController detalleOrdenCompraController;

    @Bean
    @Transactional
    CommandLineRunner init(PaisRepository paisRepository,
                           ProvinciaRepository provinciaRepository,
                           LocalidadRepository localidadRepository,
                           DireccionRepository direccionRepository,
                           UsuarioRepository usuarioRepository,
                           ProductoRepository productoRepository,
                           DetalleProductoRepository detalleProductoRepository,
                           CategoriaRepository categoriaRepository,
                           DescuentoRepository descuentoRepository,
                           TalleRepository talleRepository,
                           OrdenCompraRepository ordenCompraRepository,
                           DetalleOrdenCompraController detalleOrdenCompraController) {
        return args -> {
            // --------------- Paises ---------------- //
            Pais argentina = Pais.builder().nombre("Argentina").build();
            paisRepository.save(argentina);

            // ------------- Provincias --------------- //
            Provincia mendoza = Provincia.builder().nombre("Mendoza").pais(argentina).build();
            Provincia cordoba = Provincia.builder().nombre("Cordoba").pais(argentina).build();
            Provincia tucuman = Provincia.builder().nombre("Tucuman").pais(argentina).build();
            Provincia buenos_aires = Provincia.builder().nombre("Buenos Aires").pais(argentina).build();
            Provincia santa_fe = Provincia.builder().nombre("Santa Fe").pais(argentina).build();
            Provincia salta = Provincia.builder().nombre("Salta").pais(argentina).build();

            provinciaRepository.save(mendoza);
            provinciaRepository.save(cordoba);
            provinciaRepository.save(tucuman);
            provinciaRepository.save(buenos_aires);
            provinciaRepository.save(santa_fe);
            provinciaRepository.save(salta);

            // -------------- Localidades ---------------- //
            Localidad lujan = Localidad.builder().nombre("Lujan de Cuyo").codigoPostal(5505).provincia(mendoza).build();
            Localidad carlos_paz = Localidad.builder().nombre("Carlos Paz").codigoPostal(5152).provincia(cordoba).build();
            Localidad tafi = Localidad.builder().nombre("Tafi del Valle").codigoPostal(4137).provincia(tucuman).build();
            Localidad san_pedro = Localidad.builder().nombre("San Pedro").codigoPostal(2930).provincia(buenos_aires).build();
            Localidad rosario = Localidad.builder().nombre("Rosario").codigoPostal(2000).provincia(santa_fe).build();
            Localidad cafayate = Localidad.builder().nombre("Cafayate").codigoPostal(4427).provincia(salta).build();

            localidadRepository.save(lujan);
            localidadRepository.save(carlos_paz);
            localidadRepository.save(tafi);
            localidadRepository.save(san_pedro);
            localidadRepository.save(rosario);
            localidadRepository.save(cafayate);

            // --------------- Direcciones ----------------- //
            Direccion direccion_mendoza = Direccion.builder().domicilio("San Martín 1500").casa("C4")
                    .localidad(lujan).build();
            Direccion direccion_cordoba = Direccion.builder().domicilio("Av. San Martín 200").casa("F10")
                    .localidad(carlos_paz).build();
            Direccion direccion_santa_fe = Direccion.builder().domicilio("Corrientes 1000").casa("E25")
                    .localidad(rosario).build();

            direccionRepository.save(direccion_mendoza);
            direccionRepository.save(direccion_cordoba);
            direccionRepository.save(direccion_santa_fe);

            // --------------- Usuarios ------------------- //
            Usuario usuario1 = Usuario.builder()
                    .username("Roberto").apellido("Carlos").dni(467890123).rol(Rol.USUARIO)
                    .email("correo@gmail.com").password("sdsdsdsd")
                    .direccion(direccion_mendoza).build();
            Usuario usuario2 = Usuario.builder()
                    .username("Rodrigo").apellido("Mora").dni(34789123).rol(Rol.USUARIO)
                    .email("correo2@gmail.com").password("contraseña")
                    .direccion(direccion_cordoba).build();
            Usuario usuario3 = Usuario.builder()
                    .username("Esteban").apellido("Quito").dni(56777903).rol(Rol.USUARIO)
                    .email("esteban34@gmail.com").password("estebanquito")
                    .direccion(direccion_santa_fe).build();
            Usuario admin1 = Usuario.builder()
                    .username("Ernesto").apellido("Sabato").dni(24789123).rol(Rol.ADMIN)
                    .email("eladmin123@gmail.com").password("adminquebuentipo")
                    .direccion(direccion_mendoza).build();

            usuarioRepository.save(usuario1);
            usuarioRepository.save(usuario2);
            usuarioRepository.save(usuario3);
            usuarioRepository.save(admin1);

            // ----------------- Productos ------------------ //
            Producto producto1 = Producto.builder()
                    .nombre("Zapatillas PUMA x TORTUGAS NINJA Suede").tipoProducto(TipoProducto.CALZADO)
                    .sexo(Sexo.HOMBRE).precio_compra(190000)
                    .precio_venta(210000).build();
            Producto producto2 = Producto.builder()
                    .nombre("Zapatillas Ever FS CV").tipoProducto(TipoProducto.CALZADO)
                    .sexo(Sexo.MUJER).precio_compra(90000)
                    .precio_venta(95000).build();
            Producto producto3 = Producto.builder()
                    .nombre("Campera acolchada").tipoProducto(TipoProducto.ROPA)
                    .sexo(Sexo.HOMBRE).precio_compra(230000)
                    .precio_venta(260000).build();
            Producto producto4 = Producto.builder()
                    .nombre("Crop top PUMA STRONG").tipoProducto(TipoProducto.ROPA)
                    .sexo(Sexo.MUJER).precio_compra(50000)
                    .precio_venta(55000).build();
            Producto producto5 = Producto.builder()
                    .nombre("Botines Puma 90").tipoProducto(TipoProducto.CALZADO)
                    .sexo(Sexo.HOMBRE).precio_compra(60000)
                    .precio_venta(650000).build();

            productoRepository.save(producto1);
            productoRepository.save(producto2);
            productoRepository.save(producto3);
            productoRepository.save(producto4);
            productoRepository.save(producto5);

            // -------------- Categoria ----------------- //
            Categoria running = Categoria.builder().nombre("Running")
                    .productos(Arrays.asList(producto1, producto2)).build();
            Categoria urbano = Categoria.builder().nombre("Urbano")
                    .productos(Arrays.asList(producto1, producto2)).build();
            Categoria training = Categoria.builder().nombre("Entrenamiento")
                    .productos(List.of(producto4)).build();
            Categoria futbol = Categoria.builder().nombre("Futbol")
                    .productos(List.of(producto5)).build();

            categoriaRepository.save(running);
            categoriaRepository.save(urbano);
            categoriaRepository.save(training);
            categoriaRepository.save(futbol);

            // ---------------- Talles --------------- //
            Talle l = Talle.builder().name("L").build();
            Talle xl = Talle.builder().name("XL").build();
            Talle xxl = Talle.builder().name("XXL").build();
            Talle talle1 = Talle.builder().name("39").build();
            Talle talle2 = Talle.builder().name("40").build();

            talleRepository.save(l);
            talleRepository.save(xl);
            talleRepository.save(xxl);
            talleRepository.save(talle1);
            talleRepository.save(talle2);

            // -------------- Descuentos ---------------- //
            Descuento descuento50 = Descuento.builder().nombre("50%")
                    .fechaInicio(LocalDate.of(2025, 5, 10))
                    .fechaFin(LocalDate.of(2025, 5, 20))
                    .porcentaje(0.5).build();
            Descuento descuento10 = Descuento.builder().nombre("10%")
                    .fechaInicio(LocalDate.of(2025, 6, 1))
                    .fechaFin(LocalDate.of(2025, 6, 10))
                    .porcentaje(0.1).build();

            descuentoRepository.save(descuento10);
            descuentoRepository.save(descuento50);

            // ------------------ Detalle Producto ------------------ //
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }
}
