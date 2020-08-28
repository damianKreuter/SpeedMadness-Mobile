package com.project.speedmadness.crearPeddo.adapter

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.speedmadness.R
import com.project.speedmadness.auxFunctions.ImagenPedidoSegunTipo
import com.project.speedmadness.homePedidos.adapter.PedidosActivoAdapter
import com.project.speedmadness.model.Pedido
import com.project.speedmadness.pedidos.verDetalles.PedidoDetalleFullDialogFragment
import kotlinx.android.synthetic.main.pedido_card_view.view.*

class ListaNombreItemPedido (val itemsLista:ArrayList<String>):
    RecyclerView.Adapter<PedidosActivoAdapter.PedidoVM>(){

    private lateinit var dialogDetallePedido : Dialog

    class PedidoVM(itemView: View, parent : ViewGroup) : RecyclerView.ViewHolder(itemView){
        val ic_imagen_pedido: ImageView = itemView.ic_pedido_card
        val ic_titul: TextView = itemView.card_titutlo
        val ic_estado: TextView = itemView.card_estado
        val ic_fecha: TextView = itemView.card_fecha
        val ic_nroPedido: TextView = itemView.card_nroPedido
        val layout_expandible : LinearLayout = itemView.findViewById(R.id.constraintLayoutExpandible)
        val layout_total_carta : LinearLayout = itemView.findViewById(R.id.cartaChicaExpandible)
        val todo_layout: LinearLayout = itemView.findViewById(R.id.cartaChicaExpandible)
        val buttonVerDetalles : Button = itemView.findViewById(R.id.buttonVerDetalles)
        val parentContext : Context = parent.context
        val viewGroup : ViewGroup = parent
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PedidosActivoAdapter.PedidoVM {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PedidosActivoAdapter.PedidoVM, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}