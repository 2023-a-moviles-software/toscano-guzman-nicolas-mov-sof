package com.example.deber2recyclerview.BD

import com.example.deber2recyclerview.entidad.Categoria
import com.example.deber2recyclerview.entidad.Comida

class BaseDeDatos {
    companion object {
        val listaCategorias = mutableListOf<Categoria>(
            Categoria(
                "Pizzas",
                "https://www.laespanolaaceites.com/wp-content/uploads/2019/06/pizza-con-chorizo-jamon-y-queso-1080x671.jpg",
                mutableListOf<Comida>(
                    Comida(
                        "Familiar Exclusiva",
                        27.85,
                        "https://s3.amazonaws.com/admin.kfc.production/products/62a223d1a62d5_Pizza%20Hornero%20-%20Exclusiva.jpg"
                    ),
                    Comida(
                        "Familiar Hawaiana",
                        21.15,
                        "https://s3.amazonaws.com/admin.kfc.production/products/62a223ffe26ac_Pizza%20Hornero%20-%20Hawaiana.jpg"
                    ),
                    Comida(
                        "Familiar Hornero",
                        27.85,
                        "https://s3.amazonaws.com/admin.kfc.production/products/62a224300c55c_Pizza%20Hornero.jpg"
                    ),
                    Comida(
                        "Familiar Jamón/Champ",
                        21.15,
                        "https://s3.amazonaws.com/admin.kfc.production/products/62a224674e89d_Pizza%20Hornero%20-Jam%C3%B3n%20y%20champi%C3%B1ones.jpg"
                    ),
                    Comida(
                        "Familiar Maqueno",
                        19.25,
                        "https://s3.amazonaws.com/admin.kfc.production/products/62a2251482e7b_Pizza%20Hornero%20-%20maque%C3%B1o.jpg"
                    ),
                    Comida(
                        "Familiar Mozarel Esp",
                        19.25,
                        "https://s3.amazonaws.com/admin.kfc.production/products/62a225e1ebfa3_Pizza%20Hornero%20-%20mozzarella%20especial.jpg"
                    ),
                    Comida(
                        "Familiar Napolitana",
                        19.25,
                        "https://s3.amazonaws.com/admin.kfc.production/products/62a225ff3ed1b_Pizza%20Hornero%20-%20Napolitana.jpg"
                    ),
                    Comida(
                        "Familiar Pepperonatta",
                        27.85,
                        "https://s3.amazonaws.com/admin.kfc.production/products/62a2265e4b3f9_Pizza%20Hornero%20-%20Pepperonata.jpg"
                    ),
                    Comida(
                        "Familiar SuperCriolla",
                        27.85,
                        "https://s3.amazonaws.com/admin.kfc.production/products/62a226a03064b_Pizza%20Hornero%20-%20Super%20criolla.jpg"
                    ),
                    Comida(
                        "Familiar Todo Carnes",
                        27.85,
                        "https://s3.amazonaws.com/admin.kfc.production/products/62a226be27927_Pizza%20Hornero%20-%20Todo%20carnes.jpg"
                    )
                )
            ),
            Categoria(
                "Alcohol",
                "https://as01.epimg.net/diarioas/imagenes/2021/07/14/actualidad/1626290551_619916_1626290714_noticia_normal_recorte1.jpg",
                mutableListOf<Comida>(
                    Comida(
                        "Anti0queñ0 750 ml",
                        20.99,
                        "https://tb-static.uber.com/prod/image-proc/processed_images/80fc11e4e9598bec3a348effa492dbf7/5954bcb006b10dbfd0bc160f6370faf3.jpeg"
                    ),
                    Comida(
                        "Combo doble vino tierra de fuego 750 ml",
                        20.99,
                        "https://tb-static.uber.com/prod/image-proc/processed_images/fd318e6f0305d95f889aa03120efe35e/5954bcb006b10dbfd0bc160f6370faf3.jpeg"
                    ),
                    Comida(
                        "Switch de maracuya de 1500 ml",
                        5.50,
                        "https://tb-static.uber.com/prod/image-proc/processed_images/f6e696bffa6864776177133c82654d93/5954bcb006b10dbfd0bc160f6370faf3.jpeg"
                    ),
                    Comida(
                        "Ruskaya",
                        17.50,
                        "https://tb-static.uber.com/prod/image-proc/processed_images/a7356d08318b3c1858d76c2943f6565f/5954bcb006b10dbfd0bc160f6370faf3.jpeg"
                    ),
                    Comida(
                        "Jhonny rojo 1 litro",
                        41.99,
                        "https://tb-static.uber.com/prod/image-proc/processed_images/13fc07c9fa05e9507c3e27766aecdbf0/5954bcb006b10dbfd0bc160f6370faf3.jpeg"
                    ),
                    Comida(
                        "R0n pon pon",
                        9.50,
                        "https://tb-static.uber.com/prod/image-proc/processed_images/9695ef172920032bc909c2520d7b942b/5954bcb006b10dbfd0bc160f6370faf3.jpeg"
                    ),
                    Comida(
                        "R0n san miguel silver",
                        16.99,
                        "https://tb-static.uber.com/prod/image-proc/processed_images/d7d7d798fff3d5cca0ac89d3f96aa3d2/5954bcb006b10dbfd0bc160f6370faf3.jpeg"
                    )
                )
            ),
            Categoria(
                "Hamburguesas",
                "https://www.recetasnestle.com.ec/sites/default/files/srh_recipes/4e4293857c03d819e4ae51de1e86d66a.jpg",
                mutableListOf<Comida>(
                    Comida(
                        "Hamburguesa El Hornero",
                        7.50,
                        "https://s3.amazonaws.com/admin.kfc.production/products/62a0ee5218558_hamburguesa%20hornero.jpg"
                    ),
                    Comida(
                        "Chivito Completo",
                        8.90,
                        "https://s3.amazonaws.com/admin.kfc.production/products/62a0ee7740683_Chivito%20completo.jpg"
                    ),
                    Comida(
                        "Chivito de Pollo",
                        7.50,
                        "https://s3.amazonaws.com/admin.kfc.production/products/62a0ee872f322_chivito%20pollo.jpg"
                    ),
                    Comida(
                        "Chivito Mozarella",
                        6.95,
                        "https://s3.amazonaws.com/admin.kfc.production/products/637b9b169dda3_chivito-mozzarella.jpg"
                    ),
                    Comida(
                        "Hamburguesa Provolone",
                        8.00,
                        "https://s3.amazonaws.com/admin.kfc.production/products/62a0ee6279d9e_Hamburguesa%20provolone.jpg"
                    ),
                )
            ),
            Categoria(
                "Mascotas",
                "https://cdn.shopify.com/s/files/1/0268/6861/files/Perros-vs-Gatos2_grande.jpg?v=1587405310",
                mutableListOf<Comida>(
                    Comida(
                        "PEDIGREE CACHORRO BISCROK 100 GR",
                        1.32,
                        "https://citypet.ec/wp-content/uploads/2023/07/PEDIGREE-CACHORRO-BISCROK-100-GR.png"
                    ),
                    Comida(
                        "10 HUESOS 2-3 VARIOS SABORES",
                        3.10,
                        "https://citypet.ec/wp-content/uploads/2023/07/10-HUESOS-2-3-VARIOS-SABORES-6117.png"
                    ),
                    Comida(
                        "NUTRAPRO SNACK DENTAL RMG",
                        2.79,
                        "https://citypet.ec/wp-content/uploads/2023/07/NUTRAPRO-SNACK-DENTAL-RMG-200GRM-4002688.png"
                    ),
                    Comida(
                        "NUTRAPRO SNACK DENTAL RPM ",
                        1.59,
                        "https://citypet.ec/wp-content/uploads/2023/07/NUTRAPRO-SNACK-DENTAL-RPM-100GR-4002689.png"
                    ),
                    Comida(
                        "7 ARITOS PERRO MINI",
                        2.27,
                        "https://citypet.ec/wp-content/uploads/2023/07/7-ARITOS-PERRO-MINI-6402.png"
                    ),
                    Comida(
                        "4 HUESO",
                        2.15,
                        "https://citypet.ec/wp-content/uploads/2023/07/4-HUESO-3-4-6143.png"
                    ),
                    Comida(
                        "2 HUESO 4-5",
                        1.66,
                        "https://citypet.ec/wp-content/uploads/2023/07/2-HUESO-4-5-6124.png"
                    )
                )
            ),
            Categoria(
                "Medicinas",
                "https://medlineplus.gov/images/Medicines_share.jpg",
                mutableListOf<Comida>(
                    Comida(
                        "Suero Oral 75 Meq Con Zinc Uva",
                        3.37,
                        "https://www.fybeca.com/dw/image/v2/BDPM_PRD/on/demandware.static/-/Sites-masterCatalog_FybecaEcuador/default/dwc6a7c8a8/images/large/100021614-HIDRAPLUS-75MEQ-ZINC-MEGALABS-UVA-400ML-FRASCO.jpg?sw=1000&sh=1000"
                    ),
                    Comida(
                        "Vitaminas C, D Y Zinc",
                        11.16,
                        "https://www.fybeca.com/dw/image/v2/BDPM_PRD/on/demandware.static/-/Sites-masterCatalog_FybecaEcuador/default/dw9020c4d2/images/large/100190435-REDOXON-TOTAL-NARANJA-135G-CAJA.jpg?sw=1000&sh=1000"
                    ),
                    Comida(
                        "Sildenafil 50 Mg Blíster Unidad",
                        1.59,
                        "https://www.fybeca.com/on/demandware.static/-/Sites/es_EC/dwce0944ff/1_2.png"
                    ),
                    Comida(
                        "Kufer Q Forte Unidad",
                        0.22,
                        "https://www.fybeca.com/dw/image/v2/BDPM_PRD/on/demandware.static/-/Sites-masterCatalog_FybecaEcuador/default/dw2f9e8952/images/large/279178-KUFER-Q-FORTE-CAPSULA-UNIDAD.jpg?sw=1000&sh=1000"
                    ),
                    Comida(
                        "Avodart 0.5 Mg Unidad",
                        1.28,
                        "https://www.fybeca.com/dw/image/v2/BDPM_PRD/on/demandware.static/-/Sites-masterCatalog_FybecaEcuador/default/dw932d3c4c/images/large/143550-AVODART-CAPSULAS-05-MG-UNIDAD.jpg?sw=1000&sh=1000"
                    ),
                    Comida(
                        "Azitromicina Medigener 500 Mg Unidad",
                        1.28,
                        "https://www.fybeca.com/dw/image/v2/BDPM_PRD/on/demandware.static/-/Sites-masterCatalog_FybecaEcuador/default/dw719fdb12/images/large/183566-AZITROMICINA-MEDIGENER-500MG-UNIDAD.jpg?sw=1000&sh=1000"
                    ),
                    Comida(
                        "Buster 50 Mg Unidad",
                        3.75,
                        "https://www.fybeca.com/dw/image/v2/BDPM_PRD/on/demandware.static/-/Sites-masterCatalog_FybecaEcuador/default/dwe877bc84/images/large/100229534-BUSTER-EMAPHARM-50-MG-UNIDAD.jpg?sw=1000&sh=1000"
                    )
                )
            )
        )
    }
}