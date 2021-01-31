package edu.atilim.ise308.murat.murat_zeynelcumhur_recipe

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class NewRecipeDialog(mainActivity: MainActivity) : DialogFragment() {
    val main = mainActivity
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(activity)
        val inflater = activity?.layoutInflater
        val newRecipeDialog = inflater?.inflate(R.layout.add_recipe_dialog, null)

        val editTextFoodName = newRecipeDialog?.findViewById<EditText>(R.id.editTextFoodName)
        val editTextInsideMaterials =
            newRecipeDialog?.findViewById<EditText>(R.id.editTextInsideMaterials)
        val editTextHowToMake = newRecipeDialog?.findViewById<EditText>(R.id.editTextHowToMake)
        val editCheckTried = newRecipeDialog?.findViewById<CheckBox>(R.id.checkBoxTried)

        val buttonAddRecipe = newRecipeDialog?.findViewById<Button>(R.id.buttonAddDialog)
        val cancelAddRecipe = newRecipeDialog?.findViewById<Button>(R.id.buttonCancelDialog)
        val context = this.context
        val db = DatabaseHelper(context!!)


        builder.setView(newRecipeDialog)

        cancelAddRecipe?.setOnClickListener {
            dismiss()
        }
        buttonAddRecipe?.setOnClickListener {

            if (editCheckTried?.isChecked == true) {
                val recipe = Recipe(
                    foodName = editTextFoodName?.text.toString(),
                    insideMaterials = editTextInsideMaterials?.text.toString(),
                    howToMake = editTextHowToMake?.text.toString(),
                    haveBeenTried = 1
                )
                db.insertData(recipe)
            }
            else if (editCheckTried?.isChecked == false){
                val recipe = Recipe(
                    foodName = editTextFoodName?.text.toString(),
                    insideMaterials = editTextInsideMaterials?.text.toString(),
                    howToMake = editTextHowToMake?.text.toString(),
                    haveBeenTried = 0
                )
                db.insertData(recipe)
            }

            dismiss()
            main.getItems()
        }



        return builder.create()
    }


}