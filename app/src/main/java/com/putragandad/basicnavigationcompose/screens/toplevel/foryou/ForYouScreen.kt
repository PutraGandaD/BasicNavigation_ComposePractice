package com.putragandad.basicnavigationcompose.screens.toplevel.foryou

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.putragandad.basicnavigationcompose.R
import com.putragandad.basicnavigationcompose.ui.theme.BasicNavigationComposeTheme
import com.putragandad.basicnavigationcompose.ui.theme.PureWhite

// composable screen for bottom nav bar screen
@Composable
fun ForYouScreen(modifier: Modifier = Modifier) {
    var checkedBox by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = PureWhite,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Join Waitlist",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(top = 6.dp))
                Text(
                    text = stringResource(id = R.string.waitlist_description),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(top = 16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val firstname = rememberSaveable { mutableStateOf("") }
                    OutlinedTextField(
                        value = firstname.value,
                        placeholder = { Text("First Name") },
                        onValueChange = { firstname.value = it },
                        modifier = Modifier.weight(1f)
                    )
                    val secondname = rememberSaveable { mutableStateOf("") }
                    OutlinedTextField(
                        value = secondname.value,
                        placeholder = { Text("Last Name") },
                        onValueChange = { secondname.value = it },
                        modifier = Modifier.weight(1f)

                    )
                }
                val email = rememberSaveable { mutableStateOf("") }
                OutlinedTextField(
                    value = email.value,
                    placeholder = { Text("Email") },
                    onValueChange = { email.value = it },
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(top = 6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = checkedBox,
                        onCheckedChange = { checkedBox = it }
                    )
                    Text(
                        text = stringResource(id = R.string.waitlist_agree_terms_condition),
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                }
                Spacer(modifier = Modifier.padding(top = 6.dp))
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("Join the Waitlist")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    BasicNavigationComposeTheme {
        ForYouScreen()
    }
}
