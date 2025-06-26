# FRC Robot Control System

Este repositório documenta o sistema de controle de um robô FRC equipado com drivetrain Swerve, elevador com três níveis de altura e mecanismo de intake para manipulação de "canos". Toda a operação é realizada por meio de um controle Xbox, com foco em modularidade, clareza e segurança para ambiente competitivo.

## Estrutura do Projeto

```
/src/main/java/frc/robot/
│
├── Robot.java                # Classe principal do robô
├── Constants.java            # Armazena constantes globais
├── subsystems/               # Submódulos do robô
│   ├── SwerveSubsystem.java
│   ├── ElevatorSubsystem.java
│   └── IntakeSubsystem.java
└── commands/                 # Comandos que operam os subsistemas
    ├── DriveCommand.java
    ├── IntakeCommand.java
    ├── ShootCommand.java
    └── SetElevatorPositionCommand.java
```

## Mapeamento de Controles

| Controle Xbox        | Função                                               |
|----------------------|------------------------------------------------------|
| Botão B              | Dispara o cano usando o intake                       |
| Botão Y              | Retrae o cano lentamente com velocidade -0.1         |
| Botão A              | Coleta o cano (aciona intake para dentro)           |
| D-Pad ↑              | Eleva para posição alta                              |
| D-Pad ↓              | Eleva para posição inicial                           |
| D-Pad ← / →          | Eleva para posição intermediária                     |
| Botão B              | Também retorna elevador à posição inicial            |
| Bumper Esquerdo      | Limita velocidade do Swerve Drive para 5%            |
| Bumper Direito       | Ajusta velocidade para 50%                           |
| Ambos Bumpers        | Desbloqueia velocidade máxima (100%)                |

## Lógica dos Subsistemas

### Swerve Drive

Sistema de movimentação omnidirecional com controle independente de trção e direção. A velocidade máxima do robô é ajustada dinamicamente com os bumpers do controle:

```java
if (leftBumper && rightBumper) {
    maxSpeed = 1.0; // 100%
} else if (rightBumper) {
    maxSpeed = 0.5; // 50%
} else {
    maxSpeed = 0.05; // 5%
}
```

*Isto é apenas um exemplo.*

### Intake (Coletor e Lançador de Canos)

Controle do mecanismo de intake para capturar ou lançar os canos.

```java
if (buttonA) {
    intake.setSpeed(1.0); // coleta o cano
} else if (buttonB) {
    intake.setSpeed(-1.0); // dispara o cano
} else if (buttonY) {
    intake.setSpeed(-0.1); // recolhe suavemente o cano
} else {
    intake.stop(); // desliga o motor
}
```

*Isto é apenas um exemplo.*

### Elevator (Elevador com Três Posições)

Sistema de elevação automatizado com três posições programadas, controlado pelo D-Pad ou botão B.

```java
if (dpadUp) {
    elevator.setTargetPosition(HIGH_POS); // posição alta
} else if (dpadLeft || dpadRight) {
    elevator.setTargetPosition(MID_POS); // posição intermediária
} else if (dpadDown || buttonB) {
    elevator.setTargetPosition(LOW_POS); // posição inicial
}
```

*Isto é apenas um exemplo.*

## Arquitetura de Código

O projeto segue o modelo de arquitetura Command-Based da WPILib, visando escalabilidade e separação de responsabilidades.

## Possíveis Expansões Futuras

- Inclusão de sensores para detecção automática de canos no intake
- Feedback visual por LEDs RGB
- Integração com Shuffleboard ou SmartDashboard para ajustes dinâmicos
- Sistema de auto balanceamento com giroscópio no drivetrain

## Testes e Validação

- Todos os subsistemas testados em ambiente real
- Verificações de desempenho sob diferentes velocidades
- Validação do elevador com controle via encoder
- Proteção do intake contra sobrecarga e reversão forçada

## Requisitos

- Java 17 ou superior
- WPILib 2025
- Visual Studio Code com extensão WPILib
- Gradle (fornecido pela WPILib)

## Licença

Este projeto está licenciado sob a Licença MIT. Consulte o arquivo `LICENSE` para mais informações.

## Contribuições

Contribuições são bem-vindas. Utilize issues ou pull requests para propor melhorias ou correções.
