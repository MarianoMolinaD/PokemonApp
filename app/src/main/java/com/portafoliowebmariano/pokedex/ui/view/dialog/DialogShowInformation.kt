package com.portafoliowebmariano.pokedex.ui.view.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.portafoliowebmariano.pokedex.databinding.DialogInformationBinding
import com.portafoliowebmariano.pokedex.databinding.DialogNotConexionBinding

object DialogShowInformation {

    fun showDialogInformation(context: Context){
        val inflater = LayoutInflater.from(context)
        val binding = DialogInformationBinding.inflate(inflater)

        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.setCancelable(false)
        alertDialog.setView(binding.root)

        binding.btnDialog.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog.show()
    }
}