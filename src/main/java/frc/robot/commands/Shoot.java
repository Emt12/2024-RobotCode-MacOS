package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Dashboard;

public class Shoot extends CommandBase {
    private final Shooter shooter;
    private final Dashboard dashboard;

    public Shoot(Shooter shooter, Dashboard dashboard){
        this.shooter = shooter;
        this.dashboard = dashboard;
    }
}
