package com.example.viewmodel.ui.main

import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.SavedStateHandle
import kotlin.math.E
import kotlin.random.Random

const val RESULT_KEY = "Peso value"

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val curps= ArrayList <String>()

    //Creacion de variables donde se guardara los resultados del fragmento
    private var año=listOf<String>("1")
    private var mes=""
    private var dia=""
    private var nombre=listOf<String>("1")
    private var paterno= listOf<String>("X")
    private var materno=listOf<String>("1")
    private var estado=""
    private var sexo=listOf<String>("1")
    //private var result=0f
    private var result:MutableLiveData<String> = savedStateHandle.getLiveData(RESULT_KEY)

    //Metodo para calcular la CURP
    fun calcularCurp(añoV:String,nombreV: String,paternoV: String,maternoV: String,mesV:String,diaV:String,sexoV:String,estadoV:String){
        //Asignacion de variables recepcionados en el metodo
        año=añoV.uppercase().chunked(2)
        nombre= nombreV.uppercase().chunked(1)
        paterno=paternoV.uppercase().chunked(1)
        materno=maternoV.uppercase().chunked(1)
        mes=mesV
        dia=diaV
        sexo=sexoV.uppercase().chunked(1)
        estado=estadoV

        //Creacion de variables de transformacion de las variables recepcionadas
        var curpIncomp=""
        var curpComp =""
        var existe=false
        var estadoFin=""
        var consoPat="X"
        var consoMat="X"
        var consoNom="X"
        val inicialP=paterno[0]
        val inicialM=materno[0]
        val paternoSub=paterno.subList(1,paterno.lastIndex)
        val maternoSub=materno.subList(1,materno.lastIndex)
        val nombreSub=nombre.subList(1,nombre.lastIndex)

        //METODO PARA IDENTIFICAR LA PRIMERA VOCAL DEL APELLIDO PATERNO
        val vocal =paterno.find { it.equals("A")|| it.equals("E") || it.equals("I") || it.equals("O")||it.equals("U")}.toString()


        //Metodo para identificar la primera consonante del apellido paterno
        consoPat= paternoSub.find{it.equals("B")|| it.equals("C")|| it.equals("D") || it.equals("F") || it.equals("G")
                || it.equals("H") || it.equals("J") || it.equals("K") || it.equals("L") || it.equals("M") || it.equals("N")
                || it.equals("Ñ") || it.equals("P") || it.equals("Q") || it.equals("R")|| it.equals("S")|| it.equals("T")
                || it.equals("V") || it.equals("W")|| it.equals("X")|| it.equals("Y") || it.equals("Z") }.toString()

        //Metodo para identificar la primera consonante del apellido materno
        consoMat=maternoSub.find{it.equals("B")|| it.equals("C")|| it.equals("D") || it.equals("F") || it.equals("G")
                || it.equals("H") || it.equals("J") || it.equals("K") || it.equals("L") || it.equals("M") || it.equals("N")
                || it.equals("Ñ") || it.equals("P") || it.equals("Q") || it.equals("R")|| it.equals("S")|| it.equals("T")
                || it.equals("V") || it.equals("W")|| it.equals("X")|| it.equals("Y") || it.equals("Z") }.toString()

        //Metodo para identificar la primera consonante del nombre
        consoNom=nombreSub.find{it.equals("B")|| it.equals("C")|| it.equals("D") || it.equals("F") || it.equals("G")
                || it.equals("H") || it.equals("J") || it.equals("K") || it.equals("L") || it.equals("M") || it.equals("N")
                || it.equals("Ñ") || it.equals("P") || it.equals("Q") || it.equals("R")|| it.equals("S")|| it.equals("T")
                || it.equals("V") || it.equals("W")|| it.equals("X")|| it.equals("Y") || it.equals("Z") }.toString()

        //Asignacion en caso de no encontrar consonante en alguno de las condiciones previas
        if(consoMat=="null"){consoMat="X"}
        if(consoPat=="null"){consoPat="X"}
        if(consoNom=="null"){consoNom="X"}

        //Cambio de variable de estado especifico a abreviacion
        when(estado){
            "Aguascalientes" -> estadoFin="AS"
            "Baja California" ->estadoFin ="BC"
            "Baja California Sur"-> estadoFin="BS"
            "Campeche" -> estadoFin="CC"
            "Chiapas" -> estadoFin="CS"
            "Chihuahua" -> estadoFin="CH"
            "Ciudad de Mexico"-> estadoFin="DF"
            "Coahuila" ->estadoFin="CL"
            "Colima"-> estadoFin="CM"
            "Durango" -> estadoFin="DG"
            "Guanajuato" -> estadoFin="GT"
            "Guerrero" ->estadoFin ="GR"
            "Hidalgo"-> estadoFin="HG"
            "Jalisco" -> estadoFin="JC"
            "Mexico"->estadoFin="MC"
            "Michoacan"->estadoFin="MN"
            "Morelos" -> estadoFin="MS"
            "Nayarit"->estadoFin="NT"
            "Nuevo Leon"->estadoFin="NL"
            "Oaxaca"->estadoFin="OC"
            "Puebla"->estadoFin="PL"
            "Queretaro"->estadoFin="QO"
            "Quintana Roo"->estadoFin="QR"
            "San Luis Potosi"->estadoFin="SP"
            "Sinaloa"->estadoFin="SL"
            "Sonora"->estadoFin="SR"
            "Tabasco"->estadoFin="TC"
            "Taumalipas"->estadoFin="TS"
            "Tlaxcala"->estadoFin="TL"
            "Veracruz"->estadoFin="VZ"
            "Yucatan"->estadoFin="YN"
            "Zacatecas"->estadoFin="ZS"

        }

        //Asignacion de variable temporal
        curpIncomp=inicialP+ vocal+inicialM+nombre[0]+año[1]+mes+dia+sexo[0]+estadoFin+consoPat+consoMat+consoNom

        //COMPARACION DE EXISTENCIA PREVIA DE CURP
        if(curps.isNotEmpty()){
            for (i in 0 until curps.size){
                var curpArreglo=curps[i].chunked(16)
                println("Curp arreglo: "+curpArreglo)
                println("Curp incompleto: "+curpIncomp)
                if(curpArreglo[0]==curpIncomp){
                    existe=true
                    curpComp=curps[i]
                }
            }
        }

        //METODO PARA IDENTIFICAR LAS HOMOCLAVES DEPENDIENDO DE SI EXISTE PREVIAMENTE
        if(existe==false){
            //ARREGLO QUE UTILIZA PARA CREAR LA HOMOCLAVE
            val homoclaveDig= arrayOf <String> ("1","2","3","4","5","6","7","8","9","A","B","C","D","F","G","H","I","J","K","L","M","N","O","P","Q","R",
                "S","T","U","V","W","X","Y","Z")

            //GENERACION DE LA HOMOCLAVE
            val randomValue1= Random.nextInt(0,homoclaveDig.size)
            val randomValue2= Random.nextInt(0,homoclaveDig.size)
            //LLENADO DE LA VARIBALE DE CURPCOMP
            curpComp=curpIncomp+homoclaveDig[randomValue1]+homoclaveDig[randomValue2]
            curps.add(curpComp)
        }


        println("Curp: "+curpComp)


        savedStateHandle.set(RESULT_KEY,curpComp)
        //result.setValue(value.toFloat()*usdToPesos)
    }

    //fun getResult():String?{
    fun getResult(): String? {
        return result.value
    }


}

