package com.samples.itis_android_inception_22.presentation.fragments.editTextPage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.FragmentEditTextPageBinding
import com.samples.itis_android_inception_22.presentation.base.BaseFragment

class EditTextPageFragment : BaseFragment(R.layout.fragment_edit_text_page) {

    private val viewBinding: FragmentEditTextPageBinding by viewBinding(FragmentEditTextPageBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTextChangeListeners()
        initClickListeners()
    }

    private fun initTextChangeListeners() {
        with(viewBinding) {

            /* Добавление слушателя изменения текста при помощи функции расширения. При таком варианте вам не требуется
             * обязательная реализация всех трех методов интерфейса TextWatcher.
             * Данный слушатель проверяет, есть ли введенные символы. Если они есть - показываем подсказку,
             * если нет - скрываем
             * */
            emailInnerEt.addTextChangedListener(onTextChanged = { input: CharSequence?, p1: Int, p2: Int, count: Int ->
                emailDataTextInput.helperText = if (input?.isNotEmpty() == true) {
                    getString(R.string.enter_email_helper_text)
                } else {
                    null
                }
                input?.let {
                    checkResultBtn.isEnabled = it.length >= 10

                }
            })

            /* Добавление слушателя изменений текста стандартным способом. Вы создаете анонимный класс через ключевое
             * слово object (без указания имени). Далее указываете, какой интерфейс должен реализовывать ваш класс.
             * В данном случае это TextWatcher. У интерфейса TextWatcher есть три метода, которые обязательно должны быть
             * реализованы анонимным классом
             * */
            defaultEt.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(p0: Editable?) {}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(input: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(input?.startsWith("+6") == true) {

                    }
                    input?.let {
                        if (it.length > 10) {
                            checkResultBtn.isEnabled = it.length >= 10
                        }
                    }
                }
            }
            )
        }
    }

    private fun initClickListeners() {
        with(viewBinding) {
            checkResultBtn.setOnClickListener {
                val toastTextRes = if (emailInnerEt.text?.contains('@') == true) {
                    R.string.check_passed_text
                } else {
                    R.string.check_failed_text
                }
                Toast.makeText(requireContext(), toastTextRes, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val EDIT_TEXT_PAGE_FRAGMENT_TAG = "EDIT_TEXT_PAGE_FRAGMENT_TAG "

        fun getInstance() = EditTextPageFragment()
    }
}