// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;
import frc.robot.RobotContainer;
import frc.robot.commands.Elevador.Descer;
import frc.robot.commands.Elevador.StopElevator;
import frc.robot.commands.Elevador.Subir;
import frc.robot.commands.Intake.Atirar;
import frc.robot.subsystems.Intake.Tracao;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AtirarGeral extends SequentialCommandGroup {
  public Tracao tracao = RobotContainer.tracaoA;
  
  /** Creates a new AtirarGeral. */
  public AtirarGeral() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new Atirar(0),
      new Tracaocmd(tracao, -4.3),
      new Descer(1),
      new WaitCommand(0.3),
      new StopElevator()
    );
  }
}
