package edu.atilim.ise308.murat.murat_zeynelcumhur_recipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private var recipeList: ArrayList<Recipe>? = arrayListOf()
    private var recyclerViewRecipe: RecyclerView? = null
    private var recipeAdapter: RecyclerViewAdapter? = null
    val db by lazy { DatabaseHelper(this) }

    /*var name = findViewById<TextView>(R.id.textViewFoodName).text
    var inside = findViewById<TextView>(R.id.textViewInsideMaterials).text
    var howToMake = findViewById<TextView>(R.id.textViewHowToMake).text
    var calories = findViewById<TextView>(R.id.textViewFoodCalories).text*/
    //val btnRead = findViewById<Button>(R.id.button_read)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnAdd = findViewById<ImageButton>(R.id.toolbar_add_button)
        /*val context = this
        context.deleteDatabase("Recipe_App_Database")*/
        getItems()



        btnAdd.setOnClickListener {
            val newRecipeDialog = NewRecipeDialog(this)
            newRecipeDialog.show(supportFragmentManager,"123")
        }
    }
    fun getItems(){
        recyclerViewRecipe = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        recipeAdapter = RecyclerViewAdapter(db.readData() as ArrayList<Recipe>,this)
        val layoutManager = LinearLayoutManager(this)
        recyclerViewRecipe?.layoutManager = layoutManager
        recyclerViewRecipe?.itemAnimator = DefaultItemAnimator()
        recyclerViewRecipe?.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerViewRecipe!!.adapter = recipeAdapter
    }
}
