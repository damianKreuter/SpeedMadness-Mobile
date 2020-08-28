package com.project.speedmadness.pedidos.verDetalles

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.project.speedmadness.R
import com.project.speedmadness.model.Pedido

class PedidoDetalleFullDialogFragment (var pedido: Pedido) : DialogFragment() {

    lateinit var toolbar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    companion object {
        val TAG : String= "example_dialog";
        fun display(fragmentManager: FragmentManager, pedidoSeleccionado : Pedido): PedidoDetalleFullDialogFragment? {
            val exampleDialog = PedidoDetalleFullDialogFragment(pedidoSeleccionado)
            exampleDialog.show(fragmentManager, TAG)
            return exampleDialog
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fullscreen_dialog_pedido_detalle, container, false)
        cargarDatos(view)
        toolbar = view.findViewById(R.id.toolbarPart)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener{
                v->dismiss()
        }
        toolbar.setTitle("Detalles de pedido")
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorBlanco))
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog : Dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }


    private fun cargarDatos(view:View){
        var tituloNro :TextInputLayout =
            view.findViewById(R.id.input_layout_titulo_id_detalle)

        var tituloEdit: TextInputEditText =
            view.findViewById(R.id.EditTextTituloPedidoDetalle)
        tituloEdit.isEnabled = false
        var desdeDir: TextInputEditText =
            view.findViewById(R.id.PedidoDetalleEditTextDesde)
        desdeDir.isEnabled = false
        var hastaDir: TextInputEditText =
            view.findViewById(R.id.PedidoDetalleEditTextHasta)
        hastaDir.isEnabled = false
        var estado: TextInputEditText =
            view.findViewById(R.id.PedidoDetalleEditTextEstado)
        estado.isEnabled = false
        var descripcion: TextInputEditText =
            view.findViewById(R.id.PedidoDetalleEditTextDescripcion)
        descripcion.isEnabled = false

        tituloNro.hint = "Nro: "+Editable.Factory.getInstance().newEditable(pedido.id_pedido)
        tituloEdit.text = Editable.Factory.getInstance().newEditable(pedido.titulo)
        desdeDir.text = Editable.Factory.getInstance().newEditable(pedido.desde)
        hastaDir.text = Editable.Factory.getInstance().newEditable(pedido.destino)
        estado.text = Editable.Factory.getInstance().newEditable(pedido.estadoPedido.name)
        descripcion.text = Editable.Factory.getInstance().newEditable(pedido.id_pedido)
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.getWindow()?.setLayout(width, height);
            dialog.getWindow()?.setWindowAnimations(R.style.AppTheme_Slide);
        }
    }
}






























