package farhan.defra.kotlin.shopee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import farhan.defra.kotlin.R

class BeritaAdapter(
    private val listBerita: List<BeritaModel>,
    private val listener: OnAdapterListener
) : RecyclerView.Adapter<BeritaAdapter.ViewBerita>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewBerita {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_linear, parent, false)
        return ViewBerita(view)
    }

    override fun onBindViewHolder(holder: ViewBerita, position: Int) {
        val berita = listBerita[position]
        holder.imgBerita.setImageResource(berita.gambar)
        holder.tvJudul.text = berita.judul
        holder.tvTanggal.text = berita.tanggal

        holder.itemView.setOnClickListener {
            listener.onClick(berita)
        }
    }

    override fun getItemCount() = listBerita.size

    class ViewBerita(view: View) : RecyclerView.ViewHolder(view) {
        val imgBerita: ImageView = view.findViewById(R.id.imgBerita)
        val tvJudul: TextView = view.findViewById(R.id.tvJudul)
        val tvTanggal: TextView = view.findViewById(R.id.tvTanggal)
    }

    interface OnAdapterListener {
        fun onClick(result: BeritaModel)
    }
}
