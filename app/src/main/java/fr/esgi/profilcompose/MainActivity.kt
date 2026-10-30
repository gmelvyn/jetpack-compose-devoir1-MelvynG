package fr.esgi.profilcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.esgi.profilcompose.ui.theme.ProfilComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfilComposeTheme {
                DeveloperProfileScreen(
                    name = "Melvyn",
                    role = "Étudiant · Développeur Android junior",
                    description = "Étudiant à l’ESGI, je découvre le développement Android et je crée mes premières interfaces avec Kotlin et Jetpack Compose.",
                    skills = listOf("Kotlin", "Jetpack Compose", "Git & GitHub"),
                    availability = "Disponible",
                    contact = "melvyn@example.com",
                    onContactClick = {
                    }
                )
            }
        }
    }
}

@Composable
fun DeveloperProfileScreen(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    availability: String,
    contact: String,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            ProfileCard(
                name = name,
                role = role,
                description = description,
                skills = skills,
                availability = availability,
                contact = contact,
                onContactClick = onContactClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun ProfileCard(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    availability: String,
    contact: String,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            ProfileHeader(name = name, role = role)
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
            )
            Text(
                text = "Compétences",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            for (skill in skills) {
                SkillItem(
                    skill = skill,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
                )
            }
            SecondaryInformation(
                label = "Contact",
                value = contact,
                modifier = Modifier.padding(top = 12.dp)
            )
            SecondaryInformation(
                label = "Disponibilité",
                value = availability,
                modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
            )
            ProfileAction(
                label = "Contacter",
                onClick = onContactClick,
                modifier = Modifier.fillMaxWidth()
            )
            PortfolioAction(
                label = "Voir le portfolio",
                owner = name,
                onClick = {
                },
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun ProfileHeader(name: String, role: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = role,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun SkillItem(skill: String, modifier: Modifier = Modifier) {
    Text(
        text = "• $skill",
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

@Composable
fun SecondaryInformation(label: String, value: String, modifier: Modifier = Modifier) {
    Text(
        text = "$label : $value",
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

@Composable
fun ProfileAction(label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, modifier = modifier) {
        Text(text = label)
    }
}

@Composable
fun PortfolioAction(
    label: String,
    owner: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = label,
        style = MaterialTheme.typography.labelLarge,
        modifier = modifier
            .clickable(role = Role.Button, onClick = onClick)
            .semantics { contentDescription = "Ouvrir le portfolio de $owner" }
            .padding(16.dp)
    )
}

@Preview(name = "Android junior · Disponible", showBackground = true, widthDp = 393, heightDp = 851)
@Composable
fun AndroidProfilePreview() {
    ProfilComposeTheme {
        DeveloperProfileScreen(
            name = "Melvyn",
            role = "Étudiant · Développeur Android junior",
            description = "Étudiant à l’ESGI, je découvre le développement Android et je crée mes premières interfaces avec Kotlin et Jetpack Compose.",
            skills = listOf("Kotlin", "Jetpack Compose", "Git & GitHub"),
            availability = "Disponible",
            contact = "melvyn@example.com",
            onContactClick = {}
        )
    }
}

@Preview(name = "Full-stack · En mission", showBackground = true, widthDp = 393, heightDp = 851)
@Composable
fun FullStackProfilePreview() {
    ProfilComposeTheme {
        DeveloperProfileScreen(
            name = "Camille Martin",
            role = "Développeuse full-stack",
            description = "Je conçois des applications web accessibles et des services backend pour accompagner les équipes dans leurs projets.",
            skills = listOf("TypeScript", "React", "Java & Spring"),
            availability = "En mission · Indisponible actuellement",
            contact = "camille@example.com",
            onContactClick = {}
        )
    }
}
