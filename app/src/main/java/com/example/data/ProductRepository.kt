package com.example.data

import com.example.model.Category
import com.example.model.Product

object ProductRepository {

    val products: List<Product> = listOf(
        // MONITORES
        Product(
            id = "mon-001",
            name = "Monitor ASUS ROG Swift OLED PG27AQDM 27\"",
            brand = "ASUS ROG",
            category = Category.MONITORES,
            priceSoles = 3299.0,
            originalPriceSoles = 3699.0,
            rating = 4.9f,
            reviewCount = 142,
            description = "Monitor gaming de élite con panel OLED 1440p de 27 pulgadas, tasa de refresco ultra rápida de 240Hz y tiempo de respuesta récord de 0.03ms. Disipador térmico personalizado inteligente y soporte HDR10.",
            specs = mapOf(
                "Tamaño" to "27 pulgadas QHD (2560 x 1440)",
                "Panel" to "OLED Antirreflejo",
                "Tasa de Refresco" to "240 Hz",
                "Tiempo Respuesta" to "0.03 ms GTG",
                "G-Sync / FreeSync" to "Compatible G-Sync & FreeSync Premium",
                "Puertos" to "2x HDMI 2.0, 1x DisplayPort 1.4, USB 3.2 Hub"
            ),
            highlights = listOf("Panel OLED 240Hz", "0.03ms de respuesta", "Colores DCI-P3 99%", "Disipador térmico exclusivo"),
            stock = 4,
            isFeatured = true,
            warrantyMonths = 24,
            tags = listOf("monitor", "pantalla", "asus", "rog", "oled", "240hz", "qhd", "2k", "gamer", "gaming", "esports")
        ),
        Product(
            id = "mon-002",
            name = "Monitor LG UltraGear 27GR75Q-B 27\" IPS 165Hz",
            brand = "LG",
            category = Category.MONITORES,
            priceSoles = 999.0,
            originalPriceSoles = 1180.0,
            rating = 4.8f,
            reviewCount = 285,
            description = "El favorito de la comunidad gamer en Perú. Panel IPS con resolución 2K QHD, 165Hz de refresco con 1ms MBR, compatibilidad con NVIDIA G-Sync y AMD FreeSync Premium para partidas ultra fluidas.",
            specs = mapOf(
                "Tamaño" to "27 pulgadas QHD (2560 x 1440)",
                "Panel" to "IPS con sRGB 99%",
                "Tasa de Refresco" to "165 Hz",
                "Tiempo Respuesta" to "1 ms GTG",
                "Tecnología" to "HDR10, G-Sync Compatible",
                "Conectividad" to "2x HDMI 2.0, 1x DP 1.4, Audio Out"
            ),
            highlights = listOf("Excelente balance calidad/precio", "Panel IPS nítido 2K", "165Hz de fluidez", "Ergonomía regulable en altura"),
            stock = 12,
            isFeatured = true,
            warrantyMonths = 24,
            tags = listOf("monitor", "pantalla", "lg", "ultragear", "ips", "165hz", "2k", "qhd", "gamer", "barato", "calidad")
        ),
        Product(
            id = "mon-003",
            name = "Monitor Samsung Odyssey G5 32\" Curvo 165Hz",
            brand = "Samsung",
            category = Category.MONITORES,
            priceSoles = 1290.0,
            originalPriceSoles = 1450.0,
            rating = 4.7f,
            reviewCount = 118,
            description = "Inmersión total en 32 pulgadas con curvatura 1000R que coincide con el campo visual humano. Resolución WQHD, HDR10 y 165Hz para disfrutar simuladores, shooters y productividad multitarea.",
            specs = mapOf(
                "Tamaño" to "32 pulgadas Curvo 1000R",
                "Resolución" to "WQHD (2560 x 1440)",
                "Tasa de Refresco" to "165 Hz",
                "Tiempo Respuesta" to "1 ms",
                "HDR" to "HDR10",
                "Puertos" to "1x HDMI 2.0, 1x DP 1.2"
            ),
            highlights = listOf("Curvatura 1000R inmersiva", "Gran formato de 32\"", "FreeSync Premium", "Modo Eye Saver antiparpadeo"),
            stock = 7,
            isFeatured = false,
            warrantyMonths = 12,
            tags = listOf("monitor", "pantalla", "samsung", "odyssey", "curvo", "32", "165hz", "wqhd", "inmersivo")
        ),
        Product(
            id = "mon-004",
            name = "Monitor MSI Optix G2412 24\" FHD 170Hz Esports",
            brand = "MSI",
            category = Category.MONITORES,
            priceSoles = 580.0,
            originalPriceSoles = 660.0,
            rating = 4.6f,
            reviewCount = 94,
            description = "Monitor competitivo para jugadores de Valorant, CS2 y Dota 2. Panel IPS de 24 pulgadas, 170Hz y 1ms, con tecnología Night Vision para ver enemigos en zonas oscuras del mapa.",
            specs = mapOf(
                "Tamaño" to "23.8 pulgadas Full HD (1920 x 1080)",
                "Panel" to "IPS 170Hz overclock",
                "Tiempo Respuesta" to "1 ms MPRT",
                "Gama de Color" to "DCI-P3 86.52% / sRGB 102.58%",
                "Bisel" to "Diseño sin bordes Frameless"
            ),
            highlights = listOf("Súper accesible y veloz", "Ideal para shooters competitivos", "170Hz fluido", "Panel IPS con ángulos amplios"),
            stock = 15,
            isFeatured = false,
            warrantyMonths = 12,
            tags = listOf("monitor", "pantalla", "msi", "24", "fhd", "1080p", "170hz", "economico", "esports", "valorant")
        ),

        // PROCESADORES
        Product(
            id = "cpu-001",
            name = "Procesador AMD Ryzen 7 7800X3D (AM5)",
            brand = "AMD",
            category = Category.PROCESADORES,
            priceSoles = 1799.0,
            originalPriceSoles = 1950.0,
            rating = 5.0f,
            reviewCount = 312,
            description = "El procesador indiscutiblemente rey del gaming mundial. Con tecnología 3D V-Cache de 96MB L3, 8 núcleos y 16 hilos en arquitectura Zen 4 de 5nm. Máximos FPS en cualquier título competitivo y AAA.",
            specs = mapOf(
                "Socket" to "AM5 (Plataforma DDR5)",
                "Núcleos / Hilos" to "8 Núcleos / 16 Hilos",
                "Frecuencia Base / Turbo" to "4.2 GHz / hasta 5.0 GHz",
                "Caché Total" to "104 MB (96 MB 3D V-Cache L3)",
                "TDP" to "120W",
                "Gráficos Integrados" to "AMD Radeon Graphics (RDNA 2)"
            ),
            highlights = listOf("El mejor CPU para juegos del mundo", "96MB 3D V-Cache", "Consumo energético eficiente", "Compatible con placas B650 y X670"),
            stock = 6,
            isFeatured = true,
            warrantyMonths = 36,
            tags = listOf("procesador", "cpu", "amd", "ryzen", "7800x3d", "am5", "gamer", "gaming", "3d v-cache", "zen 4")
        ),
        Product(
            id = "cpu-002",
            name = "Procesador Intel Core i7-14700K (LGA1700)",
            brand = "Intel",
            category = Category.PROCESADORES,
            priceSoles = 1720.0,
            originalPriceSoles = 1880.0,
            rating = 4.8f,
            reviewCount = 180,
            description = "Potencia híbrida extrema para creadores de contenido, streamers y gamers exigentes. 20 núcleos (8 de rendimiento + 12 de eficiencia) y hasta 5.6 GHz con Turbo Boost Max 3.0.",
            specs = mapOf(
                "Socket" to "LGA1700",
                "Núcleos / Hilos" to "20 Núcleos (8P + 12E) / 28 Hilos",
                "Frecuencia Máxima" to "Hasta 5.6 GHz",
                "Caché Intel Smart" to "33 MB L3 + 28 MB L2",
                "Memoria" to "Soporte DDR4 y DDR5 hasta 5600 MT/s",
                "Gráficos" to "Intel UHD Graphics 770"
            ),
            highlights = listOf("20 núcleos de alto rendimiento", "Ideal para render 3D, edición 4K y streaming", "Hasta 5.6 GHz", "Overclocking desbloqueado"),
            stock = 8,
            isFeatured = true,
            warrantyMonths = 36,
            tags = listOf("procesador", "cpu", "intel", "core", "i7", "14700k", "14va", "lga1700", "render", "edicion", "streaming")
        ),
        Product(
            id = "cpu-003",
            name = "Procesador AMD Ryzen 5 7600X (AM5)",
            brand = "AMD",
            category = Category.PROCESADORES,
            priceSoles = 890.0,
            originalPriceSoles = 990.0,
            rating = 4.8f,
            reviewCount = 205,
            description = "La puerta de entrada ideal a la nueva generación AM5 y memoria DDR5. 6 núcleos y 12 hilos con frecuencias que rozan los 5.3 GHz para un rendimiento brutal en juegos a precio inteligente.",
            specs = mapOf(
                "Socket" to "AM5",
                "Núcleos / Hilos" to "6 Núcleos / 12 Hilos",
                "Frecuencia Turbo" to "Hasta 5.3 GHz",
                "Caché L3" to "32 MB",
                "TDP" to "105W",
                "Litografía" to "TSMC 5nm FinFET"
            ),
            highlights = listOf("Relación costo/beneficio imbatible", "Soporte PCIe 5.0 y DDR5", "Alta frecuencia mono-núcleo", "Listo para upgrades futuros en AM5"),
            stock = 14,
            isFeatured = false,
            warrantyMonths = 36,
            tags = listOf("procesador", "cpu", "amd", "ryzen 5", "7600x", "am5", "ddr5", "economico", "calidad precio")
        ),
        Product(
            id = "cpu-004",
            name = "Procesador Intel Core i5-13400F (LGA1700)",
            brand = "Intel",
            category = Category.PROCESADORES,
            priceSoles = 740.0,
            originalPriceSoles = 820.0,
            rating = 4.7f,
            reviewCount = 140,
            description = "El procesador más vendido para armados de PC equilibrados. 10 núcleos (6P + 4E) y 16 hilos, incluye disipador Intel Laminar RM1 de fábrica y gran compatibilidad con placas B760.",
            specs = mapOf(
                "Socket" to "LGA1700",
                "Núcleos / Hilos" to "10 Núcleos (6P + 4E) / 16 Hilos",
                "Frecuencia Turbo" to "Hasta 4.6 GHz",
                "Caché" to "20 MB Intel Smart Cache",
                "Disipador" to "Incluye Intel Laminar RM1",
                "TDP Base" to "65W (eficiente y silencioso)"
            ),
            highlights = listOf("Excelente precio", "Incluye disipador de fábrica", "Bajo consumo y temperaturas frescas", "Excelente para emparejar con RTX 4060"),
            stock = 11,
            isFeatured = false,
            warrantyMonths = 36,
            tags = listOf("procesador", "cpu", "intel", "core", "i5", "13400f", "barato", "economico", "pc gamer")
        ),

        // TECLADOS
        Product(
            id = "tec-001",
            name = "Teclado Mecánico Razer Huntsman V2 TKL Óptico",
            brand = "Razer",
            category = Category.TECLADOS,
            priceSoles = 480.0,
            originalPriceSoles = 560.0,
            rating = 4.9f,
            reviewCount = 210,
            description = "Velocidad lumínica con switches ópticos lineales Razer de 2da generación amortiguados con sonido silenciado. Tasa de sondeo real de 8000Hz, teclas PBT de doble inyección y reposamuñecas ergonómico.",
            specs = mapOf(
                "Formato" to "Tenkeyless (TKL 80%)",
                "Switches" to "Razer Red Linear Optical Gen-2",
                "Polling Rate" to "8000 Hz HyperPolling",
                "Teclas" to "PBT Double-Shot duraderas",
                "Iluminación" to "Razer Chroma RGB por tecla",
                "Cable" to "USB-C trenzado desmontable"
            ),
            highlights = listOf("Respuesta instantánea a la velocidad de la luz", "Espuma amortiguadora de sonido", "Reposamuñecas magnético incluido", "Teclas PBT que no se desgastan"),
            stock = 9,
            isFeatured = true,
            warrantyMonths = 24,
            tags = listOf("teclado", "mecanico", "razer", "huntsman", "optico", "tkl", "rgb", "gamer", "silencioso", "periferico")
        ),
        Product(
            id = "tec-002",
            name = "Teclado Corsair K70 RGB PRO Cherry MX Red",
            brand = "Corsair",
            category = Category.TECLADOS,
            priceSoles = 620.0,
            originalPriceSoles = 690.0,
            rating = 4.8f,
            reviewCount = 135,
            description = "Un clásico legendario con chasis de aluminio cepillado de grado aeronáutico, switches originales mecánicos alemanes Cherry MX Red, rueda de volumen dedicada y botón de torneo exclusivo.",
            specs = mapOf(
                "Formato" to "Completo (100% con teclado numérico)",
                "Switches" to "Cherry MX Red mecánicos lineales",
                "Procesamiento" to "Corsair AXON 8000Hz",
                "Estructura" to "Aluminio anodizado negro mate",
                "Controles Multimedia" to "Rueda de volumen metálica y teclas de control",
                "Memoria Integrada" to "8 MB para hasta 50 perfiles"
            ),
            highlights = listOf("Chasis de aluminio súper robusto", "Switches Cherry MX originales", "Controles de volumen dedicados", "Modo torneo de bloqueo rápido"),
            stock = 5,
            isFeatured = false,
            warrantyMonths = 24,
            tags = listOf("teclado", "mecanico", "corsair", "k70", "cherry mx", "aluminio", "rgb", "completo")
        ),
        Product(
            id = "tec-003",
            name = "Teclado Inalámbrico Logitech G PRO X TKL Lightspeed",
            brand = "Logitech G",
            category = Category.TECLADOS,
            priceSoles = 690.0,
            originalPriceSoles = 780.0,
            rating = 4.9f,
            reviewCount = 98,
            description = "Diseñado junto a los mejores profesionales de esports del mundo. Conexión inalámbrica ultrarrápida Lightspeed de 1ms, switches táctiles GX Brown, batería de 50 horas y estuche rígido de viaje.",
            specs = mapOf(
                "Formato" to "Tenkeyless profesional (TKL)",
                "Conectividad" to "Lightspeed 2.4GHz + Bluetooth + USB-C",
                "Batería" to "Hasta 50 horas continuas",
                "Switches" to "GX Brown Táctil",
                "Iluminación" to "LIGHTSYNC RGB",
                "Accesorios" to "Incluye estuche de transporte premium"
            ),
            highlights = listOf("Cero latencia Lightspeed", "Batería de larga duración", "Estuche rígido incluido", "Ideal para torneos y setups limpios"),
            stock = 6,
            isFeatured = true,
            warrantyMonths = 24,
            tags = listOf("teclado", "inalambrico", "logitech", "g pro x", "lightspeed", "tkl", "bluetooth", "esports")
        ),
        Product(
            id = "tec-004",
            name = "Teclado Mecánico Redragon Kumara K552 RGB",
            brand = "Redragon",
            category = Category.TECLADOS,
            priceSoles = 169.0,
            originalPriceSoles = 199.0,
            rating = 4.7f,
            reviewCount = 420,
            description = "El teclado mecánico económico favorito del Perú. Estructura de plástico ABS reforzado con placa de acero, switches Outemu Red lineales intercambiables (Hot-Swap) e iluminación RGB.",
            specs = mapOf(
                "Formato" to "TKL compacto 87 teclas (Distribución Español con Ñ)",
                "Switches" to "Outemu Red (Lineal suave, intercambiable)",
                "Retroiluminación" to "RGB Chroma con 18 efectos",
                "Resistencia" to "A prueba de salpicaduras",
                "Conexión" to "USB enchapado en oro"
            ),
            highlights = listOf("El rey de la gama de entrada", "Diseño en español con tecla Ñ", "Hot-swap con repuestos incluidos", "Cuerpo reforzado con placa metálica"),
            stock = 25,
            isFeatured = false,
            warrantyMonths = 12,
            tags = listOf("teclado", "mecanico", "redragon", "kumara", "k552", "economico", "barato", "espanol", "hot-swap")
        ),

        // MOUSE
        Product(
            id = "mou-001",
            name = "Mouse Logitech G502 X PLUS Lightspeed Inalámbrico",
            brand = "Logitech G",
            category = Category.MOUSE,
            priceSoles = 510.0,
            originalPriceSoles = 580.0,
            rating = 4.9f,
            reviewCount = 340,
            description = "La evolución de la leyenda. Equipado con switches híbridos óptico-mecánicos LIGHTFORCE, sensor HERO 25K de precisión submicrónica, iluminación RGB activa de 8 zonas y botón DPI shift reversible.",
            specs = mapOf(
                "Sensor" to "HERO 25K (100 - 25,600 DPI)",
                "Switches" to "LIGHTFORCE Óptico-Mecánicos híbridos",
                "Peso" to "89 gramos optimizado",
                "Batería" to "Hasta 120 horas (RGB off) / 37 horas (RGB on)",
                "Botones" to "13 botones totalmente programables",
                "Rueda" to "Rueda hiperrápida de doble modo (giro libre / pasos)"
            ),
            highlights = listOf("Switches óptico-mecánicos sin doble clic", "13 botones programables", "Rueda de desplazamiento infinito", "Autonomía de hasta 120 horas"),
            stock = 8,
            isFeatured = true,
            warrantyMonths = 24,
            tags = listOf("mouse", "raton", "logitech", "g502", "g502x", "inalambrico", "lightspeed", "hero 25k", "gamer")
        ),
        Product(
            id = "mou-002",
            name = "Mouse Razer DeathAdder V3 Pro Inalámbrico Ultraligero",
            brand = "Razer",
            category = Category.MOUSE,
            priceSoles = 499.0,
            originalPriceSoles = 570.0,
            rating = 4.9f,
            reviewCount = 195,
            description = "Ergonomía icónica perfeccionada con un peso pluma de tan solo 63 gramos sin perforaciones. Sensor óptico Focus Pro 30K de máxima precisión, switches ópticos Gen-3 y hasta 90 horas de batería.",
            specs = mapOf(
                "Peso" to "63 gramos ultraligero",
                "Sensor" to "Razer Focus Pro 30K Óptico (30,000 DPI)",
                "Switches" to "Razer Ópticos Gen-3 (90 millones de clics)",
                "Velocidad / Aceleración" to "750 IPS / 70 G",
                "Batería" to "Hasta 90 horas con USB-C",
                "Pies de Mouse" to "100% PTFE virgen de deslizamiento suave"
            ),
            highlights = listOf("Peso pluma de 63g", "Ergonomía perfecta para manos medianas/grandes", "Sensor óptico más preciso del mercado", "Switches de 90M clics sin fallo"),
            stock = 7,
            isFeatured = true,
            warrantyMonths = 24,
            tags = listOf("mouse", "raton", "razer", "deathadder", "v3 pro", "inalambrico", "ultraligero", "esports", "fps")
        ),
        Product(
            id = "mou-003",
            name = "Mouse Logitech G203 Lightsync RGB 8000 DPI",
            brand = "Logitech G",
            category = Category.MOUSE,
            priceSoles = 99.0,
            originalPriceSoles = 129.0,
            rating = 4.8f,
            reviewCount = 580,
            description = "El mouse gamer más vendido de la historia en Perú y Latinoamérica. Sensor de grado gamer de 8000 DPI, diseño ergonómico clásico de 6 botones y vibrante iluminación RGB LIGHTSYNC con ondas de color.",
            specs = mapOf(
                "Sensor" to "Sensor óptico gamer de 200 a 8,000 DPI",
                "Botones" to "6 botones mecánicos con tensión por resorte metálico",
                "Iluminación" to "RGB LIGHTSYNC 16.8 millones de colores",
                "Peso" to "85 gramos (cable liviano)",
                "Software" to "Logitech G HUB para macros y sensibilidad"
            ),
            highlights = listOf("El mejor mouse calidad/precio por menos de S/. 100", "Sensor confiable y preciso", "Tensado mecánico de botones", "Compatible con cualquier PC y laptop"),
            stock = 30,
            isFeatured = false,
            warrantyMonths = 24,
            tags = listOf("mouse", "raton", "logitech", "g203", "lightsync", "barato", "economico", "rgb", "gamer")
        ),

        // TARJETAS DE VIDEO
        Product(
            id = "gpu-001",
            name = "Tarjeta de Video ASUS Dual GeForce RTX 4070 SUPER OC 12GB",
            brand = "ASUS / NVIDIA",
            category = Category.TARJETAS_VIDEO,
            priceSoles = 2890.0,
            originalPriceSoles = 3150.0,
            rating = 5.0f,
            reviewCount = 112,
            description = "Rendimiento demoledor en 1440p y 4K con tecnología DLSS 3 Frame Generation y trazado de rayos de 3ra generación. Dos ventiladores Axial-tech y diseño de 2.5 ranuras para amplia compatibilidad.",
            specs = mapOf(
                "Arquitectura" to "NVIDIA Ada Lovelace",
                "Memoria" to "12GB GDDR6X (192-bit)",
                "CUDA Cores" to "7168 Cores",
                "Frecuencia Boost" to "2550 MHz (Modo OC)",
                "Tecnologías" to "DLSS 3.5, Ray Tracing, NVENC AV1",
                "Conectores de Poder" to "1x 16-pin (12VHPWR con adaptador incluido)"
            ),
            highlights = listOf("DLSS 3 con generación de cuadros por IA", "12GB GDDR6X rápida", "Silenciosa y fría", "Excelente para 1440p al máximo de FPS"),
            stock = 5,
            isFeatured = true,
            warrantyMonths = 36,
            tags = listOf("tarjeta", "video", "grafica", "gpu", "nvidia", "geforce", "rtx", "4070", "super", "asus", "dlss", "4k")
        ),
        Product(
            id = "gpu-002",
            name = "Tarjeta de Video MSI GeForce RTX 4060 Ventus 2X 8GB",
            brand = "MSI / NVIDIA",
            category = Category.TARJETAS_VIDEO,
            priceSoles = 1399.0,
            originalPriceSoles = 1550.0,
            rating = 4.7f,
            reviewCount = 160,
            description = "La gráfica perfecta para jugar en 1080p con todos los gráficos al máximo y soporte de DLSS 3. Consumo ultra eficiente de apenas 115W, con refrigeración dual TORX Fan 4.0 y placa trasera de refuerzo.",
            specs = mapOf(
                "Memoria" to "8GB GDDR6 (128-bit)",
                "CUDA Cores" to "3072 Cores",
                "Consumo (TDP)" to "115W (Fuente recomendada 550W)",
                "Conector" to "1x 8-pin estándar",
                "Salidas de Video" to "3x DisplayPort 1.4a, 1x HDMI 2.1a"
            ),
            highlights = listOf("Consumo bajísimo de 115W", "DLSS 3 Frame Generation", "Doble ventilador TORX 4.0", "Ideal para actualizar PCs antiguas"),
            stock = 10,
            isFeatured = false,
            warrantyMonths = 36,
            tags = listOf("tarjeta", "video", "grafica", "gpu", "rtx 4060", "msi", "ventus", "1080p", "economica", "eficiente")
        ),

        // COMPONENTES & ALMACENAMIENTO
        Product(
            id = "ram-001",
            name = "Memoria RAM Kingston Fury Beast RGB DDR5 32GB (2x16GB) 6000MHz",
            brand = "Kingston",
            category = Category.COMPONENTES,
            priceSoles = 490.0,
            originalPriceSoles = 550.0,
            rating = 4.9f,
            reviewCount = 230,
            description = "Kit dual channel de alta velocidad para plataformas AMD AM5 e Intel 13va/14va gen. Perfiles AMD EXPO e Intel XMP 3.0 integrados con difusor de calor negro mate e iluminación RGB suave patentada.",
            specs = mapOf(
                "Capacidad" to "32GB (2 módulos de 16GB)",
                "Tipo" to "DDR5 288-pin DIMM",
                "Velocidad" to "6000 MT/s (CL30 / CL36)",
                "Perfiles" to "AMD EXPO & Intel XMP 3.0 certificados",
                "Voltaje" to "1.35V",
                "Tecnología RGB" to "Kingston Fury Infrared Sync"
            ),
            highlights = listOf("6000MHz punto dulce para Ryzen 7000/9000", "Disipador de perfil bajo", "Garantía de por vida Kingston", "Sincronización infrarroja de luces"),
            stock = 18,
            isFeatured = true,
            warrantyMonths = 60,
            tags = listOf("ram", "memoria", "ddr5", "kingston", "fury", "32gb", "6000mhz", "expo", "xmp", "componente")
        ),
        Product(
            id = "ssd-001",
            name = "Disco SSD Samsung 990 PRO NVMe M.2 2TB PCIe 4.0",
            brand = "Samsung",
            category = Category.COMPONENTES,
            priceSoles = 690.0,
            originalPriceSoles = 780.0,
            rating = 5.0f,
            reviewCount = 175,
            description = "La máxima velocidad en almacenamiento de estado sólido PCIe 4.0. Hasta 7450 MB/s de lectura secuencial. Compatible con PC Gamer de alta gama y PlayStation 5. Control térmico inteligente con níquel.",
            specs = mapOf(
                "Capacidad" to "2TB (2,000 GB)",
                "Interfaz" to "PCIe Gen 4.0 x4, NVMe 2.0",
                "Lectura Secuencial" to "Hasta 7,450 MB/s",
                "Escritura Secuencial" to "Hasta 6,900 MB/s",
                "Compatibilidad" to "PC de escritorio, Laptop y Sony PS5",
                "Durabilidad (TBW)" to "1200 TBW"
            ),
            highlights = listOf("Velocidad tope PCIe 4.0", "Cargas de juegos casi instantáneas", "Compatible con PS5", "Software Samsung Magician"),
            stock = 12,
            isFeatured = false,
            warrantyMonths = 60,
            tags = listOf("ssd", "disco", "solido", "samsung", "990 pro", "nvme", "m2", "2tb", "ps5", "almacenamiento")
        )
    )

    fun getProductById(id: String): Product? {
        return products.find { it.id == id }
    }

    /**
     * Motor de búsqueda inteligente y relacional:
     * 1. Limpia y tokeniza la consulta del usuario.
     * 2. Evalúa coincidencias directas (título, marca, tags, especificaciones).
     * 3. Evalúa sinónimos y relaciones tecnológicas (ej. "pantalla" -> monitores, "raton" -> mouse, "cpu" -> procesadores).
     * 4. Devuelve los productos emparejados ordenados por relevancia.
     */
    fun searchProducts(query: String, selectedCategory: Category = Category.ALL): List<Product> {
        val cleanQuery = query.trim().lowercase()

        val baseList = if (selectedCategory == Category.ALL) {
            products
        } else {
            products.filter { it.category == selectedCategory }
        }

        if (cleanQuery.isEmpty()) {
            return baseList
        }

        // Expandir sinónimos tecnológicos comunes
        val expandedKeywords = mutableListOf(cleanQuery)
        when {
            cleanQuery.contains("pantalla") || cleanQuery.contains("display") -> {
                expandedKeywords.addAll(listOf("monitor", "oled", "ips", "hz", "curvo"))
            }
            cleanQuery.contains("raton") || cleanQuery.contains("puntero") -> {
                expandedKeywords.addAll(listOf("mouse", "dpi", "sensor", "clic"))
            }
            cleanQuery.contains("micro") || cleanQuery.contains("procesador") || cleanQuery.contains("cpu") -> {
                expandedKeywords.addAll(listOf("ryzen", "core", "intel", "amd", "am5", "lga1700"))
            }
            cleanQuery.contains("tecla") || cleanQuery.contains("switch") -> {
                expandedKeywords.addAll(listOf("teclado", "mecanico", "tkl", "optico"))
            }
            cleanQuery.contains("video") || cleanQuery.contains("grafica") || cleanQuery.contains("gpu") -> {
                expandedKeywords.addAll(listOf("rtx", "geforce", "nvidia", "radeon", "4070", "4060"))
            }
            cleanQuery.contains("disco") || cleanQuery.contains("memoria") || cleanQuery.contains("almacen") -> {
                expandedKeywords.addAll(listOf("ssd", "ram", "ddr5", "nvme", "m.2"))
            }
            cleanQuery.contains("juego") || cleanQuery.contains("gamer") || cleanQuery.contains("gaming") -> {
                expandedKeywords.addAll(listOf("esports", "fps", "lightspeed", "chroma", "rgb", "rog"))
            }
            cleanQuery.contains("barato") || cleanQuery.contains("economico") || cleanQuery.contains("oferta") -> {
                expandedKeywords.addAll(listOf("calidad precio", "kumara", "g203", "13400f", "optix"))
            }
        }

        return baseList.map { product ->
            val score = calculateMatchScore(product, cleanQuery, expandedKeywords)
            Pair(product, score)
        }
            .filter { it.second > 0 }
            .sortedByDescending { it.second }
            .map { it.first }
    }

    private fun calculateMatchScore(product: Product, mainQuery: String, keywords: List<String>): Int {
        var score = 0
        val pName = product.name.lowercase()
        val pBrand = product.brand.lowercase()
        val pDesc = product.description.lowercase()
        val pCategory = product.category.displayName.lowercase()
        val pTags = product.tags.map { it.lowercase() }
        val pSpecs = product.specs.entries.joinToString(" ") { "${it.key} ${it.value}".lowercase() }

        // Coincidencia exacta o contiene query principal
        if (pName.contains(mainQuery)) score += 100
        if (pBrand.contains(mainQuery)) score += 80
        if (pCategory.contains(mainQuery)) score += 60
        if (pTags.any { it.contains(mainQuery) }) score += 40
        if (pSpecs.contains(mainQuery)) score += 30
        if (pDesc.contains(mainQuery)) score += 20

        // Coincidencia con palabras clave relacionadas
        for (kw in keywords) {
            if (kw == mainQuery) continue
            if (pName.contains(kw)) score += 25
            if (pTags.any { it.contains(kw) }) score += 20
            if (pSpecs.contains(kw)) score += 15
            if (pCategory.contains(kw)) score += 15
        }

        return score
    }

    /**
     * Devuelve productos relacionados según el contexto actual o producto seleccionado
     */
    fun getRelatedProducts(product: Product, limit: Int = 4): List<Product> {
        return products
            .filter { it.id != product.id }
            .sortedByDescending { other ->
                var score = 0
                if (other.category == product.category) score += 30
                if (other.brand == product.brand) score += 20
                val commonTags = other.tags.intersect(product.tags.toSet()).size
                score += commonTags * 10
                score
            }
            .take(limit)
    }

    fun getSuggestedSearches(): List<String> {
        return listOf(
            "Monitores 240Hz",
            "Ryzen 7 7800X3D",
            "Mouse Inalámbrico",
            "Teclado Mecánico TKL",
            "RTX 4070 SUPER",
            "Pantallas OLED",
            "Memoria DDR5 6000MHz",
            "Periféricos Gamer"
        )
    }
}
