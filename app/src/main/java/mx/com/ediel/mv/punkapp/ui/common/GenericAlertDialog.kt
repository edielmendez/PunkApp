package mx.com.ediel.mv.punkapp.ui.common

import android.content.Context
import androidx.appcompat.app.AlertDialog

class GenericAlertDialog {
    companion object{
        fun showDialog(
            context: Context,
            title: String,
            message: String,
            textBtnAccept: String = "Aceptar",
            textBtnCancel: String = "Cancelar",
            onBtnAcceptClick: ( () -> Unit )? = null,
            onBtnCancelClick: ( () -> Unit )? = null
        ){

            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(message)
            onBtnAcceptClick?.let {
                builder.setPositiveButton(textBtnAccept){ dialog, _ ->
                    it.invoke()
                    dialog.dismiss()
                }
            }
            onBtnCancelClick?.let {
                builder.setNegativeButton(textBtnCancel){ dialog, _ ->
                    it.invoke()
                    dialog.dismiss()
                }
            }

            builder.create()
            builder.show()
        }

    }
}