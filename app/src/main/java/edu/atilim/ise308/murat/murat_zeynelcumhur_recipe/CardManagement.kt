package edu.atilim.ise308.murat.murat_zeynelcumhur_recipe

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.*
import androidx.fragment.app.DialogFragment

class CardManagement : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(activity)
        val inflater = activity?.layoutInflater
        val cardManagementDialog = inflater?.inflate(R.layout.recipe_cardview, null)


        builder.setView(cardManagementDialog)



        return builder.create()
    }
}