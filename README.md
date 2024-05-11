# Gauge Chart Game

Essa aplicação tem como objetivo utilizar a biblioteca do Gauge Chart no Android Studio, para ser lúdico foi criado um simples jogo onde o jogador avança ou recua em um medidor de progresso em direção a 100. Enquanto o jogador avança, o PC também avança automaticamente. O objetivo é chegar a 100 antes do PC.

## Funcionalidades 🛠️

| Funcionalidade          | Descrição                                                                                       |
|-------------------------|-------------------------------------------------------------------------------------------------|
| Avançar ou Voltar       | O jogador pode avançar clicando no botão "Avançar" e recuar clicando no botão "Voltar". |
| Progresso Automático do PC  | O PC avança automaticamente em direção a 100. |
| Níveis do PC       | Conforme o jogador avança de nível, o PC avança em velocidades diferentes. |
| Toast de Vencedor   | Quando alguém vence, um toast é exibido indicando o vencedor. |

## Níveis e Velocidades do PC 🚀

- **PC**: Avança de 5 em 5 a cada 1s.
- **PC Moderado**: Avança de 10 em 10 a cada 1s.
- **PC Radiante**: Avança de 25 em 25 a cada 1s.

```OBS.: Toda vez que o Jogador perde em algum dos níves, o jogo é resetado.```


## Tecnologias Utilizadas 🚀

- **Biblioteca Gauge**: Disponivel através desse repositório: https://github.com/Gruzer/simple-gauge-android

## Dependências Implementadas
Em settings.graddle.kts foi colocado: 
```kts
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```


Nas dependências foi colocado:
```kts
dependencies {
	implementation ("com.github.Gruzer:simple-gauge-android:0.3.1")
}
```

## Inserindo o Gauge no Projeto e modificando suas cores:
Inserindo gauge no XML:
```xml
<com.ekn.gruzer.gaugelibrary.HalfGauge
        android:id="@+id/jogadorGauge"
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:layout_below="@id/txtJogador"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="16dp" />
```

Modificando suas cores:
```java
private void defineFaixasDeCores(HalfGauge gauge) {
        Range range1 = new Range();
        range1.setColor(Color.parseColor("#ce0000"));
        range1.setFrom(0.0); //Começo da Cor
        range1.setTo(40.0); //Final da Cor - Ou seja, fica nessa cor de 0 a 40

        Range range2 = new Range();
        range2.setColor(Color.parseColor("#E3E500"));
        range2.setFrom(40.0);
        range2.setTo(70.0); //Final da Cor - Ou seja, fica nessa cor de 40 a 70

        Range range3 = new Range();
        range3.setColor(Color.parseColor("#00b20b"));
        range3.setFrom(70.0);
        range3.setTo(100.0); //Final da Cor - Ou seja, fica nessa cor de 70 a 100

        gauge.addRange(range1);
        gauge.addRange(range2);
        gauge.addRange(range3);

        gauge.setMinValue(0.0); // Valor minimo do Gauge
        gauge.setMaxValue(100.0); // Valor máximo do Gauge
        gauge.setValue(0.0); // Valor de Default do Gauge
    }
```

## Como Usar 📋

1. Faça o download do código-fonte do projeto.
2. Importe o projeto para o Android Studio.
3. Compile e execute o aplicativo em um dispositivo ou emulador Android.

## Telas 📱
### Tela
<p>Exibe os marcadores de Progresso, os Gauges de avanço, Nivel do PC e feedbacks de Vitória e Derrota</p>
<img src="https://github.com/LarissaSL/Gauge_Chart_Game/assets/112571317/dac1a7b9-48dc-4bed-b3e9-025597850005" width="300">

## Desenvolvimento

| Desenvolvimento                           | Descrição                                                                                                                                                                            |
|-------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Configuração do Ambiente | Colocando as depedências necessárias para utilizar o Gauge |
| Implementação da Interface do Usuário  | Desenvolvimento da interface do usuário (XML) para exibir o medidor de progresso, botões e informações sobre o jogo. |
| Implementação da Lógica do Jogo | Implementação da lógica do jogo, incluindo a progressão do jogador e do PC, a detecção de vencedores e o reset do jogo. |
| Configuração do Handler e do Runnable | Configuração do Handler e do Runnable para controlar o avanço automático do PC. |
| Configuração dos Botões de Avançar e Voltar | Configuração dos botões de avançar e voltar para permitir que o jogador controle o progresso. |

## 🎥 Materiais de Apoio Utilizados

Aqui estão alguns vídeos que utilizei como apoio durante o desenvolvimento:

- [![YouTube Video 1](https://www.youtube.com/watch?v=te2Qipd91s4&t=125s) **Using Gauge library in Android Studio**

- [Documentação Oficial do Gruzer com Exemplos](https://github.com/Gruzer/simple-gauge-android)


## Simulação 🎬

Aqui está uma simulação do aplicativo em funcionamento:

https://github.com/LarissaSL/Gauge_Chart_Game/assets/112571317/09c014ab-20b3-4b9c-ade4-5dde4cfb1b35




