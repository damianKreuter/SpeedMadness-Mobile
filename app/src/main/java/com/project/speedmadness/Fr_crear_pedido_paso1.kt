package com.project.speedmadness

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.project.speedmadness.main.MainActivity
import com.project.speedmadness.model.TipoPedido

class Fr_crear_pedido_paso1 : Fragment() {

    private lateinit var tipoPedido: TipoPedido
    private lateinit var inputTipoPedido: TextInputLayout

    private lateinit var btnCrearItem: Button
    private lateinit var btnSiquiente: Button
    private lateinit var autoCompleteTextView: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fr_crear_pedido_paso1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputTipoPedido = view.findViewById(R.id.input_tipoPedido)
        btnCrearItem = view.findViewById(R.id.buttonCrearItem)
        btnSiquiente = view.findViewById(R.id.buttonSiguiente)

        setearAdaptadorComboBox(view)
        val editTextCrearItem : TextInputEditText = view.findViewById(R.id.editLayout_input_layout_descripcionPedidoNuevo)
        btnCrearItem.setOnClickListener{

            val datoIngresado : String = editTextCrearItem.text.toString()

        }

        btnSiquiente.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_fr_crear_pedido_paso1_to_cargarDirecciones)
        }
    }

    private fun setearAdaptadorComboBox(view: View) {
        autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView)
        val items = listOf("Compra", "Cobranza", "Flete", "Mensajeria","Transporte")
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_menu_text, items)
        autoCompleteTextView.setAdapter(adapter)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fr_crear_pedido_paso1().apply {

            }
    }
}