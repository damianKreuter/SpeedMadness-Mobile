package com.project.speedmadness.homePedidos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.project.speedmadness.R
import com.project.speedmadness.homePedidos.adapter.PedidosActivoAdapter
import java.text.FieldPosition

/*
Pertenece a la primera vista que ve el usuario,
la idea es que el usuario pueda ver como funciona
sus pedidos que están pendientes para poder consultar
luego su data y el historial de pedidos que ha
realizado a lo largo del uso de la app.
 */
class HomePage : Fragment() {

    private lateinit var tabLayout:TabLayout
    private lateinit var viewPager:ViewPager2

    private lateinit var pedidosHistorial : PedidosHistorial
    private lateinit var PedidosActivos : PedidosActivos

    private lateinit var viewHome:View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewHome = view

        viewPager = view.findViewById(R.id.viewPager)

        pedidosHistorial = PedidosHistorial()
        PedidosActivos = PedidosActivos()


        val homeViewAdapter : HomeViewAdapter?= activity?.let { HomeViewAdapter(it) }
        homeViewAdapter?.addFragment(PedidosActivos, "Mis pedidos")
        homeViewAdapter?.addFragment(pedidosHistorial, "Historial")

        viewPager.adapter = homeViewAdapter
        tabLayout = view.findViewById(R.id.homeTabLayout)
        initTabLayout()

        initnavBar()
    }

    private fun initnavBar() {

    }

    private fun initTabLayout() {
        val tabLayoutMediator =
            TabLayoutMediator(tabLayout, viewPager, TabLayoutMediator.TabConfigurationStrategy() {
                    tab: TabLayout.Tab, i: Int ->
                when(i) {
                    0 -> {
                        tab.setText("Mis pedidos")
                        tab.setIcon(R.drawable.ic_flete_caudal)
                    }
                    1 -> {
                        tab.setText("Historial")
                        tab.setIcon(R.drawable.ic_cajas)
                    }

                }
            })
        tabLayoutMediator.attach()
    }

}


