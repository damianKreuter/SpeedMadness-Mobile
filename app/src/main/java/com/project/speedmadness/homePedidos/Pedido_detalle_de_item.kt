package com.project.speedmadness.homePedidos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.speedmadness.R

class Pedido_detalle_de_item : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pedido_detalle_de_item, container, false)
    }

    /*
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Pedido_detalle_de_item().apply {

    }
    */

}