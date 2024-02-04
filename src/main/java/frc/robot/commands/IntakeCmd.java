package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Dashboard;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants;
import org.photonvision.PhotonCamera;

public class IntakeCmd extends CommandBase{
    private PIDController pidController;
    private Limelight visionSubsystem;
    private Dashboard dashboard;
    private PhotonCamera photonCamera;
    public IntakeCmd(PhotonCamera photonCamera, Limelight visionSubsystem){
        this.photonCamera = photonCamera;
        this.visionSubsystem = visionSubsystem;
        this.pidController = new PIDController(1, 0, 0);
        addRequirements(visionSubsystem);
    }
    public void IntakeUp(){

    }
    public void IntakeDown(){

    }
}
