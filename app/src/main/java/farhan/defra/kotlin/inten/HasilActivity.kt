package farhan.defra.kotlin.inten

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import farhan.defra.kotlin.R

class HasilActivity : AppCompatActivity() {
    private lateinit var btnHasil: Button
    private lateinit var tvNIDN: TextView
    private lateinit var tvNama: TextView
    private lateinit var tvUmur: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hasil)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnHasil = findViewById(R.id.btnHasil)
        tvNIDN = findViewById(R.id.tvNIDN)
        tvNama = findViewById(R.id.tvNama)
        tvUmur = findViewById(R.id.tvUmur)
    }
    override fun onStart() {
        super.onStart()
        Hasil()
        getData()
    }

    fun getData(){
        val bundle = intent.extras
        if(bundle != null){
            tvNIDN.text = "NIDN : ${bundle.getInt("pNIDN",0)}"
            tvNama.text = "NAMA : ${bundle.getString("pNama")}"
            tvUmur.text = "UMUR : ${bundle.getInt("pUmur",0)}"
        }
    }
    fun Hasil() {
        btnHasil.setOnClickListener {
            //Pindah Activity
            startActivity(Intent(this, ExplisitIntenActivity::class.java))
        }
    }
}
