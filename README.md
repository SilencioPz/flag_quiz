🎮 README.md - FlagQuiz: O Jogo das Bandeiras

(Um projeto Kotlin com música clássica e efeitos sonoros imersivos!)
-------------------------------------------------------------------------------------------------------
🌍 Sobre o Projeto

Aplicativo Android desenvolvido em Kotlin para testar conhecimentos geográficos de forma divertida:

✅ 30 bandeiras de países selecionados

🎵 Trilha sonora de Beethoven (para um clima épico!)

🔊 Efeitos sonoros gerados por IA (imersão total)

📱 UI intuitiva com Jetpack Compose
-------------------------------------------------------------------------------------------------------
🚀 Roadmap

Versão	        Status	      Observação

Kotlin 2.0	    ✅ Estável	  Compatível com Android 8+ (API 26)

Modo Hard	      🔄 Em breve	  Timer + Dicas limitadas

Ranking	        🛠️ Planejado	Salvar pontuações com Room DB
-------------------------------------------------------------------------------------------------------
🛠️ Tecnologias & Ferramentas

Componente	Detalhes

Linguagem	Kotlin 2.0.21

Arquitetura	MVVM + Clean Architecture

Bibliotecas	Jetpack Compose, Coroutines, ViewModel

Recursos	Assets otimizados (SVG + WebP)

📂 Estrutura do Projeto

FlagQuiz/  

├── app/  

│   ├── src/main/  

│   │   ├── com/example/flagquiz  

│   │   │   ├── data/        # Lógica das bandeiras (JSON/Repository)  

|   |   |   |__ flags/       # Todas as bandeiras do jogo

│   │   │   ├── ui.theme/    # Composables (GameScreen, Settings)  

│   │   │   ├── components/  # Configurações de idiomas

|   |   |   |__ screens/     # Telas do jogo

|   |   |   |__ viewmodels/  # Parâmetros do jogo(começar jogo, nome do jogador, etc)

│   │   │   └── utils/       # Defini o idioma que será usado

│   │   └── res/              

│   │       ├── raw/         # Música e SFX  

│   │       └── drawable/    # Bandeiras em SVG  

├── build.gradle.kts         # Dependências principais  

└── .gitignore               # Ignora /build, .gradle, etc.  

------------------------------------------------------------------------------------------
⚡ Como Executar

Pré-requisitos:

    Android Studio 2025+

    Emulador/Dispositivo (API 26+)

------------------------------------------------------------------------------------------
Passos:

bash

git clone https://github.com/seu-user/FlagQuiz.git  

cd FlagQuiz  

# Abra no Android Studio e sincronize o Gradle  

------------------------------------------------------------------------------------------
🎯 Features a serem implementadas

✔ Sistema de Pontuação: Acertos consecutivos dobram a pontuação!

✔ Efeitos Sonoros: Feedback imersivo para acertos/erros

✔ Otimizado: Bandeiras em SVG (sem pixelização)
------------------------------------------------------------------------------------------

🔮 Próximas Atualizações

    Adicionar modo multiplayer (via Bluetooth)

    Suporte a temas personalizados

    Integrar API de países para curiosidades

------------------------------------------------------------------------------------------
📌 Compatibilidade

📱 Mínima: Android 8.0 (API 26)

🌟 Recomendada: Android 12+ (API 31)

------------------------------------------------------------------------------------------
Dependências principais:

kotlin

implementation("androidx.compose.material3:material3:1.2.0")  

implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0")  

------------------------------------------------------------------------------------------
🎉 Desenvolvido com:

    Kotlin 💙

    Música clássica 🎻 (Beethoven)

    Efeitos sonoros por IA 🤖

"Por um mundo onde todos sabem a bandeira de Madagascar!" 🇲🇬
