package com.project.speedmadness.auxFunctions

import android.widget.ImageView
import com.project.speedmadness.R
import com.project.speedmadness.model.TipoPedido

class ImagenPedidoSegunTipo {
  //  Flete, Transporte_Personas, Compras, Venta, Mensajeria, Cobranza
    fun obtenerIcono(tipo:TipoPedido):Int {
        return when(tipo) {
            TipoPedido.Flete -> R.drawable.ic_camion
            TipoPedido.Cobranza-> R.drawable.ic_rico
            TipoPedido.Compras-> R.drawable.ic_shopping
            TipoPedido.Mensajeria -> R.drawable.ic_mensajeria
            TipoPedido.Transporte_Personas -> R.drawable.ic_perfil
            TipoPedido.Venta -> R.drawable.ic_dinero
        }
    }
}