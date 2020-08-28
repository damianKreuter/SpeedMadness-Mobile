package com.project.speedmadness.homePedidos.adapter

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.project.speedmadness.R
import com.project.speedmadness.auxFunctions.ImagenPedidoSegunTipo
import com.project.speedmadness.model.Pedido
import com.project.speedmadness.pedidos.verDetalles.PedidoDetalleFullDialogFragment
import kotlinx.android.synthetic.main.pedido_card_view.view.*


class PedidosActivoAdapter(val itemsLista:ArrayList<Pedido>):RecyclerView.Adapter<PedidosActivoAdapter.PedidoVM>(){

    private lateinit var dialogDetallePedido : Dialog

    class PedidoVM(itemView: View, parent : ViewGroup) : RecyclerView.ViewHolder(itemView){
        val ic_imagen_pedido: ImageView = itemView.ic_pedido_card
        val ic_titul: TextView = itemView.card_titutlo
        val ic_estado: TextView = itemView.card_estado
        val ic_fecha: TextView = itemView.card_fecha
        val ic_nroPedido: TextView = itemView.card_nroPedido
        val layout_expandible : LinearLayout = itemView.findViewById(R.id.constraintLayoutExpandible)
        val layout_total_carta : LinearLayout  = itemView.findViewById(R.id.cartaChicaExpandible)
        val todo_layout:LinearLayout = itemView.findViewById(R.id.cartaChicaExpandible)
        val buttonVerDetalles : Button = itemView.findViewById(R.id.buttonVerDetalles)
        val parentContext : Context = parent.context
        val viewGroup : ViewGroup = parent
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PedidoVM {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.pedido_card_view, parent, false)
        return PedidoVM(view, parent)
    }

    override fun getItemCount(): Int {
        return itemsLista.size
    }

    override fun onBindViewHolder(holder: PedidosActivoAdapter.PedidoVM, position: Int) {
        val pedidoEspecifico: Pedido = itemsLista[position]

        holder.ic_imagen_pedido.setImageResource(
            ImagenPedidoSegunTipo().obtenerIcono(pedidoEspecifico.tipoPedido)
        )
        holder.ic_estado.text = pedidoEspecifico.estadoPedido.name
        holder.ic_fecha.text = pedidoEspecifico.fecha
        holder.ic_nroPedido.text = pedidoEspecifico.id_pedido
        holder.ic_titul.text = pedidoEspecifico.titulo
        holder.ic_nroPedido.text = pedidoEspecifico.id_pedido
        holder.ic_nroPedido.text = pedidoEspecifico.id_pedido

        val estaExandido: Boolean = itemsLista[position].expandible

        holder.layout_expandible.visibility = if (estaExandido) View.VISIBLE else View.GONE

        holder.layout_total_carta.setOnClickListener {
            val personaCamber = itemsLista[position]
            personaCamber.expandible = !personaCamber.expandible
            notifyItemChanged(position)
        }

        holder.buttonVerDetalles.setOnClickListener {
            val pedidoLista : Pedido = itemsLista[position]
            PedidoDetalleFullDialogFragment.display(pedidoLista.context, pedidoLista)
        }
    }
}