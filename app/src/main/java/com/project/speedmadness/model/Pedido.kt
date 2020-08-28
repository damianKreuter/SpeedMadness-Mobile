package com.project.speedmadness.model

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.google.android.gms.dynamic.SupportFragmentWrapper

class Pedido(
    var titulo: String,
    var fecha: String,
    var id_pedido: String,
    var id_user: String,
    var estadoPedido: EstadoPedido,
    var desde: String,
    var destino: String,
    var esIdaYVuelta: Boolean,
    var listaItems: ArrayList<String>,
    var tipoPedido: TipoPedido,
    var monto: Double,
    var context : FragmentManager,
    var expandible: Boolean = false

)