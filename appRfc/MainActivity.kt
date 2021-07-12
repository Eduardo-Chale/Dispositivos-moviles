package com.example.apprfc

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    //CREACION DE ARREGLOS PARA COMPROBACION DE RFC EXISTENTE
    val rfcs= ArrayList <String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //CREACION DE ARREGLOS PARA LLENAR LOS SPINNERS
        val mes = arrayOf <String> ("01","02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")
        val anio = ArrayList <Int>()
        for(i in 1900 until 2022){
            anio.add(i)
        }
        val dia = arrayOf <String>("01","02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31")




        //UNION DE LOS VIEW SPINNER CON LOS DATOS DE LOS ARREGLOS
        val spinMes= findViewById<Spinner>(R.id.spnMes)
        val spinAnio= findViewById<Spinner>(R.id.spnAnio)
        val spinDia= findViewById<Spinner>(R.id.spnDia)

        var adapterMes= ArrayAdapter(this,android.R.layout.simple_list_item_1,mes)
        spinMes.adapter=adapterMes

        var adapterAnio= ArrayAdapter(this,android.R.layout.simple_list_item_1,anio)
        spinAnio.adapter=adapterAnio

        var adapterDia= ArrayAdapter(this,android.R.layout.simple_list_item_1,dia)
        spinDia.adapter=adapterDia
    }

    //METODO PARA CONSEGUIR EL RFC
    fun getRFC(view: View){
        //CREACION DE VARIABLES DONDE SE IMPRIME EL RFC COMPLETO, SIN HOMOCLAVE Y VARIABLE DE COMPROBACION
        var RfcIncomp=""
        var RfcComp =""
        var existe=false


        //TRANSFORMACION DE LAS VARIABLES DENTRO DE LOS SPINNERS
        val anio = spnAnio.getSelectedItem().toString()
        val mes = spnMes.getSelectedItem().toString()
        val dia =spnDia.getSelectedItem().toString()

        //SEPARACION DE LAS CADENAS DE CARACTERS DONDE ESTAN LOS NOMBRES, APELLIDOS Y AÑO DE NACIMIENTO
        val paterno = editTxtPaterno.text.toString().toUpperCase().chunked(1)
        val materno =editTxtMaterno.text.toString().toUpperCase().chunked(1)
        val nombre =editTxtName.text.toString().toUpperCase().chunked(1)
        val anioDig=anio.chunked(1)


        //METODO PARA IDENTIFICAR LA PRIMERA VOCAL DEL APELLIDO PATERNO
        val vocal =paterno.find { it.equals("A")|| it.equals("E") || it.equals("I") || it.equals("O")||it.equals("U")}

        //LLENADO DE LA VARIBALE DE RFCINCOMP
        RfcIncomp=paterno[0]+vocal+materno[0]+nombre[0]+anioDig[2]+anioDig[3]+mes+dia





        //COMPARACION DE EXISTENCIA PREVIA DE RFC
        if(rfcs.isNotEmpty()){
            for (i in 0 until rfcs.size){
                var rfcArreglo=rfcs[i].chunked(10)
                println("Rfc arreglo: "+rfcArreglo)
                println("RFC incompleto: "+RfcIncomp)
                if(rfcArreglo[0]==RfcIncomp){
                    existe=true
                    RfcComp=rfcs[i]
                }
            }
        }

        println("Rfc completo: "+RfcComp)

        if(existe==false){
            //ARREGLO QUE UTILIZA PARA CREAR LA HOMOCLAVE
            val homoclaveDig= arrayOf <String> ("1","2","3","4","5","6","7","8","9","A","B","C","D","F","G","H","I","J","K","L","M","N","O","P","Q","R",
                "S","T","U","V","W","X","Y","Z")

            //GENERACION DE LA HOMOCLAVE
            val randomValue1= Random.nextInt(0,homoclaveDig.size)
            val randomValue2= Random.nextInt(0,homoclaveDig.size)
            val randomValue3= Random.nextInt(0,homoclaveDig.size)
            //LLENADO DE LA VARIBALE DE RFCCOMP
            RfcComp=RfcIncomp+homoclaveDig[randomValue1]+homoclaveDig[randomValue2]+homoclaveDig[randomValue3]
            rfcs.add(RfcComp)
        }




        //IMPRESION DE LA VARIABLE DENTRO DE LA APLICACION Y MEDIANTE LINEA DE CODIGO
        println(RfcComp)
        txtRFC.text=RfcComp
    }

    //METODO PARA LIMPIAR TODOS LOS CAMPOS EN CASO DE QUERER PONER UN NUEVO RFC
    fun cleanOptions (view: View){
        editTxtName.text.clear()
        editTxtMaterno.text.clear()
        editTxtPaterno.text.clear()

        spnDia.setSelection(0)
        spnAnio.setSelection(0)
        spnMes.setSelection(0)
        txtRFC.text=""
    }
}