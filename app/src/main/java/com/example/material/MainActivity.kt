package com.example.material

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.material.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private var binding:ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding?.containedIconButton?.setOnClickListener(this)

    }
    // Метод создает Диалог всплывающий с выбором
    @SuppressLint("ResourceAsColor")
    override fun onClick(view: View) {

        // Создаем массив 3 элемента
        val multiItems = arrayOf(resources.getString(R.string.onePart), resources.getString(R.string.twoPart),
            resources.getString(R.string.threePart))
        // По умолчанию ничего не выбрано
        val checkedItems = booleanArrayOf(false, false, false)

        // Пустой массив создаем (Для выбранных данных)
        val selectedPart = ArrayList<Any>()

        // Создаем диалоговое окно
        val builder = MaterialAlertDialogBuilder(this, R.style.MyDialogTheme)

            .setTitle(resources.getString(R.string.badBoys))

            .setNeutralButton(resources.getString(R.string.close)) { dialog, which ->

                loadNeutral()

            }

            .setNegativeButton(resources.getString(R.string.no)) { dialog, which ->

                loadNegative()

            }

            .setPositiveButton(resources.getString(R.string.yes)) { dialog, which ->

                // В пустой массив записываем выбранные значения
                val getParts = selectedPart.toString().replace("[","").replace("]","")
                // Выводим из массива в ТекстВью
                binding?.result?.text = resources.getString(R.string.yourSelected) + " " + getParts

            }

                // Если мы что-то выбрали, то это должны добавить в массив
            .setMultiChoiceItems(multiItems, checkedItems) { dialog, which, checked ->

                if (checked) selectedPart.add(multiItems[which])
                else selectedPart.remove(multiItems[which])

            }

            .show()

       builder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor (this, R.color.black))
       builder.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor (this, R.color.black))
       builder.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(ContextCompat.getColor (this, R.color.black))

    }

    private fun loadNeutral() {
        binding?.result?.text = getString(R.string.actionNeutral)
    }

    private fun loadNegative() {
        binding?.result?.text = getString(R.string.actionNegative)
    }

}