package farhan.defra.kotlin.widgets

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import farhan.defra.kotlin.R
import farhan.defra.kotlin.inten.ExplisitIntenActivity

class HasilFormPegawaiActivity : AppCompatActivity() {
    private lateinit var tvNIP: TextView
    private lateinit var tvNama: TextView
    private lateinit var tvTempatLahir: TextView
    private lateinit var tvTglLahir: TextView
    private lateinit var tvJam: TextView
    private lateinit var tvUmur: TextView
    private lateinit var tvNIK: TextView
    private lateinit var tvKK: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hasil_form_pegawai)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvNIP = findViewById(R.id.tvNIP)
        tvNama = findViewById(R.id.tvNama)
        tvTempatLahir = findViewById(R.id.tvTempatLahir)
        tvTglLahir = findViewById(R.id.tvTglLahir)
        tvJam = findViewById(R.id.tvJam)
        tvUmur = findViewById(R.id.tvUmur)
        tvNIK = findViewById(R.id.tvNIK)
        tvKK = findViewById(R.id.tvKK)
    }

    fun getData(){
        val bundle = intent.extras
        if(bundle != null){
            tvNIP.text = "NIP : ${bundle.getString("pNIP")}"
            tvNama.text = "NAMA : ${bundle.getString("pNama")}"
            tvTempatLahir.text = "Tempat Lahir : ${bundle.getString("pTempatLahir")}"
            tvTglLahir.text = "Tanggal Lahir : ${bundle.getString("pTglLahir")}"
            tvJam.text = "Jam : ${bundle.getString("pJam")}"
            tvUmur.text = "Umur : ${bundle.getString("pUmur")}"
            tvNIK.text = "NIK : ${bundle.getString("pNIK")}"
            tvKK.text = "KK : ${bundle.getString("pKK")}"

        }


    }
    override fun onStart() {
        super.onStart()
        getData()

    }
}