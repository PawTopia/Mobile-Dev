package com.example.pawtopia.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pawtopia.R
import com.example.pawtopia.common.isValidEmail
import com.example.pawtopia.common.state.InputTextState
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(
    navigateToRegister: () -> Unit,
    login: () -> Unit,
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    var emailState by remember {
        mutableStateOf(InputTextState())
    }
    var passwordState by remember {
        mutableStateOf(InputTextState())
    }
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo), contentDescription = "logo",
            alignment = Alignment.Center,
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Masuk", fontSize = 24.sp,
            fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start)
        )
        InputText(
            value = emailState.value,
            onChange = { newValue ->
                emailState = emailState.copy(
                    value = newValue,
                    isError = !newValue.isValidEmail()
                )
            },
            label = "Email",
            isError = emailState.isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Email, contentDescription = "Email")
            },
            trailingIcon = {
                if (emailState.value.isNotEmpty()) {
                    IconButton(onClick = { emailState = emailState.copy(value = "") }) {
                        Icon(imageVector = Icons.Outlined.Clear, contentDescription = "Clear")
                    }
                }
            },
            supportingText = {
                if (emailState.isError) {
                    Text(text = "Email not valid")
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        )

        InputText(
            value = passwordState.value,
            onChange = { newValue ->
                passwordState = passwordState.copy(
                    value = newValue,
                    isError = newValue.length < 8
                )
            },
            label = "Password",
            isError = passwordState.isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Password")
            },
            trailingIcon = {
                val icon =
                    if (passwordVisibility) ImageVector.vectorResource(R.drawable.ic_visibility_off) else ImageVector.vectorResource(
                        R.drawable.ic_visibility
                    )
                val desc = if (passwordVisibility) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(imageVector = icon, contentDescription = desc)
                }
            },
            supportingText = {
                if (passwordState.isError) {
                    Text(text = "Password at least 8 characters")
                }
            },
        )

        Button(
            onClick = {
                auth.signInWithEmailAndPassword(emailState.value, passwordState.value)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Authentication Success.", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        }
                    }
                login() },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            Text(
                text = "Log In",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(
                4.dp,
                alignment = Alignment.CenterHorizontally
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            Text(
                text = "Belum punya akun? ",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Daftar Disini",
                modifier = Modifier.clickable { navigateToRegister() },
                fontWeight = FontWeight.Bold,
                style = TextStyle(textDecoration = TextDecoration.Underline),
            )
        }
    }
}

@Composable
fun InputText(
    value: String,
    onChange: (String) -> Unit,
    label: String,
    isError: Boolean,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    supportingText: @Composable () -> Unit = {}
) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        singleLine = singleLine,
        shape = RoundedCornerShape(16.dp),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        isError = isError,
        label = {
            Text(text = label)
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        supportingText = supportingText,
        modifier = modifier
            .fillMaxWidth()
    )
}
