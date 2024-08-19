package br.dev.gustavovilela.firstprojectcompose.presentation.ui.demo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.dev.gustavovilela.firstprojectcompose.R
import br.dev.gustavovilela.firstprojectcompose.presentation.NavRoutes

@Composable
fun DemoScreen(
    navContrller: NavHostController,
    loginViewModel: DemoViewModel = viewModel()
) {
    // Observing the state from ViewModel
    val text by loginViewModel.text
    val password by loginViewModel.password
    val sliderPosition by loginViewModel.sliderPosition
    val switchState by loginViewModel.switchState
    val checked by loginViewModel.checked

    // Adding a ScrollState for vertical scrolling.
    val scrollState = rememberScrollState()

    // Column organizes the elements vertically, with the addition of Modifier.verticalScroll(scrollState).
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState), // Enabling vertical scrolling.
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Text displays a simple text.
        Text(
            text = stringResource(id = R.string.screen_title),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // OutlinedTextField is a text field with a border.
        OutlinedTextField(
            value = text,
            onValueChange = { loginViewModel.onTextChange(it) },
            label = { Text(stringResource(id = R.string.text_field_label)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // OutlinedTextField for password input, using PasswordVisualTransformation to hide the text.
        OutlinedTextField(
            value = password,
            onValueChange = { loginViewModel.onPasswordChange(it) },
            label = { Text(stringResource(id = R.string.password_field_label)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Button is a clickable button.
        Button(
            onClick = { navContrller.navigate(NavRoutes.LIST) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(stringResource(id = R.string.button_text))
        }

        // Row organizes the elements horizontally.
        // Checkbox is a selectable checkbox.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { loginViewModel.onCheckedChange(it) },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(stringResource(id = R.string.checkbox_text))
        }

        // Switch is a toggle switch.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Switch(
                checked = switchState,
                onCheckedChange = { loginViewModel.onSwitchChange(it) },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(stringResource(id = R.string.switch_text))
        }

        // Text displaying the current value of the Slider.
        Text(
            text = stringResource(id = R.string.slider_text, sliderPosition.toInt()),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        // Slider is a control for selecting a value within a range.
        Slider(
            value = sliderPosition,
            onValueChange = { loginViewModel.onSliderChange(it) },
            valueRange = 0f..100f,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // CircularProgressIndicator displays a circular progress indicator.
        CircularProgressIndicator(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .size(48.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DemoScreenPreview() {
    DemoScreen(rememberNavController())
}