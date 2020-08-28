package com.project.speedmadness.crearPeddo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.project.speedmadness.R
import com.project.speedmadness.model.TipoPedido

class Crear_pedido_fullscreen : DialogFragment() {

    lateinit var toolbar : Toolbar
    lateinit var tipoElegido : TipoPedido


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.dialog_fragment_crear_pedido_fullscreen, container, false)
        toolbar = view.findViewById(R.id.toolbarPartPedido)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener{
                v->dismiss()
        }
        toolbar.setTitle("Creación de pedido")
        toolbar.setSubtitle("1 de 3 Pasos")
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorBlanco))
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.colorBlanco))

    }

    companion object {
        val TAG : String= "creacion_pedido_dialog";
        fun display(fragmentManager: FragmentManager): Crear_pedido_fullscreen? {
            val crear_pedido_fullscreen = Crear_pedido_fullscreen()
            crear_pedido_fullscreen.show(fragmentManager, TAG)
            return crear_pedido_fullscreen
        }
    }
}