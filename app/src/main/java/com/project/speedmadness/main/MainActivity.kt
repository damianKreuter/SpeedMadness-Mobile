package com.project.speedmadness.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.project.speedmadness.R
import com.project.speedmadness.crearPeddo.Crear_pedido_fullscreen
import com.project.speedmadness.loginFragments.LoginHome
import com.project.speedmadness.loginFragments.LoginIngresar
import com.project.speedmadness.loginFragments.LoginRegistrar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home_page.*



class MainActivity : AppCompatActivity(), LoginIngresar.FragmentLoginListener, LoginRegistrar.FragmentRegistroListener
    ,LoginHome.FragmentChequearSiHaySession{
    private var mAuth: FirebaseAuth? = null

    enum class viewPart {
        HOME, PERFIL, CREAR, GRAFICO, AJUSTES
    }

    enum class ProviderType {
        BASIC, GOOGLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //     setContentView(R.layout.logins)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()

        customBottomBar.inflateMenu(R.menu.nav_bar)
        val navController : NavController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(customBottomBar, navController)
        accionDeCreacionDePedido()
    }

    private fun accionDeCreacionDePedido() {
        val fab : FloatingActionButton = floating_action_button
        fab.setOnClickListener{
            Crear_pedido_fullscreen.display(this.supportFragmentManager)
        }
    }

    override fun chequearSession(): Boolean {
        val prefs: SharedPreferences = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val user = prefs.getString("user", null)
        return user.isNullOrEmpty()

    }

    override fun registroNuevo(user: String?, email: String?, credencial: String?, metodo: String) {
        //Se registró nuevo usuario de forma exitosa, recordaremos este registro
        val prefs: SharedPreferences.Editor = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("user", user)
        prefs.putString("email", email)
        prefs.putString("credencial", credencial)
        prefs.putString("user", metodo)
        prefs.apply()
    }

    private fun yaIngresoEsteUsuario(userIngresado:String):Boolean {
        val prefs: SharedPreferences = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val userAnterior = prefs.getString("user", null)
        return userAnterior.equals(userIngresado)
    }

    override fun ingresoNuevo(user: String, credencial: String, metodo: String) {
        //Nuevo Ingreso con usuario, guardamos para podes hacerlo mas directo
        val prefs: SharedPreferences.Editor = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        if(!yaIngresoEsteUsuario(user)){
            prefs.putString("user", user)
            //BUSCAR LUEGO EL TEMA DEL MAIL, SI DEJARLO EN FIREBASE O QUE
       //     prefs.putString("email", email)
            prefs.putString("credencial", credencial)
            prefs.putString("user", metodo)
            prefs.apply()
        }
    }





    fun sessionActiva():Boolean{
        val pref:SharedPreferences = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = pref.getString("email", null)
        val provider = pref.getString("provider",null)
        return email!=null&&provider!=null
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        updateUI(currentUser)


    }

    fun cambiarPantalla (nav : LinearLayout, icono : ImageView, texto : TextView) {
        var params : ViewGroup.LayoutParams = icono.layoutParams

        nav.setOnClickListener{
            Toast.makeText(this, texto.text, Toast.LENGTH_SHORT).show()
            params.width = 100
            params.height = 100
            icono.layoutParams = params
            texto.visibility = View.VISIBLE
        }
    }

    fun cambiarPantalla (nav : FloatingActionButton) {
        nav.setOnClickListener{
            Toast.makeText(this,nav.id, Toast.LENGTH_SHORT).show()
        }
    }

    fun cerrarSesion(){
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.clear()
        prefs.apply()

        FirebaseAuth.getInstance().signOut()
        onBackPressed()
    }

    //QUEDA TERMIANR DE VER ESTE
    fun authentificarConGoogle(){
        val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleClient = GoogleSignIn.getClient(this, googleConf)

        googleClient.signInIntent
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)



    }

    private fun updateUI(currentUser: FirebaseUser?) {
        Toast.makeText(applicationContext, "Creacon con USER: "+currentUser.toString(), Toast.LENGTH_LONG).show()
    }




}