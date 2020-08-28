package com.project.speedmadness.homePedidos

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.project.speedmadness.R
import com.project.speedmadness.homePedidos.adapter.PedidosActivoAdapter
import com.project.speedmadness.model.EstadoPedido
import com.project.speedmadness.model.Pedido
import com.project.speedmadness.model.TipoPedido
import kotlinx.android.synthetic.main.fragment_pedidos_historial.*


/*
Historial de todos los pedidos, pendientes y hechos

Debe ser info minimalista
    ID pedido
    Fecha pedido
    Estado

En caso de que el user quiera solicitar info detalle
puede apretar las mismas y ver dichos detalles que
deberían ser parecidos a los que se muestran en la
pantalla de pedidos Activos.
 */
class PedidosHistorial : Fragment() {

    private lateinit var pedidosLista : ArrayList<Pedido>
    private lateinit var pedidoAdapter: PedidosActivoAdapter
    private lateinit var myContext : FragmentActivity;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        myContext = activity as FragmentActivity
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pedidos_activos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pedidosLista = ArrayList()
        pedidoAdapter = PedidosActivoAdapter(pedidosLista)
        initRecyclerView(view)
    }

    private fun addData(){
        var itemsPedidos = ArrayList<String>()
        itemsPedidos.add("Coca cola")
        itemsPedidos.add("Partid")
        itemsPedidos.add("Tarzan")
        pedidosLista.add(
            Pedido("Santander pedido del mes de Julio", "10/02/2020", "ABS25W63", "ADMIN", EstadoPedido.En_Preparacion,
            "La heras 123",  "Boucharn 4123", true, itemsPedidos, TipoPedido.Mensajeria, 150.25, myContext.supportFragmentManager)
        )
        itemsPedidos.add("Manzana")
        itemsPedidos.add("Bananas")
        itemsPedidos.remove("Coca cola")
        itemsPedidos.remove("Partid")
        itemsPedidos.remove("Tarzan")
        pedidosLista.add(
            Pedido("Alimentos", "10/06/2019", "xxlsq15", "123wasd", EstadoPedido.Pendiente, "Ritwon 123",
            "Pastan 902", true, itemsPedidos, TipoPedido.Compras, 12.50, myContext.supportFragmentManager)
        )
        pedidosLista.add(
            Pedido("Mi pedido favorito de febrero", "05/02/2018", "xx5645", "123wasd", EstadoPedido.Pendiente, "Ritwon 123",
            "Pastan 902", true, itemsPedidos, TipoPedido.Compras, 12.50, myContext.supportFragmentManager)
        )
        pedidosLista.add(
            Pedido("Cobrar alquileres a mi nombre", "18/10/2019", "cxa23", "123wasd", EstadoPedido.Entregado, "Ritwon 123",
            "Pastan 902", true, itemsPedidos, TipoPedido.Cobranza, 12.50, myContext.supportFragmentManager)
        )
        pedidosLista.add(
            Pedido("Ropa para comprar", "10/06/2019", "xxlsq15", "123wasd", EstadoPedido.Pendiente, "Ritwon 123",
                "Pastan 902", true, itemsPedidos, TipoPedido.Compras, 12.50, myContext.supportFragmentManager)
        )
        pedidosLista.add(
            Pedido("Mi pedido", "05/02/2018", "xx5645", "123wasd", EstadoPedido.Pendiente, "Ritwon 123",
                "Pastan 902", true, itemsPedidos, TipoPedido.Compras, 12.50, myContext.supportFragmentManager)
        )
        pedidosLista.add(
            Pedido("Deuda impaga", "18/10/2019", "cxa23", "123wasd", EstadoPedido.Entregado, "Ritwon 123",
                "Pastan 902", true, itemsPedidos, TipoPedido.Cobranza, 12.50, myContext.supportFragmentManager)
        )
        pedidosLista.add(
            Pedido("Comida y provisiones", "10/06/2019", "xxlsq15", "123wasd", EstadoPedido.Pendiente, "Ritwon 123",
                "Pastan 902", true, itemsPedidos, TipoPedido.Compras, 12.50, myContext.supportFragmentManager)
        )
        pedidosLista.add(
            Pedido("Mi pedido", "05/02/2018", "xx5645", "123wasd", EstadoPedido.Pendiente, "Ritwon 123",
                "Pastan 902", true, itemsPedidos, TipoPedido.Compras, 12.50, myContext.supportFragmentManager)
        )
        pedidosLista.add(
            Pedido("Pedidos de viejos amigos que no me dieron una chota de guita", "18/10/2019", "cxa23", "123wasd", EstadoPedido.Entregado, "Ritwon 123",
                "Pastan 902", true, itemsPedidos, TipoPedido.Cobranza, 12.50, myContext.supportFragmentManager)
        )
    }

    private fun initRecyclerView(view: View){
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_activos)
        recyclerView.adapter = PedidosActivoAdapter(pedidosLista)
        recycler_view_historial.apply {
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                    //    recyclerView.layoutManager = GridLayoutManager(this.context,2)
        }
        addData()

    }

}