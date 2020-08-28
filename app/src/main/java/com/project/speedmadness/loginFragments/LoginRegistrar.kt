package com.project.speedmadness.loginFragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.project.speedmadness.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginRegistrar.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginRegistrar : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var mAuth: FirebaseAuth
    private lateinit var user: EditText
    private lateinit var contra: EditText
    private lateinit var email: EditText

    enum class provider {
        BASIC, GOOGLE, FACEBOK, TWITTER
    }

    interface FragmentRegistroListener {
        fun registroNuevo(user: String?, email: String?, credencial:String?, metodo:String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_registrar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)

        mAuth = FirebaseAuth.getInstance()

        user = view.findViewById(R.id.inputLoginUser)
        contra = view.findViewById(R.id.inputLoginPassword)
        email = view.findViewById(R.id.inputLoginEmail)
        //EVALUAR SI EXISTEN EN FIREBASE
        var buttonRegistroTerminado: Button = view.findViewById(R.id.buttonRegistro)
        buttonRegistroTerminado.setOnClickListener() {
            if (registroCorrecto()) {
                val buttonRegistroTerminado: Button = view.findViewById(R.id.buttonRegistro)
                buttonRegistroTerminado.setOnClickListener(View.OnClickListener {
                    navController.navigate(
                        R.id.action_loginRegistrar_to_homePage
                    )
                })
            }
        }
    }

    private fun registroCorrecto():Boolean{
        var faltaCampos:Boolean = false;
        var procesoCompletado:Boolean = false;
        if(user.text.toString().isNotEmpty()) {
            faltaCampos=true
        }

        if(contra.text.toString().isNotEmpty()){
            faltaCampos=true
        }

        if(email.text.toString().isNotEmpty()){
            faltaCampos=true
        }

        if(!faltaCampos) {
            mAuth.createUserWithEmailAndPassword(email.text.toString(), contra.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        procesoCompletado = true
                        guardarRegistroEnSharedPreference(user.text.toString(), email.text.toString(), contra.text.toString(), provider.BASIC.toString())
                    } else {
                        showAlert("Error al crear nuevo usuario")
                    }
                }
        } else showAlert("Compete los campos faltantes")
        return procesoCompletado
    }

    private fun guardarRegistroEnSharedPreference(user:String, email:String, contra:String, provider:String){
        val prefs: SharedPreferences.Editor? = activity?.getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)?.edit()
        prefs?.putString("user", user)
        prefs?.putString("email", email)
        prefs?.putString("credencial", contra)
        prefs?.putString("user", provider)
        prefs?.apply()
    }

    private fun showAlert(mensaje:String) {
        Toast.makeText(activity,mensaje,Toast.LENGTH_LONG).show()
    }
}