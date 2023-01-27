package fr.isen.bernet.androidcontactds

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ContactAdapter(var mList: ArrayList<Results>, val onContactClickListener: (contactName: String) -> Unit) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]

        holder.firstName.text = itemsViewModel.name?.first
        holder.lastName.text = itemsViewModel.name?.last
        holder.addressNumber.text = itemsViewModel.location?.street?.number.toString()
        holder.street.text = itemsViewModel.location?.street?.name
        holder.state.text = itemsViewModel.location?.state
        holder.city.text = itemsViewModel.location?.city
        holder.email.text = itemsViewModel.email

        val pp = itemsViewModel.picture?.large
        if (pp != null) {
            Picasso.get().load(pp).resize(150,150).into(holder.pp)
        }

        holder.itemView.setOnClickListener() {
            itemsViewModel.name?.first?.let { it1 -> onContactClickListener(it1) }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val firstName: TextView = itemView.findViewById(R.id.cardFirstName)
        val lastName: TextView = itemView.findViewById(R.id.cardLastName)
        val email: TextView = itemView.findViewById(R.id.cardMail)
        val addressNumber: TextView = itemView.findViewById(R.id.cardNumber)
        val street: TextView = itemView.findViewById(R.id.cardStreet)
        val state: TextView = itemView.findViewById(R.id.cardState)
        val city: TextView = itemView.findViewById(R.id.cardCity)

        val pp: ImageView = itemView.findViewById(R.id.imageView)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshList(contactFromAPI: ArrayList<Results>) {
        mList = contactFromAPI
        notifyDataSetChanged()
    }
}