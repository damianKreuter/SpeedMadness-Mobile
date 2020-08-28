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
import kotlinx.android.synthetic.main.fragment_pedidos_activos.*

/*
Vsita por default
Solo se mostrará los pedidos que tiene vigentes

Datos importantes:
    ID,
    Estado,
    Monto,
    Listado de cosas
    Fecha de pedido
    Indicador de estado
 */
class PedidosActivos : Fragment()
    //, RecyclerView.Adapter<PedidosActivos.PedidoActivo>
{
    // TODO: Rename and change types of parameters

    private lateinit var pedidosLista : ArrayList<Pedido>
    private lateinit var pedidoAdapter : PedidosActivoAdapter
    private lateinit var recyclerView : RecyclerView

    private lateinit var myContext : FragmentActivity;

    private val COLUMNS_COUNT : Int = 2

    override fun onAttach(context: Context) {
        myContext = activity as FragmentActivity
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        initData()

        initRecyclerView(view)

        /*
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, )
            .addSharedElement(ivChar, ivChar.transitionName)
            .commitAllowingStateLoss() // or commit()

        */


    }

    private fun initData(){
        pedidosLista = ArrayList()

        var itemsPedidos = ArrayList<String>()
        itemsPedidos.add("Coca cola")
        itemsPedidos.add("Partid")
        itemsPedidos.add("Tarzan")
        pedidosLista.add(Pedido("Santander pedido", "10/02/2020", "ABS25W63", "ADMIN", EstadoPedido.En_Preparacion,
            "La heras 123",  "Boucharn 4123", true, itemsPedidos, TipoPedido.Mensajeria, 150.25, myContext.supportFragmentManager))
        itemsPedidos.add("Manzana")
        itemsPedidos.add("Bananas")
        itemsPedidos.remove("Coca cola")
        itemsPedidos.remove("Partid")
        itemsPedidos.remove("Tarzan")
        pedidosLista.add(Pedido("Ropa para comprar", "10/06/2019", "xxlsq15", "123wasd", EstadoPedido.Pendiente, "Ritwon 123",
            "ART 902", true, itemsPedidos, TipoPedido.Compras, 12.50,myContext.supportFragmentManager))
        pedidosLista.add(Pedido("Pedido de cocas", "05/02/2018", "xx5645", "123wasd", EstadoPedido.Pendiente, "Ritwon 123",
            "Ritwon 902", true, itemsPedidos, TipoPedido.Compras, 12.50,myContext.supportFragmentManager))
        pedidosLista.add(Pedido("Cobrar servicio", "18/10/2019", "cvwsav", "123wasd", EstadoPedido.Entregado, "Robinson 123",
            "Pastan 902", true, itemsPedidos, TipoPedido.Cobranza, 12.50,myContext.supportFragmentManager))
        pedidosLista.add(Pedido("Cobrar prestamo", "18/10/2019", "cxa23", "123wasd", EstadoPedido.Entregado, "ART 123",
            "ART 902", true, itemsPedidos, TipoPedido.Cobranza, 12.50,myContext.supportFragmentManager))
        pedidosLista.add(Pedido("Venta de auto", "18/10/2019", "6433", "qa23", EstadoPedido.Entregado, "Ritwon 123",
            "Pastan 902", true, itemsPedidos, TipoPedido.Venta, 12.50,myContext.supportFragmentManager))
        pedidosLista.add(Pedido("Transportar a hospital", "18/10/2019", "ddwq", "bgdsr3", EstadoPedido.Entregado, "ART 123",
            "Pastan 902", true, itemsPedidos, TipoPedido.Transporte_Personas, 12.50,myContext.supportFragmentManager))
        pedidosLista.add(Pedido("Cobrar terreno", "18/10/2019", "cvwsav", "123wasd", EstadoPedido.Entregado, "cadw 123",
            "Pastan 902", true, itemsPedidos, TipoPedido.Cobranza, 12.50,myContext.supportFragmentManager))
        pedidosLista.add(Pedido("Cobrar Yunque", "18/10/2019", "cxa23", "123wasd", EstadoPedido.Entregado, "Pastan 123",
            "cadw 902", true, itemsPedidos, TipoPedido.Cobranza, 12.50,myContext.supportFragmentManager))
        pedidosLista.add(Pedido("Venta de pinos", "18/10/2019", "6433", "qa23", EstadoPedido.Entregado, "Ritwon 123",
            "cadw 902", true, itemsPedidos, TipoPedido.Venta, 12.50,myContext.supportFragmentManager))



        pedidoAdapter = PedidosActivoAdapter(pedidosLista)
    }

    private fun initRecyclerView(view: View){
        recyclerView = view.findViewById(R.id.recycler_view_activos)
        recyclerView.adapter = PedidosActivoAdapter(pedidosLista)
        recycler_view_activos.apply {
            recyclerView.layoutManager = StaggeredGridLayoutManager(COLUMNS_COUNT, LinearLayoutManager.VERTICAL)
  //          postponeEnterTransition()
            /*
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
            */

        }

    }

    inner class PedidoActivo(itemView: View) : RecyclerView.ViewHolder(itemView) {

 //       internal var id_pedido:
    }

}