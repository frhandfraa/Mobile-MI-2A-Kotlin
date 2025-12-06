package farhan.defra.kotlin.exuts

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import farhan.defra.kotlin.R

class HasilFormGajiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_form_gaji)

        val nip = intent.getStringExtra("NIP")
        val nama = intent.getStringExtra("NAMA")
        val tanggal = intent.getStringExtra("TANGGAL")
        val jam = intent.getStringExtra("JAM")
        val status = intent.getStringExtra("STATUS")
        val anak = intent.getStringExtra("ANAK")
        val golongan = intent.getStringExtra("GOLONGAN")
        val uangMakan = intent.getIntExtra("UANG_MAKAN", 0)
        val lembur = intent.getIntExtra("LEMBUR", 0)
        val dinasLuar = intent.getIntExtra("DINAS_LUAR", 0)
        val tunjangan = intent.getIntExtra("TUNJANGAN", 0)
        val gajiPokok = intent.getIntExtra("GAJI_POKOK", 0)
        val total = intent.getIntExtra("TOTAL_GAJI", 0)

        val tvNIP = findViewById<TextView>(R.id.tvNIP)
        val tvNama = findViewById<TextView>(R.id.tvNama)
        val tvTanggal = findViewById<TextView>(R.id.tvTanggalLahir)
        val tvJam = findViewById<TextView>(R.id.tvJamBayar)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)
        val tvAnak = findViewById<TextView>(R.id.tvJumlahAnak)
        val tvGolongan = findViewById<TextView>(R.id.tvGolongan)
        val tvGajiPokok = findViewById<TextView>(R.id.tvGajiPokok)
        val tvUangMakan = findViewById<TextView>(R.id.tvUangMakan)
        val tvLembur = findViewById<TextView>(R.id.tvLembur)
        val tvDinasLuar = findViewById<TextView>(R.id.tvDinasLuar)
        val tvTunjangan = findViewById<TextView>(R.id.tvTunjangan)
        val tvTotal = findViewById<TextView>(R.id.tvTotalGaji)
        val btnKembali = findViewById<Button>(R.id.btnKembali)

        tvNIP.text = "NIP: $nip"
        tvNama.text = "Nama: $nama"
        tvTanggal.text = "Tanggal Lahir: $tanggal"
        tvJam.text = "Jam Bayar: $jam"
        tvStatus.text = "Status: $status"
        tvAnak.text = "Jumlah Anak: $anak"
        tvGolongan.text = "Golongan: $golongan"

        tvGajiPokok.text = "Gaji Pokok: Rp $gajiPokok"
        tvUangMakan.text = "Uang Makan: Rp $uangMakan"
        tvLembur.text = "Lembur: Rp $lembur"
        tvDinasLuar.text = "Dinas Luar: Rp $dinasLuar"
        tvTunjangan.text = "Tunjangan Keluarga: Rp $tunjangan"

        tvTotal.text = "Total Gaji: Rp $total"

        btnKembali.setOnClickListener {
            finish()
        }
    }
}
