package farhan.defra.kotlin.shopee

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import farhan.defra.kotlin.R

class LinearRecyclerActivity : AppCompatActivity() {
    private lateinit var rvBerita: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycle_view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvBerita = findViewById(R.id.rvBerita)
        rvBerita.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        setBerita()
    }

    private fun setBerita() {
        val berita = listOf(
            BeritaModel(R.drawable.gambar1, "Judul Berita 1", "10 Oktober 2025",
                "Lorem ipsum dolor sit amet..."),
            BeritaModel(R.drawable.gambar2, "Judul Berita 2", "9 Oktober 2025",
                "Berita kedua isi lengkapnya..."),
            BeritaModel(R.drawable.gambar3, "Judul Berita 3", "8 Oktober 2025",
                "Isi berita ketiga...")
        )

        val beritaAdapter = BeritaAdapter(berita, object : BeritaAdapter.OnAdapterListener {
            override fun onClick(result: BeritaModel) {
                val bundle = Bundle().apply {
                    putInt("gambar", result.gambar)
                    putString("judul", result.judul)
                    putString("tanggal", result.tanggal)
                    putString("isi", result.Isi_Berita)
                }

                val intent = Intent(this@LinearRecyclerActivity, DetailBeritaActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })

        rvBerita.adapter = beritaAdapter
    }
}
