package com.example.ortalamahesapla

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.yeni_ders_layout.view.*
import kotlinx.android.synthetic.main.yeni_ders_layout.view.btnDersSil
import kotlinx.android.synthetic.main.yeni_ders_layout.view.spnYeniDersKredi

class MainActivity : AppCompatActivity() {

    private val Dersler= arrayOf("Matematik","Türkçe","Fizik","Edebiyat","Algoritma","Tarih")
    private var tumDerslerinBilgileri:ArrayList<Dersler> = ArrayList(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter=ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,Dersler)
        etDersAd.setAdapter(adapter)

        if (rootscroll.childCount==0){
            btnHesapla.visibility= View.INVISIBLE
        }else{btnHesapla.visibility= View.VISIBLE}


        btnDersEkle.setOnClickListener {


            if (!etDersAd.text.isNullOrEmpty()){
                var inflater= LayoutInflater.from(this)

                var yeniDersView=inflater.inflate(R.layout.yeni_ders_layout,null)
                yeniDersView.etYeniDersAd.setAdapter(adapter)



                var dersAdi= etDersAd.text.toString()
                var dersKredi= spnDersKredi.selectedItem.toString()
                var dersNotu= spnDersNot.selectedItem.toString()

                yeniDersView.etYeniDersAd.setText(dersAdi)
                yeniDersView.spnYeniDersKredi.setSelection(spnDersKredi.selectedItemPosition)
                yeniDersView.spnYeniDersNot.setSelection(spnDersNot.selectedItemPosition)


                yeniDersView.btnDersSil.setOnClickListener {

                    rootscroll.removeView(yeniDersView)

                    if (rootscroll.childCount==0){
                        btnHesapla.visibility= View.INVISIBLE
                    }else{btnHesapla.visibility= View.VISIBLE}

                }

                rootscroll.addView(yeniDersView)

                btnHesapla.visibility= View.VISIBLE
                sıfırla()


            }else{
                FancyToast.makeText(this,"Ders Adı Giriniz !",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show()

            }


        }

    }

    fun sıfırla(){

        etDersAd.setText("")
        spnDersKredi.setSelection(0)
        spnDersNot.setSelection(0)
    }

    fun hesapla(view: View) {
        var toplamNot:Double=0.0
        var toplamKredi:Double=0.0

        for (i in 0..rootscroll.childCount-1){

            var teksatir= rootscroll.getChildAt(i)

            var geciciDers= Dersler(teksatir.etYeniDersAd.text.toString(),
                ((teksatir.spnYeniDersKredi.selectedItemPosition)+1).toString(),
                teksatir.spnYeniDersNot.selectedItem.toString())

            tumDerslerinBilgileri.add(geciciDers)
        }

        for (oankiDers in tumDerslerinBilgileri){

            toplamNot+= harfNotuCevir(oankiDers.dersHarf)*(oankiDers.dersKredisi.toDouble())
            toplamKredi+=oankiDers.dersKredisi.toDouble()
        }
        FancyToast.makeText(this,"ORTALAMA: "+(toplamNot/toplamKredi),FancyToast.LENGTH_LONG,FancyToast.INFO,true).show()
        tumDerslerinBilgileri.clear()
    }

    fun harfNotuCevir(gelenHarf:String):Double{

        var deger= 0.0

        when(gelenHarf){

            "AA"->deger=4.0

            "BA"->deger=3.5

            "BB"->deger=3.0

            "BC"->deger=2.5

            "CC"->deger=2.0

            "DC"->deger=1.5

            "DD"->deger=1.0

            "FF"->deger=0.0
        }
        return  deger
    }


}
