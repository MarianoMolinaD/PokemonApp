package com.portafoliowebmariano.pokedex.ui.view.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.portafoliowebmariano.pokedex.databinding.DialogNotConexionBinding

object DialogShowErrorNetwork {

    fun showDialogErrorNet(context: Context, message: String){
        val inflater = LayoutInflater.from(context)
        val binding = DialogNotConexionBinding.inflate(inflater)

        val alerDialog = AlertDialog.Builder(context).create()
        alerDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        alerDialog.setCancelable(false)
        alerDialog.setView(binding.root)

        binding.tvMessageDialog.text = message

        binding.btnDialog.setOnClickListener {
            alerDialog.dismiss()
        }

        alerDialog.show()
    }
}