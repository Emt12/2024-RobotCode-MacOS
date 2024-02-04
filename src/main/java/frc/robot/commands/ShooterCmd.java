package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.photonvision.PhotonCamera;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.subsystems.Shooter;

public class ShooterCmd extends CommandBase {
    private Shooter shooter;
    private PhotonCamera photonCamera;
    private final PIDController pidController;   
    private final Limelight visionSubsystem;
    private final DriveSubsystem driveSubsystem;

    public ShooterCmd(DriveSubsystem driveSubsystem, PhotonCamera photonCamera, Limelight visionSubsystem) {
        this.driveSubsystem = driveSubsystem;
        this.photonCamera = photonCamera;
        this.visionSubsystem = visionSubsystem;
        this.pidController = new PIDController(1, 0, 0);
        this.shooter = new Shooter();

        addRequirements(visionSubsystem);
        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        shooter.shoot();
        super.execute();
    }

    
    
}
