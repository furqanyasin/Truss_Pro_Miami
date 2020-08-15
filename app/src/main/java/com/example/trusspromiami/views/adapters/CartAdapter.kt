package com.example.trusspromiami.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trusspromiami.R
import com.example.trusspromiami.databinding.CartCardItemBinding
import com.example.trusspromiami.listeners.CartItemClickListener
import com.example.trusspromiami.models.cart.CartItem
import java.lang.ref.WeakReference

class CartAdapter(var context: WeakReference<Context>, private val cartList: ArrayList<CartItem>, var cartItemClickListener: CartItemClickListener) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var quantityValue: Int = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = DataBindingUtil.inflate<CartCardItemBinding>(LayoutInflater.from(context.get()), R.layout.cart_card_item, parent, false)
        return CartViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        var cart = cartList[position]
        holder.itemName.text = cart.title.toString()
        holder.itemPrice.text = cart.price.toString()


        holder.btnAdd.setOnClickListener {


            holder.itemquantity.text = quantityValue++.toString()

        }
        holder.btnMinus.setOnClickListener {
            if (quantityValue > 0) {
                holder.itemquantity.text = quantityValue--.toString()
            }


        }
        holder.btnDelete.setOnClickListener {

            cartItemClickListener.deleteCartItem(cart.product, position)

        }

    }


    fun removeAt(position: Int?) {

        if (position != null) {
            cartList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartList.size)
        }

    }


    class CartViewHolder constructor(binding: CartCardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var itemName = binding.tvName
        var itemPrice = binding.tvPrice
        var itemTotalPrice = binding.tvTotalPrice
        var itemquantity = binding.quantityText
        var btnAdd = binding.btnAdd
        var btnMinus = binding.btnMinus
        var btnDelete = binding.btnDelete

    }

}




