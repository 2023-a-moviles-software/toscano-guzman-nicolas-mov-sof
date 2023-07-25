package com.example.deber2recyclerview.provider

import com.example.deber2recyclerview.entidad.Comida

class HambuguesaProvider {
    companion object{
        val listaComidas: List<Comida> = listOf(
            Comida("Pizza con peperoni", 9.99, "https://www.saborusa.com/ni/wp-content/uploads/sites/19/2019/11/Animate-a-disfrutar-una-deliciosa-pizza-de-salchicha-Foto-destacada.png"),
            Comida("Frijoles con chicarron", 12.99, "https://la20delsurcenadero.com/wp-content/uploads/2021/03/costilla-la-20.jpg"),
            Comida("Bandeja paisa", 8.99, "https://la20delsurcenadero.com/wp-content/uploads/2021/03/bandeja-paisa-la-20.jpg"),
            Comida("Arroz con pollo", 14.99, "https://la20delsurcenadero.com/wp-content/uploads/2021/03/arroz-pollo-la-20.jpg"),
            Comida("Triple Box 3 Pizzas", 25.99, "https://tb-static.uber.com/prod/image-proc/processed_images/b338e3377ecc40ab0a96005775c04189/5954bcb006b10dbfd0bc160f6370faf3.jpeg"),
            Comida("Combo los Tíos Familiar", 14.99, "https://d1ralsognjng37.cloudfront.net/b0af6160-9344-4807-88bd-fac8f3d26899.jpeg"),
            Comida("Spicy tempura chicken", 13.99, "https://d1ralsognjng37.cloudfront.net/ea68367f-ef44-42d6-82bb-984349b3a249.jpeg"),
            Comida("Ceviche mixto", 9.65, "https://d1ralsognjng37.cloudfront.net/435b86eb-84de-4637-8e28-3d1ed69b6aef.jpeg"),
            Comida("Combo Mediano Bacon Smokehouse", 9.50, "https://tb-static.uber.com/prod/image-proc/processed_images/5ab1c036556aef32055dd7ea06af8e77/5954bcb006b10dbfd0bc160f6370faf3.jpeg"),
            Comida("Hornado", 15.75, "https://d1ralsognjng37.cloudfront.net/269415c4-d68b-480d-802c-0da3109a4bfb.jpeg")

        )
    }
}