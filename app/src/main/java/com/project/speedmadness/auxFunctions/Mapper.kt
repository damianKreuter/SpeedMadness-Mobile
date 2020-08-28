package com.project.speedmadness.auxFunctions

class Mapper(nombre:String, valor:String) {
    private val nombre = nombre
    private val valor = valor

    fun getNombre():String{
        return nombre
    }
    fun getValor():String{
        return valor
    }
}