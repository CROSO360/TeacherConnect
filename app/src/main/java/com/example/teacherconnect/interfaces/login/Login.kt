package com.example.teacherconnect.interfaces.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.teacherconnect.R

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF264D80),
                        Color(0xFFCC26BC)
                    )
                )
            )
    ) {
        content()
    }
}
@Composable
fun LoginScreen(navController: NavController) {
    GradientBackground {
        val showLoginForm = rememberSaveable {
            mutableStateOf(true)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.logo_blanco),
                contentDescription = null,
                modifier = Modifier.padding(10.dp)
            )
            if (showLoginForm.value) {
                Text(text = "¡Bienvenido!", color = Color.White, fontSize = 40.sp,
                    modifier = Modifier.padding(bottom = 50.dp))
            } else {
                Text(text = "¡Regístrate!", color = Color.White, fontSize = 40.sp,
                    modifier = Modifier.padding(bottom = 50.dp) )
            }
            UserForm(showLoginForm = showLoginForm.value) { email, password ->
                if (showLoginForm.value) {
                    Log.d("TeacherConnect", "Iniciando sesión con $email y $password")

                } else {
                    Log.d("TeacherConnect", "Creando Cuenta con $email y $password")
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val text1 =
                    if (showLoginForm.value) "¿No tienes cuenta?"
                    else "¿Ya tienes cuenta?"
                val text2 =
                    if (showLoginForm.value) "Regístrate"
                    else "Inicia sesión"
                Text(text = text1, color = Color.White)
                Text(
                    text = text2,
                    modifier = Modifier
                        .clickable { showLoginForm.value = !showLoginForm.value }
                        .padding(start = 5.dp),
                    color = Color.Cyan
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
    showLoginForm: Boolean,
    onDone: (String, String) -> Unit = { email, password -> }
) {
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisible = rememberSaveable {
        mutableStateOf(false)
    }
    val valido = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() &&
                password.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        EmailInput(emailState = email)
        PasswordInput(passwordState = password, labelId = "Password", passwordVisible = passwordVisible)

        if (showLoginForm) {
            SubmitButton(textId = "Login", inputValido = valido) {
                onDone(email.value.trim(), password.value.trim())
                keyboardController?.hide()
            }
        } else {
            SubmitButton(textId = "Crear cuenta", inputValido = valido) {
                onDone(email.value.trim(), password.value.trim())
                keyboardController?.hide()
            }
        }
    }
}


@Composable
fun SubmitButton(textId: String, inputValido:Boolean, onClic:()->Unit) {
    Button(
        onClick = onClic,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(0.8f),
        shape = CircleShape,
        enabled = inputValido
    ) {
        Text(
            text = textId,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(passwordState: MutableState<String>, labelId: String, passwordVisible: MutableState<Boolean>) {
    val visualTransformation=if (passwordVisible.value)
        VisualTransformation.None
    else PasswordVisualTransformation()
    OutlinedTextField(value = passwordState.value, onValueChange = {passwordState.value=it},
        label={ Text(text = labelId, color= Color.White) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(0.8f)
            .drawBehind {
                drawLine(
                    color = Color.White,
                    start = Offset(0f, size.height - 1f),
                    end = Offset(size.width, size.height - 1f)
                )
            },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent
        ),
        visualTransformation=visualTransformation,
        trailingIcon = {
            if (passwordState.value.isNotBlank()){
                PasswordVisibleIcon(passwordVisible)
            }
            else null
        }
    )
}

@Composable
fun PasswordVisibleIcon(passwordVisible: MutableState<Boolean>) {
    val image =
        if (passwordVisible.value)
            Icons.Default.VisibilityOff
        else
            Icons.Default.Visibility
    IconButton(onClick = {passwordVisible.value= !passwordVisible.value} ) {
        Icon(imageVector = image, contentDescription = "", tint = Color.White)
    }
}

@Composable
fun EmailInput(emailState: MutableState<String>, labelId: String="Email") {
    InputField(
        valueState=emailState,
        labelId=labelId,
        labelColor = Color.White,
        keyboardType= KeyboardType.Email
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    valueState: MutableState<String>,
    isSingleLine: Boolean = true,
    labelId: String,
    labelColor: Color = Color.Black,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId, color = labelColor) },
        singleLine = isSingleLine,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .drawBehind {
                drawLine(
                    color = Color.White,
                    start = Offset(0f, size.height - 1f),
                    end = Offset(size.width, size.height - 1f)
                )
            },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}
