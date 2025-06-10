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

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class EcommerceApplication {
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
   @Autowired
   private StockRepository stockRepository;

   @Bean
   @Transactional
   CommandLineRunner init(ProvinciaRepository provinciaRepository,
                          LocalidadRepository localidadRepository,
                          DireccionRepository direccionRepository,
                          UsuarioRepository usuarioRepository,
                          ProductoRepository productoRepository,
                          DetalleProductoRepository detalleProductoRepository,
                          ImagenRepository imagenRepository,
                          CategoriaRepository categoriaRepository,
                          DescuentoRepository descuentoRepository,
                          TalleRepository talleRepository,
                          StockRepository stockRepository,
                          OrdenCompraRepository ordenCompraRepository,
                          DetalleOrdenCompraController detalleOrdenCompraController,
                          PasswordEncoder passwordEncoder) {
      return args -> {
         // ------------- Provincias --------------- //
         Provincia mendoza = Provincia.builder().nombre("mendoza").build();
         Provincia cordoba = Provincia.builder().nombre("cordoba").build();

         provinciaRepository.save(mendoza);
         provinciaRepository.save(cordoba);

         // -------------- Localidades ---------------- //
         Localidad lujan = Localidad.builder().nombre("lujan de cuyo").provincia(mendoza).build();
         Localidad carlos_paz = Localidad.builder().nombre("carlos paz").provincia(cordoba).build();

         localidadRepository.save(lujan);
         localidadRepository.save(carlos_paz);

         // --------------- Direcciones ----------------- //
         Direccion direccion_mendoza = Direccion.builder().domicilio("san martín 1500")
               .localidad(lujan).build();
         Direccion direccion_cordoba = Direccion.builder().domicilio("av. san martín 200")
               .localidad(carlos_paz).build();

         direccionRepository.save(direccion_mendoza);
         direccionRepository.save(direccion_cordoba);

         // --------------- Usuarios ------------------- //
         Usuario usuario1 = Usuario.builder()
               .nombre("Roberto").apellido("Carlos").dni(467890123).rol(Rol.USUARIO)
               .username("correo@gmail.com").password(passwordEncoder.encode("sdsdsdsd"))
               .direccion(direccion_mendoza).build();
         Usuario usuario2 = Usuario.builder()
               .nombre("Rodrigo").apellido("Mora").dni(34789123).rol(Rol.USUARIO)
               .username("correo2@gmail.com").password(passwordEncoder.encode("contraseña"))
               .direccion(direccion_cordoba).build();
         Usuario admin1 = Usuario.builder()
               .nombre("Ernesto").apellido("Sabato").dni(24789123).rol(Rol.ADMIN)
               .username("eladmin123@gmail.com").password(passwordEncoder.encode("adminquebuentipo"))
               .direccion(direccion_mendoza).build();

         usuarioRepository.save(usuario1);
         usuarioRepository.save(usuario2);
         usuarioRepository.save(admin1);

         // -------------- Categoria ----------------- //
         Categoria running = Categoria.builder().nombre("Running").build();
         Categoria urbano = Categoria.builder().nombre("Urbano").build();
         Categoria training = Categoria.builder().nombre("Training").build();
         Categoria futbol = Categoria.builder().nombre("Futbol").build();

         categoriaRepository.save(running);
         categoriaRepository.save(urbano);
         categoriaRepository.save(training);
         categoriaRepository.save(futbol);

         // ----------------- Productos ------------------ //
         Producto producto1 = Producto.builder()
               .nombre("Zapatillas Speedcat OG").tipoProducto(TipoProducto.CALZADO)
               .sexo(Sexo.HOMBRE).categorias(List.of(urbano)).build();
         Producto producto2 = Producto.builder()
               .nombre("Zapatillas PUMA x HELLO KITTY Suede XL").tipoProducto(TipoProducto.CALZADO)
               .sexo(Sexo.MUJER).categorias(List.of(urbano, running)).build();
         Producto producto3 = Producto.builder()
               .nombre("Buzo WARDROBE Essentials").tipoProducto(TipoProducto.ROPA)
               .sexo(Sexo.HOMBRE).categorias(List.of(training)).build();
         Producto producto4 = Producto.builder()
               .nombre("Campera puffer oversize").tipoProducto(TipoProducto.ROPA)
               .sexo(Sexo.MUJER).categorias(List.of(urbano)).build();
         Producto producto5 = Producto.builder()
               .nombre("Botines de fútbol FUTURE 8 ULTIMATE MxSG").tipoProducto(TipoProducto.CALZADO)
               .sexo(Sexo.HOMBRE).categorias(List.of(futbol)).build();

         productoRepository.save(producto1);
         productoRepository.save(producto2);
         productoRepository.save(producto3);
         productoRepository.save(producto4);
         productoRepository.save(producto5);

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

         // ---------------- Talles --------------- //
         Talle l = Talle.builder().name("L").build();
         Talle xl = Talle.builder().name("XL").build();
         Talle xxl = Talle.builder().name("XXL").build();
         Talle talle1 = Talle.builder().name("39").build();
         Talle talle2 = Talle.builder().name("40").build();
         Talle talle3 = Talle.builder().name("41").build();

         talleRepository.save(l);
         talleRepository.save(xl);
         talleRepository.save(xxl);
         talleRepository.save(talle1);
         talleRepository.save(talle2);
         talleRepository.save(talle3);

         // ----------------------- Detalle Producto --------------- //
         DetalleProducto speedcat_negro = DetalleProducto.builder()
               .color("Negro").activo(true).precioCompra(100000).precioVenta(120000)
               .producto(producto1).descuento(descuento10)
               .imagenes(List.of(imagen1, imagen2)).build();
         DetalleProducto hello_kitty = DetalleProducto.builder()
               .color("Negro").activo(true).precioCompra(130000).precioVenta(150000)
               .producto(producto2).descuento(descuento10)
               .imagenes(List.of(imagen3, imagen4)).build();
         DetalleProducto buzo_wardrobe = DetalleProducto.builder()
               .color("Negro").activo(true).precioCompra(140000).precioVenta(160000)
               .producto(producto3).descuento(descuento50)
               .imagenes(List.of(imagen5, imagen6)).build();
         DetalleProducto campera_puffer = DetalleProducto.builder()
               .color("Beige").activo(true).precioCompra(170000).precioVenta(180000)
               .producto(producto4).descuento(descuento10)
               .imagenes(List.of(imagen7, imagen8)).build();
         DetalleProducto botines_future = DetalleProducto.builder()
               .color("Amarillo").activo(true).precioCompra(190000).precioVenta(220000)
               .producto(producto5).descuento(descuento10)
               .imagenes(List.of(imagen9, imagen10)).build();

         // ------------------------ Stock ------------------------- //
         Stock speedcat_negro_1 = Stock.builder()
               .stock(10).talle(talle1).build();
         Stock speedcat_negro_2 = Stock.builder()
               .stock(10).talle(talle2).build();

         Stock hello_kitty_1 = Stock.builder()
               .stock(10).talle(talle2).build();
         Stock hello_kitty_2 = Stock.builder()
               .stock(10).talle(talle3).build();

         Stock buzo_wardrobe_1 = Stock.builder()
               .stock(10).talle(l).build();
         Stock buzo_wardrobe_2 = Stock.builder()
               .stock(10).talle(xl).build();

         Stock campera_puffer_1 = Stock.builder()
               .stock(10).talle(xl).build();
         Stock campera_puffer_2 = Stock.builder()
               .stock(10).talle(xxl).build();

         Stock botines_future_1 = Stock.builder()
               .stock(10).talle(talle1).build();
         Stock botines_future_2 = Stock.builder()
               .stock(10).talle(talle2).build();

         // -------------------------------------------------------- //

         speedcat_negro.setStocks(List.of(speedcat_negro_1, speedcat_negro_2));
         hello_kitty.setStocks(List.of(hello_kitty_1, hello_kitty_2));
         buzo_wardrobe.setStocks(List.of(buzo_wardrobe_1, buzo_wardrobe_2));
         campera_puffer.setStocks(List.of(campera_puffer_1, campera_puffer_2));
         botines_future.setStocks(List.of(botines_future_1, botines_future_2));

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
