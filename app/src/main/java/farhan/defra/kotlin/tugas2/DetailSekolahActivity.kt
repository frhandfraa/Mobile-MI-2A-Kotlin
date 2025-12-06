package farhan.defra.kotlin.tugas2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import farhan.defra.kotlin.R

class DetailSekolahActivity : AppCompatActivity() {

    private lateinit var imgBerita: ImageView
    private lateinit var tvJudul: TextView
    private lateinit var tvTanggal: TextView
    private lateinit var tvIsi_Berita: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_sekolah)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imgBerita = findViewById(R.id.imgBerita)
        tvJudul = findViewById(R.id.tvJudul)
        tvTanggal = findViewById(R.id.tvTanggal)
        tvIsi_Berita = findViewById(R.id.tvIsi_Berita)

        getData()
    }

    private fun getData() {
        val bundle = intent.extras
        if (bundle != null) {
            imgBerita.setImageResource(bundle.getInt("gambar"))
            tvJudul.text = bundle.getString("judul")
            tvTanggal.text = bundle.getString("tanggal")
            tvIsi_Berita.text = bundle.getString("isi")
        }
    }
}
