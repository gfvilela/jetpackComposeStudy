package br.dev.gustavovilela.firstprojectcompose.presentation.ui.demo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DemoViewModel : ViewModel() {
    var text = mutableStateOf("")
        private set
    var password = mutableStateOf("")
        private set
    var sliderPosition = mutableStateOf(0f)
        private set
    var switchState = mutableStateOf(false)
        private set
    var checked = mutableStateOf(false)
        private set

    fun onTextChange(newText: String) {
        text.value = newText
    }

    fun onPasswordChange(newPassword: String) {
        password.value = newPassword
    }

    fun onSliderChange(newPosition: Float) {
        sliderPosition.value = newPosition
    }

    fun onSwitchChange(newState: Boolean) {
        switchState.value = newState
    }

    fun onCheckedChange(newChecked: Boolean) {
        checked.value = newChecked
    }

    fun onLoginClicked() {
        // Handle login logic here
        viewModelScope.launch {
            // Perform login
        }
    }
}
