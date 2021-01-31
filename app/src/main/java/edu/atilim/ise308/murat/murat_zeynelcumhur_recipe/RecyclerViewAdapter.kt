package edu.atilim.ise308.murat.murat_zeynelcumhur_recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val recipeList: ArrayList<Recipe>, private val mainActivity: MainActivity) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    val main = mainActivity

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(recipeForDisplay: Recipe) {
            foodName.text = recipeForDisplay.foodName
            foodInsideMaterials.text = recipeForDisplay.insideMaterials
            foodHowToMake.text = recipeForDisplay.howToMake
        }

        var foodName = itemView.findViewById<TextView>(R.id.textViewFoodName)
        var foodInsideMaterials = itemView.findViewById<TextView>(R.id.textViewInsideMaterials)
        var foodHowToMake = itemView.findViewById<TextView>(R.id.textViewHowToMake)
        var deleteButton = itemView.findViewById<ImageButton>(R.id.deleteButton)
        var editButton = itemView.findViewById<ImageButton>(R.id.editButton)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recipe_cardview,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipeForDisplay = recipeList.get(position)
        holder.bind(recipeForDisplay)
        val db = DatabaseHelper(mainActivity)
        holder.foodName.text = "Food Name => ${recipeForDisplay.foodName}"
        holder.foodInsideMaterials.text = "Inside Materials => ${recipeForDisplay.insideMaterials}"
        holder.foodHowToMake.text = "How to Make => ${recipeForDisplay.haveBeenTried}"

        holder.deleteButton.setOnClickListener {
            db.deleteAllData()
            Toast.makeText(mainActivity,"Delete button is clicked", Toast.LENGTH_SHORT).show()
            main.getItems()
        }
        holder.editButton.setOnClickListener {
            db.update(recipeForDisplay.haveBeenTried)
            main.getItems()
        }



    }

    override fun getItemCount() = recipeList.size
}
