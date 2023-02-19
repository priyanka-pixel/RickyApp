package com.example.rickyapp.characterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.rickyapp.data.entities.Result
import com.example.rickyapp.data.local.CharacterDAO
import com.example.rickyapp.databinding.RowCharacterBinding

/**
 * Extend ListAdapter<Entity, Viewholder>
 * Create DiffUtil
 * Include this in CharacterAdapter extension
 * ===
 * Viewholder: cache the view references
 *  - Inside the CharacterAdapter class we create the MyViewHolder Class(viewbinding)
 *  - Extend RecyclerView.ViewHolder(viewbinding.root)
 *  ==
 *  Override methods
 *   - onCreateViewHolder: LayoutInflater
 *   - onBindViewHolder: data binding
 *
 *  ====
 *  Click listerner
 *  DOESNOT have its own callback for clicks: custom
 *    1 Create inner class OnClickListener : onClick
 *    2 In constructor pass OnClickListener as parameter
 *    3 onBind() -> setOnClickListener
 */
class CharacterAdapter(private val onClickListener: OnClickListener): ListAdapter<Result, CharacterAdapter.MyViewHolder>(CharacterItemDiffCallback()) {

    class OnClickListener(val clickListener : (character: Result) -> Unit){
          // delegate
          fun onClick(character: Result) = clickListener(character)
      }

    class MyViewHolder(val rowCharacterBinding: RowCharacterBinding)
        : RecyclerView.ViewHolder(rowCharacterBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = RowCharacterBinding.inflate(view, parent, false)
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // data == view
        val characterList = getItem(position)

        holder.rowCharacterBinding.tvName.text = characterList.name
        // Glide
        Glide.with(holder.itemView.context)
            .load(characterList.image)
            .override(100,100)
            .centerCrop()
            .transform(CircleCrop())
            .into(holder.rowCharacterBinding.imgImage)

        holder.itemView.setOnClickListener{
            onClickListener.onClick(characterList)
        }

    }
}



class CharacterItemDiffCallback : DiffUtil.ItemCallback<Result>(){
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
        oldItem== newItem

}

