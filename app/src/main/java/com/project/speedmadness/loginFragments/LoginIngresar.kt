package com.project.speedmadness.loginFragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.project.speedmadness.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginIngresar.newInstance] factory method to
 * create an instance of this fragment.
 */
enum class ProviderType {
    BASIC, GOOGLE
}

class LoginIngresar : Fragment() {
    // TODO: Rename and change types of parameters
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var user: EditText
    private lateinit var contra: EditText

    private lateinit var viewFragment: View

    private val requestNum = 100

    private lateinit var botonIngresar:Button

    enum class provider {
        BASIC, GOOGLE, FACEBOOK, TWITTER
    }

    interface FragmentLoginListener {
        fun ingresoNuevo(user: String, credencial:String, metodo:String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_ingresar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        botonIngresar = view.findViewById(R.id.buttonIngresarLoginIngresar)
        user = view.findViewById(R.id.EditTextUserIngreso)
        contra = view.findViewById(R.id.EditTextContraseniaInput)

        viewFragment = view

        val btnGoogle:ImageView = viewFragment.findViewById(R.id.icGoogle)
        btnGoogle.setOnClickListener(){
            ingresoConGoogle()
        }

        //EVALUAR SI EXISTEN EN FIREBASE
        botonIngresar.setOnClickListener(){
            if(loginCorrecto()) {
                ingresarAHome()
            }
        }


    }



    private fun ingresoConGoogle() {
        val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleClient = activity?.let { GoogleSignIn.getClient(it,googleConf) }

        if(googleClient!=null){
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, requestNum)

        }
    }

    private fun ingresarAHome(){
        val navController = Navigation.findNavController(viewFragment)
        val buttonIngresar: Button = viewFragment.findViewById(R.id.buttonIngresarLoginIngresar)
        buttonIngresar.setOnClickListener(View.OnClickListener { navController.navigate(R.id.action_loginIngresar_to_homePage) })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==requestNum){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)

                if(account!=null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener() {
                            if (it.isSuccessful) {
                                guardarDatosDeSession(
                                    account.email.toString(),
                                    provider.GOOGLE.toString()
                                )
                                mostrarMensaje("Se logro guardar sesion del usuario")
                                ingresarAHome()
                            } else mostrarMensaje("No se pudo auntentificar el usuario con Google")
                        }
                }
            } catch (e: ApiException){
                mostrarMensaje("No se puede realizar la operacion")
            }

        }
    }



    private fun loginCorrecto(): Boolean{
        //CHEQUEAR CON FIREBASE
        var loginCorrecto:Boolean = false
        var camposVacios:Boolean = false
        if(user.text.toString().isNotEmpty()) {
            camposVacios=true
        }

        if(contra.text.toString().isNotEmpty()){
            camposVacios=true
        }

        if(camposVacios) {
            mAuth.signInWithEmailAndPassword(user.text.toString(), contra.text.toString())
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        loginCorrecto = true
                        guardarDatosDeSession(user.text.toString(), "email", contra.text.toString(), provider.BASIC.toString())
                    } else mostrarMensaje("No se pudo auntentificar el usuario")
                }
        }
        return loginCorrecto
    }

    private fun guardarDatosDeSession(user:String, email:String, contra:String, provider:String) {
        val prefs: SharedPreferences.Editor? = activity?.getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)?.edit()
        prefs?.putString("user", user)
        prefs?.putString("email", email)
        prefs?.putString("credencial", contra)
        prefs?.putString("user", provider)
        prefs?.apply()
    }
    private fun guardarDatosDeSession(email:String,provider:String) {
        val prefs: SharedPreferences.Editor? = activity?.getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)?.edit()
        prefs?.putString("email", email)
        prefs?.putString("provider", provider)
        prefs?.apply()
    }


    private fun mostrarMensaje(mensaje:String){
        Toast.makeText(activity, mensaje,Toast.LENGTH_LONG).show()
    }

}