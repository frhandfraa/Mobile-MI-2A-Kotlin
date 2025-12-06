package farhan.defra.kotlin.Tugas

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import farhan.defra.kotlin.R
import java.text.NumberFormat
import java.util.*

class HasilPesananActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_pesanan)

        val tvNoMeja = findViewById<TextView>(R.id.tvNoMeja)
        val tvNamaPelanggan = findViewById<TextView>(R.id.tvNamaPelanggan)
        val tvTanggalOrder = findViewById<TextView>(R.id.tvTanggalOrder)
        val tvJamOrder = findViewById<TextView>(R.id.tvJamOrder)
        val tvJenisPelanggan = findViewById<TextView>(R.id.tvJenisPelanggan)
        val tblPesanan = findViewById<TableLayout>(R.id.tblPesanan)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val tvDiskon = findViewById<TextView>(R.id.tvDiskon)
        val tvTotalAkhir = findViewById<TextView>(R.id.tvTotalAkhir)
        val btnEdit = findViewById<Button>(R.id.btnEdit)

        tvNoMeja.text = "No Meja : ${intent.getStringExtra("NoMeja")}"
        tvNamaPelanggan.text = "Nama Pelanggan : ${intent.getStringExtra("NamaPelanggan")}"
        tvTanggalOrder.text = "Tanggal : ${intent.getStringExtra("Tanggal")}"
        tvJamOrder.text = "Jam : ${intent.getStringExtra("Jam")}"
        tvJenisPelanggan.text = "Jenis Pelanggan : ${intent.getStringExtra("JenisPelanggan")}"

        @Suppress("UNCHECKED_CAST")
        val pesananList =
            intent.getSerializableExtra("pesananList") as? ArrayList<HashMap<String, String>>

        var total = 0

        pesananList?.forEach { item ->
            val row = TableRow(this)

            val tvMenu = TextView(this)
            tvMenu.text = item["menu"]

            val tvQty = TextView(this)
            tvQty.text = item["qty"]
            tvQty.gravity = android.view.Gravity.CENTER

            val hargaSatuan = item["harga"]?.toInt() ?: 0
            val qty = item["qty"]?.toInt() ?: 0
            val subtotal = hargaSatuan * qty
            total += subtotal

            val tvHarga = TextView(this)
            tvHarga.text = "Rp $subtotal"
            tvHarga.gravity = android.view.Gravity.END

            row.addView(tvMenu)
            row.addView(tvQty)
            row.addView(tvHarga)

            tblPesanan.addView(row)
        }

        val formatter = NumberFormat.getNumberInstance(Locale("in", "ID"))

        // Hitung diskon berdasarkan jenis pelanggan
        val jenisPelanggan = intent.getStringExtra("JenisPelanggan")?.lowercase(Locale.ROOT)
        var diskonPersen = 0.0

        when {
            jenisPelanggan?.contains("reguler") == true -> diskonPersen = 0.10
            jenisPelanggan?.contains("member") == true -> diskonPersen = 0.20
            jenisPelanggan?.contains("vip") == true -> diskonPersen = 0.40
        }

        val nominalDiskon = (total * diskonPersen).toInt()
        val totalAkhir = total - nominalDiskon

        tvTotal.text = "Total : Rp ${formatter.format(total)}"
        tvDiskon.text = "Diskon (${(diskonPersen * 100).toInt()}%) : Rp ${formatter.format(nominalDiskon)}"
        tvTotalAkhir.text = "Total Akhir : Rp ${formatter.format(totalAkhir)}"

        btnEdit.setOnClickListener {
            finish()
        }
    }
}
