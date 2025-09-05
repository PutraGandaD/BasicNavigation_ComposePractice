package com.putragandad.basicnavigationcompose.screens.library

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.putragandad.basicnavigationcompose.R
import com.putragandad.basicnavigationcompose.ui.theme.BasicNavigationComposeTheme

@Composable
fun LibraryScreen(modifier: Modifier = Modifier) {
    LoginScreen(
        modifier = modifier,
        onLoginClick = {},
        onGoogleLoginClick = {},
        onAppleLoginClick = {},
        onSignUpClick = {},
        onForgetPasswordClick = {},
        pwdVisibility = rememberSaveable { mutableStateOf(false) }
    )
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    onGoogleLoginClick: () -> Unit,
    onAppleLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgetPasswordClick: () -> Unit,
    pwdVisibility: MutableState<Boolean>
) {
    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Sectionr
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(R.string.login_header_title),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.login_header_description),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Email text fields
            val email = remember { mutableStateOf("") }
            OutlinedTextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                },
                label = {
                    Text(text = "Email")
                },
                trailingIcon = {
                    Surface(
                        modifier = Modifier
                            .size(40.dp),
                        shape = CircleShape,
                        color = Color.White
                    ) {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email Icon",
                                tint = Color.Unspecified // maintain original icon color in light/dark
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(100.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password text field
            val password = remember {
                mutableStateOf("")
            }
            val visible = pwdVisibility.value
            val visualTransformation = if(pwdVisibility.value) VisualTransformation.None else PasswordVisualTransformation()
            
            OutlinedTextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                label = {
                    Text(text = "Password")
                },
                visualTransformation = visualTransformation,
                trailingIcon = {
                    Surface(
                        modifier = Modifier
                            .size(40.dp),
                        shape = CircleShape,
                        color = Color.White
                    ) {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(
                                onClick = {
                                    pwdVisibility.value = !visible
                                },
                                modifier = Modifier.size(25.dp)
                            ) {
                                Icon(
                                    painter = if (pwdVisibility.value) {
                                        painterResource(id = R.drawable.ic_visibility_on)
                                    } else {
                                        painterResource(id = R.drawable.ic_visibility_off)
                                    },
                                    contentDescription = if (pwdVisibility.value) "Hide password" else "Show password",
                                    tint = Color.Unspecified
                                )
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(100.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // check box remember me and forget password
            var checkedBox by remember { mutableStateOf(false) }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Checkbox(
                        checked = checkedBox,
                        onCheckedChange = { checkedBox = it },
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = MaterialTheme.colorScheme.onSurface,
                            checkedColor = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.remember_me_checkbox_label),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                    )
                }
                Text(
                    text = "Forget Password",
                    style = TextStyle(
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier.clickable {
                        onForgetPasswordClick
                    }
                )
            }

            Spacer(modifier = Modifier.height(26.dp))
            
            // Login button
            Button(
                onClick = { onLoginClick },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(26.dp))

            // Or Text
            Text(
                text = "Or",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(26.dp))

            // Social login button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ElevatedButton(
                    onClick = { onAppleLoginClick },
                    modifier = Modifier
                        .height(48.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = Color.White
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_google_colored),
                        contentDescription = "Login with Google Icon",
                        modifier = Modifier
                            .size(24.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = "Google",
                        color = Color.Black,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                ElevatedButton(
                    onClick = { onGoogleLoginClick },
                    modifier = Modifier
                        .height(48.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = Color.White
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_apple_black),
                        contentDescription = "Login with Apple icon",
                        modifier = Modifier
                            .size(24.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = "Apple",
                        color = Color.Black,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Register
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account?",
                    style = MaterialTheme.typography.labelLarge,
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = "Sign Up",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        onSignUpClick
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Light")
@Composable
fun LoginScreenPreview() {
    BasicNavigationComposeTheme {
        LibraryScreen()
    }
}