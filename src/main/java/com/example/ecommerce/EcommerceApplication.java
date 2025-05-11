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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
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
    @Autowired
    private ImagenRepository imagenRepository;

    @Bean
    @Transactional
    CommandLineRunner init(PaisRepository paisRepository,
                           ProvinciaRepository provinciaRepository,
                           LocalidadRepository localidadRepository,
                           DireccionRepository direccionRepository,
                           UsuarioRepository usuarioRepository,
                           ProductoRepository productoRepository,
                           DetalleProductoRepository detalleProductoRepository,
                           ImagenRepository imagenRepository,
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
                    .nombre("Roberto").apellido("Carlos").dni(467890123).rol(Rol.USUARIO)
                    .username("correo@gmail.com").password("sdsdsdsd")
                    .direccion(direccion_mendoza).build();
            Usuario usuario2 = Usuario.builder()
                    .nombre("Rodrigo").apellido("Mora").dni(34789123).rol(Rol.USUARIO)
                    .username("correo2@gmail.com").password("contraseña")
                    .direccion(direccion_cordoba).build();
            Usuario usuario3 = Usuario.builder()
                    .nombre("Esteban").apellido("Quito").dni(56777903).rol(Rol.USUARIO)
                    .username("esteban34@gmail.com").password("estebanquito")
                    .direccion(direccion_santa_fe).build();
            Usuario admin1 = Usuario.builder()
                    .nombre("Ernesto").apellido("Sabato").dni(24789123).rol(Rol.ADMIN)
                    .username("eladmin123@gmail.com").password("adminquebuentipo")
                    .direccion(direccion_mendoza).build();

            usuarioRepository.save(usuario1);
            usuarioRepository.save(usuario2);
            usuarioRepository.save(usuario3);
            usuarioRepository.save(admin1);

            // ----------------- Productos ------------------ //
            Producto producto1 = Producto.builder()
                    .nombre("Zapatillas Speedcat OG").tipoProducto(TipoProducto.CALZADO)
                    .sexo(Sexo.HOMBRE).precio_compra(160000)
                    .precio_venta(180000).build();
            Producto producto2 = Producto.builder()
                    .nombre("Zapatillas PUMA x HELLO KITTY Suede XL").tipoProducto(TipoProducto.CALZADO)
                    .sexo(Sexo.MUJER).precio_compra(19000)
                    .precio_venta(200000).build();
            Producto producto3 = Producto.builder()
                    .nombre("Buzo WARDROBE Essentials").tipoProducto(TipoProducto.ROPA)
                    .sexo(Sexo.HOMBRE).precio_compra(8000)
                    .precio_venta(90000).build();
            Producto producto4 = Producto.builder()
                    .nombre("Campera puffer oversize").tipoProducto(TipoProducto.ROPA)
                    .sexo(Sexo.MUJER).precio_compra(310000)
                    .precio_venta(330000).build();
            Producto producto5 = Producto.builder()
                    .nombre("Botines de fútbol FUTURE 8 ULTIMATE MxSG").tipoProducto(TipoProducto.CALZADO)
                    .sexo(Sexo.HOMBRE).precio_compra(330000)
                    .precio_venta(350000).build();

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

            // ------------------ Imagenes ------------------ //
            Imagen imagen1 = Imagen.builder()
                    .url("https://res.cloudinary.com/dk93fowya/image/upload/v1746976155/speedcat_qvh3gy.jpg")
                    .alt("Zapatilla Speedcat OG color negro").build();
            Imagen imagen2 = Imagen.builder()
                    .url("https://res.cloudinary.com/dk93fowya/image/upload/v1746976155/speedcat-2_qombn0.jpg")
                    .alt("Zapatilla Speedcat OG color negro").build();
            Imagen imagen3 = Imagen.builder()
                    .url("https://res.cloudinary.com/dk93fowya/image/upload/v1746977987/hello_kitty_zepctk.jpg")
                    .alt("Zapatillas PUMA x HELLO KITTY color negro").build();
            Imagen imagen4 = Imagen.builder()
                    .url("https://res.cloudinary.com/dk93fowya/image/upload/v1746977986/hello_kitty-2_ezmxie.jpg")
                    .alt("Zapatillas PUMA x HELLO KITTY color negro").build();
            Imagen imagen5 = Imagen.builder()
                    .url("https://res.cloudinary.com/dk93fowya/image/upload/v1746979624/wardrobe-1_itzkxm.jpg")
                    .alt("Buzo WARDROBE Essentials color negro").build();
            Imagen imagen6 = Imagen.builder()
                    .url("https://res.cloudinary.com/dk93fowya/image/upload/v1746979624/wardrobe_sntpop.jpg")
                    .alt("Buzo WARDROBE Essentials color negro").build();
            Imagen imagen7 = Imagen.builder()
                    .url("https://res.cloudinary.com/dk93fowya/image/upload/v1746980439/puffer_ddt91y.jpg")
                    .alt("Campera puffer oversize color beige").build();
            Imagen imagen8 = Imagen.builder()
                    .url("https://res.cloudinary.com/dk93fowya/image/upload/v1746980439/puffer-1_li1jwt.jpg")
                    .alt("Campera puffer oversize color beige").build();
            Imagen imagen9 = Imagen.builder()
                    .url("https://res.cloudinary.com/dk93fowya/image/upload/v1746980888/future_8_ultimate-1_eylmr1.jpg")
                    .alt("Botines de fútbol FUTURE 8 ULTIMATE").build();
            Imagen imagen10 = Imagen.builder()
                    .url("https://res.cloudinary.com/dk93fowya/image/upload/v1746980887/future_8_ultimate_k8wrhd.jpg")
                    .alt("Botines de fútbol FUTURE 8 ULTIMATE").build();

            imagenRepository.save(imagen1);
            imagenRepository.save(imagen2);
            imagenRepository.save(imagen3);
            imagenRepository.save(imagen4);
            imagenRepository.save(imagen5);
            imagenRepository.save(imagen6);
            imagenRepository.save(imagen7);
            imagenRepository.save(imagen8);
            imagenRepository.save(imagen9);
            imagenRepository.save(imagen10);

            // ----------------------- Detalle Producto --------------- //
            DetalleProducto speedcat_negro = DetalleProducto.builder()
                    .color("Negro").activo(true)
                    .producto(producto1).descuento(descuento10)
                    .imagenes(List.of(imagen1, imagen2)).build();
            DetalleProducto hello_kitty = DetalleProducto.builder()
                    .color("Negro").activo(true)
                    .producto(producto2).descuento(descuento10)
                    .imagenes(List.of(imagen3, imagen4)).build();
            DetalleProducto buzo_wardrobe = DetalleProducto.builder()
                    .color("Negro").activo(true)
                    .producto(producto3).descuento(descuento50)
                    .imagenes(List.of(imagen5, imagen6)).build();
            DetalleProducto campera_puffer = DetalleProducto.builder()
                    .color("Beige").activo(true)
                    .producto(producto4).descuento(descuento10)
                    .imagenes(List.of(imagen7, imagen8)).build();
            DetalleProducto botines_future = DetalleProducto.builder()
                    .color("Amarillo").activo(true)
                    .producto(producto5).descuento(descuento10)
                    .imagenes(List.of(imagen9, imagen10)).build();

            detalleProductoRepository.save(speedcat_negro);
            detalleProductoRepository.save(hello_kitty);
            detalleProductoRepository.save(buzo_wardrobe);
            detalleProductoRepository.save(campera_puffer);
            detalleProductoRepository.save(botines_future);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }
}
