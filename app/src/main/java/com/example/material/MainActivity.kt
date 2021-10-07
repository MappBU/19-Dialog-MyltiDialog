package com.example.material

import android.annotation.SuppressLint
import android.content.DialogInterface.BUTTON_POSITIVE
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.material.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import java.lang.System.load

// Этот виджет используется когда нужно предоставить пользователю 2-3 варианта выбора дальнейших действий
// Но обычно юзают предыдущий СнекБар (Всплывающий снизу)

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private var binding:ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding?.containedIconButton?.setOnClickListener(this)

    }

    @SuppressLint("ResourceAsColor")
    override fun onClick(view: View) {

        // ХЗ что это, но подключаем сюда стиль прописанный в темной теме
        val builder = MaterialAlertDialogBuilder(this, R.style.MyDialogTheme)

                // Титул всплывающего диалога
            .setTitle(resources.getString(R.string.badBoys))

                // Месседж всплывающего диалога
            .setMessage(resources.getString(R.string.messageDialog))

                // Создаем нейтральную кнопку ЗАКРЫТЬ диалог
            .setNeutralButton(resources.getString(R.string.close)) { dialog, which ->

                loadNeutral()

            }

                // Создаем негативную кнопку НЕТ в диалоге
            .setNegativeButton(resources.getString(R.string.no)) { dialog, which ->

                loadNegative()

            }

                // Создаем позитивную кнопку ДА в диалоге
            .setPositiveButton(resources.getString(R.string.yes)) { dialog, which ->

                loadPositive()

            }

            .show()

        // Цвет текста кнопок в Диалоге
        builder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor (this, R.color.black))
        builder.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor (this, R.color.black))
        builder.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(ContextCompat.getColor (this, R.color.black))

    }


    // При тапе по ДА...
    private fun loadPositive() {
        binding?.result?.text = getString(R.string.actionPositive)
    }

    // При тапе по ЗАКРЫТЬ...
    private fun loadNeutral() {
        binding?.result?.text = getString(R.string.actionNeutral)
    }

    // При тапе по НЕТ...
    private fun loadNegative() {
        binding?.result?.text = getString(R.string.actionNegative)
    }

}