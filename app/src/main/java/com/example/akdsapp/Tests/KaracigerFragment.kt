package com.example.akdsapp.Tests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.akdsapp.Models.ChatRoom
import com.example.akdsapp.Models.RoomMessages
import com.example.akdsapp.Models.Tahlils
import com.example.akdsapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_karaciger.*
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [KaracigerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KaracigerFragment : DialogFragment() {

    lateinit var altName: EditText
    lateinit var astName: EditText
    lateinit var ggtName: EditText
    lateinit var btnkaraciger: Button
    var mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mDatabase=FirebaseDatabase.getInstance().getReference("Tahlils")
        var view = inflater!!.inflate(R.layout.fragment_karaciger, container, false )
        altName= view.findViewById(R.id.altTxt)
        astName= view.findViewById(R.id.astTxt)
        ggtName= view.findViewById(R.id.ggtTxt)
        btnkaraciger=view.findViewById(R.id.btnKaraciger)
        val user = mAuth.currentUser
        val uid = user!!.uid
        btnkaraciger.setOnClickListener {
            if(!altName.text.isNullOrEmpty() && !astName.text.isNullOrEmpty() && !ggtName.text.isNullOrEmpty()){
                var ref = FirebaseDatabase.getInstance().reference
                var tahlild=mDatabase.push().key
                var newTahlil = Tahlils()
                val user = mAuth.currentUser
                val uid = user!!.uid
                newTahlil.uid= FirebaseAuth.getInstance().currentUser?.uid!!
                newTahlil.Name=FirebaseAuth.getInstance().currentUser?.email!!
                newTahlil.dgr1=altName.text.toString()
                newTahlil.dgr2=astName.text.toString()
                newTahlil.dgr3=ggtName.text.toString()
                ///NOT JAVA FUZZY KÜTÜPHANESİNİ ÇALIŞTIRAMADIM TÜM AYARLAMALARA RAĞMEN
                /*String fileName = "karacigerfuzzy.fcl";
                FIS fis = FIS.load(in, true);

                // Set inputs
                fis.setVariable("dgr1", alt);
                fis.setVariable("dgr2", ast);
                fis.setVariable("dgr3", ggt);
                //Çalıştırma
                fis.evaluate();
                val risksonuc = fis.getFunctionBlock("fuzzyKaraciger").getVariable("Risk");

                */

                newTahlil.type="Karaciğer Testi"
                newTahlil.risksonuc="normal, az riskli, riskli, çok riskli"
                newTahlil.tarih=getMesajTarih()
                    mDatabase.child(tahlild.toString()).setValue(newTahlil)

                Toast.makeText(activity,"Test başarıyla oluşturuldu ", Toast.LENGTH_SHORT).show()

                dialog?.dismiss()
            }else
            {
                Toast.makeText(activity,"Boş alan bırakılamaz", Toast.LENGTH_SHORT).show()
            }
        }

        return view

    }

    private fun getMesajTarih() :String{
        var sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("tr"))
        return sdf.format(Date())
    }
}