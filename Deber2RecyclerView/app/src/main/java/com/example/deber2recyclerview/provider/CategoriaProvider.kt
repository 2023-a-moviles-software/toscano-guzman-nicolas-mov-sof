package com.example.deber2recyclerview.provider

import com.example.deber2recyclerview.entidad.Categoria

class CategoriaProvider {
    companion object{
        val listaCategorias: List<Categoria> = listOf(
            Categoria("Pizza", "https://www.laespanolaaceites.com/wp-content/uploads/2019/06/pizza-con-chorizo-jamon-y-queso-1080x671.jpg"),
            Categoria("Alcohol", "https://as01.epimg.net/diarioas/imagenes/2021/07/14/actualidad/1626290551_619916_1626290714_noticia_normal_recorte1.jpg"),
            Categoria("Farmacia", "https://medlineplus.gov/images/Medicines_share.jpg"),
            Categoria("Hamburguesas", "https://www.recetasnestle.com.ec/sites/default/files/srh_recipes/4e4293857c03d819e4ae51de1e86d66a.jpg"),
            Categoria("Mascotas", "https://cdn.shopify.com/s/files/1/0268/6861/files/Perros-vs-Gatos2_grande.jpg?v=1587405310")
        )
    }
}